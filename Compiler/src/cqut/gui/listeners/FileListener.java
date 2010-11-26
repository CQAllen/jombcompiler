package cqut.gui.listeners;

/**
 * 文件菜单中各个菜单项的监听器
 * 
 * @author Cheng
 * 
 * @date 2010-11-26
 */
public interface FileListener {

	/**
	 * 打开文件
	 */
	public void open();

	/**
	 * 保存文件
	 */
	public boolean save();

	/**
	 * 另存为
	 */
	public boolean saveAs();

	/**
	 * 退出
	 */
	public void exit();
}
