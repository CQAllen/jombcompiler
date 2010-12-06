package cqut.gui.listeners;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import cqut.gui.Window;
import cqut.gui.util.SWTUtil;
import cqut.syntaxAnalyzer.SyntaxServiceImpl;
import cqut.util.SourceReader;
import cqut.util.SystemProperty;
import cqut.util.entity.Source;

public class FileEvent implements FileListener {

	public static String path;
	public static boolean isModify = false;

	@Override
	public void exit() {
		Display.getCurrent().getActiveShell().dispose();
		Display.getCurrent().dispose();
	}

	@Override
	public void open() {
		String name = SWTUtil.openDialog(SWT.OPEN, new String[] { "*.jom;",
				"*.*" },// 打开源文件选择对话框
				new String[] { "jom源文件" + " (*.jom)", "所有文件" + " (*.*)" });
		if (name != null) {
			Shell currentShell = Display.getCurrent().getActiveShell();
			Cursor waitCursor = new Cursor(Display.getCurrent(),
					SWT.CURSOR_WAIT);
			currentShell.setCursor(waitCursor);// 设置鼠标忙

			currentShell.setText("Jom编译器 --" + name);
			StringBuffer sb = new StringBuffer();
			java.util.List<String> source = Source.getInstance(name)
					.getSource();
			for (String s : source) {
				sb.append(s);
			}
			SyntaxServiceImpl.syntaxes = SystemProperty
					.readProperties("src/syntax.properties");
			Window.highlightText.setText(sb.toString());
			path = SourceReader.getAbsoluteFilePath();
			int line = Source.getInstance().getLines();
			Window.lineNumber.removeAll();
			for (int i = 0; i < line; i++) {
				Window.lineNumber.add((i + 1) + "");
			}
			currentShell.setCursor(null);// 设置鼠标正常
			waitCursor.dispose();
		}
	}

	@Override
	public boolean save() {
		if (path == null) {
			return saveAs();
		}
		if (isModify) {
			String sh = Display.getCurrent().getActiveShell().getText();
			Display.getCurrent().getActiveShell().setText(sh.replace("*", ""));
			isModify = false;
		}
		SourceReader.write(path, Window.highlightText.getText());
		return true;
	}

	@Override
	public boolean saveAs() {
		FileDialog saveDialog = new FileDialog(Display.getCurrent()
				.getActiveShell(), SWT.SAVE);
		saveDialog.setFilterExtensions(new String[] { "*.jom;", "*.*" });
		saveDialog
				.setFilterNames(new String[] { "Jom源文件 (*.jom)", "All Files " });
		String name = saveDialog.getFileName();
		path = saveDialog.open();
		if (path == null || path.equals("")) {
			return false;
		}
		String[] tmp = path.split("\\\\");
		name = tmp[tmp.length - 1];
		tmp = null;
		if (name.equals("")) {// 如果未填写名称，则无任何事件
			return false;
		}
		if (name.indexOf(".jom") != name.length() - 4) {// 如果未写入后缀名，自动添加上后缀名
			name += ".jom";
		}
		File file = new File(saveDialog.getFilterPath(), name);
		if (file.exists()) {// 如果文件已存在，则提示覆盖
			int open = SWTUtil.showMessageBox(Display.getCurrent()
					.getActiveShell(), "警告", "文件 " + file.getName()
					+ " 已存在,是否要覆盖该文件？", SWT.ICON_WARNING | SWT.YES | SWT.NO);
			if (open != SWT.YES) {
				return false;
			}
		}
		return save();
	}

}
