package cqut.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cqut.gui.customcontrol.HighlightText;
import cqut.gui.customcontrol.JomMenu;
import cqut.gui.util.SWTUtil;

public class Window {

	private Display display;
	private Shell shell;

	public Window() {
		display = new Display();
		shell = new Shell(display, SWT.ON_TOP | SWT.MAX | SWT.MIN | SWT.CLOSE);
		config();
	}

	private void config() {
		shell.setImage(SWTUtil.getImageFromResorce("../images/shell.png"));
		shell.setSize(800, 600);
		shell.setLocation(setLocation());
		shell.setText("Jom编译器");
		shell.setLayout(new FillLayout());
		shell.setMenuBar(new JomMenu(shell, SWT.BAR));
		createContent();
		shell.open();
		close();
	}

	/**
	 * 计算使shell显示在屏幕中间的坐标
	 * 
	 * @return Point
	 */
	private Point setLocation() {
		Monitor primary = display.getPrimaryMonitor();// 获取监视器类
		Rectangle bounds = primary.getBounds();// 得到监视器分辨率
		Rectangle rect = shell.getBounds();// 得到shell的分辨率
		// 计算使shell位于监视器中间的坐标
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		return new Point(x, y);
	}

	private void close() {
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

	private void createContent() {
		SashForm form = new SashForm(shell, SWT.HORIZONTAL);
		form.setLayout(new FillLayout());

		new HighlightText(form, SWT.BORDER | SWT.WRAP);

		Composite child = new Composite(form, SWT.NONE);
		child.setLayout(new FillLayout());
		createComposite(child);

		form.setWeights(new int[] { 40, 60 });
	}

	private void createComposite(Composite child) {
		SashForm form = new SashForm(child, SWT.VERTICAL);
		form.setLayout(new FillLayout());
		Text token = new Text(form, SWT.BORDER | SWT.WRAP);
		token.setEditable(false);
		Text symbol = new Text(form, SWT.BORDER | SWT.WRAP);
		symbol.setEditable(false);
		form.setWeights(new int[] { 60, 40 });
	}

	public static void main(String[] args) {
		new Window();
	}
}
