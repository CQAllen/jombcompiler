package cqut.gui.customcontrol;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cqut.gui.util.SWTUtil;
import cqut.util.entity.Error;
import cqut.util.entity.ErrorFacade;

public class ErrorDialog extends Dialog {

	public Shell pShell;

	public ErrorDialog(Shell shell, int style) {
		super(shell, SWT.CLOSE | SWT.BORDER | SWT.APPLICATION_MODAL);
		this.pShell = shell;
	}

	public void open() {
		int size = ErrorFacade.getInstance().getSize();
		if (size == 0) {
			SWTUtil.showMessageBox(pShell, "词法分析错误信息:", "恭喜，词法完全正确！", SWT.OK
					| SWT.ICON_INFORMATION);
		} else {
			final Shell shell = new Shell(pShell, SWT.CLOSE | SWT.BORDER
					| SWT.APPLICATION_MODAL);
			shell.setSize(400, 400);
			shell.setLocation(SWTUtil.setLocation(Display.getCurrent(), shell));
			shell.setModified(false);
			shell.setLayout(new FillLayout());
			shell.setText("错误数量:" + size + " 个，错误详细信息:");
			Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION
					| SWT.BORDER);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			TableColumn line = new TableColumn(table, SWT.LEFT);
			line.setText("行数");
			line.setWidth(80);
			TableColumn message = new TableColumn(table, SWT.LEFT);
			message.setText("错误信息");
			message.setWidth(220);
			for (Error e : ErrorFacade.getInstance().getErrors()) {
				new TableItem(table, SWT.CENTER).setText(new String[] {
						e.getRow() + "", e.getMessage() });
			}
			shell.pack();
			shell.open();
		}
	}

	@Override
	protected void checkSubclass() {
	}

}
