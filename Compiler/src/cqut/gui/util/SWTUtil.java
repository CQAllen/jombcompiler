package cqut.gui.util;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SWTUtil {

	private SWTUtil() {
	}

	/**
	 * 当必要时显示的提示信息
	 * 
	 * @param shell
	 * @param title
	 *            标题
	 * @param msg
	 *            提示内容
	 * @param style
	 *            提示风格
	 * @return int
	 */
	public static int showMessageBox(Shell shell, String title, String msg,
			int style) {
		if (shell != null && !shell.isDisposed()) {
			MessageBox mb = new MessageBox(shell, style);
			mb.setText(title);
			mb.setMessage(msg);
			return mb.open();
		} else {
			return -1;
		}
	}

	/**
	 * 网格布局的网格数据的生成：<br>
	 * 根据给定的参数生成不同的网格数据
	 * 
	 * @param style
	 * <br>
	 *            -1:纵向填充所有空间并充满<br>
	 *            0:横纵向都填充所有空间并充满<br>
	 *            1:横向填充所有空间并充满
	 * @return GridData
	 */
	public static GridData createGridData(int style) {
		GridData data = new GridData();
		if (style >= 0) {
			data.grabExcessHorizontalSpace = true;
			data.horizontalAlignment = GridData.FILL;
		}
		if (style <= 0) {
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
		}
		return data;
	}

	/**
	 * 从资源中获取图片
	 * 
	 * @param name
	 *            资源路径，相对路径绝对路径皆可
	 * @return SWT封装的Image对象
	 */
	public static Image getImageFromResorce(String name) {
		return new Image(Display.getCurrent(), SWTUtil.class
				.getResourceAsStream(name));
	}

	/**
	 * 打开对话框
	 * 
	 * @param style
	 *            对话框的风格
	 * @param filterExtensions
	 *            后缀名
	 * @param filterNames
	 * @return 选择文件的路径
	 */
	public static String openDialog(int style, String[] filterExtensions,
			String[] filterNames) {
		FileDialog fileDialog = new FileDialog(Display.getCurrent()
				.getActiveShell(), style);
		fileDialog.setFilterExtensions(filterExtensions);
		fileDialog.setFilterNames(filterNames);
		return fileDialog.open();
	}
}
