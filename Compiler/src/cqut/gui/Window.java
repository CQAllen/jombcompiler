package cqut.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import cqut.gui.customcontrol.HighlightText;
import cqut.gui.customcontrol.JomMenu;
import cqut.gui.util.SWTUtil;

public class Window {

	private Display display;
	private Shell shell;

	/* these are controls */
	public static HighlightText highlightText;
	public static Table token;
	public static Table symbol;

	public Window() {
		display = new Display();
		shell = new Shell(display, SWT.ON_TOP | SWT.MAX | SWT.MIN | SWT.CLOSE);
		config();
	}

	private void config() {
		shell.setImage(SWTUtil.getImageFromResorce("../images/shell.png"));
		shell.setSize(800, 600);
		shell.setLocation(SWTUtil.setLocation(display, shell));
		shell.setText("Jom编译器");
		shell.setLayout(new FillLayout());
		shell.setMenuBar(new JomMenu(shell, SWT.BAR));
		createContent();
		shell.open();
		close();
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

		highlightText = new HighlightText(form, SWT.BORDER | SWT.WRAP);

		Composite child = new Composite(form, SWT.NONE);
		child.setLayout(new FillLayout());
		createComposite(child);

		form.setWeights(new int[] { 40, 60 });
	}

	private void createComposite(Composite child) {
		SashForm form = new SashForm(child, SWT.VERTICAL);
		form.setLayout(new FillLayout());

		Composite up = new Composite(form, SWT.NONE);
		up.setLayout(new FillLayout());
		createUpComposite(up);
		Composite down = new Composite(form, SWT.NONE);
		down.setLayout(new FillLayout());
		createDownComposite(down);

		form.setWeights(new int[] { 50, 50 });
	}

	private void createUpComposite(Composite up) {
		token = new Table(up, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		token.setHeaderVisible(true);
		token.setLinesVisible(true);

		TableColumn meta = new TableColumn(token, SWT.LEFT);
		meta.setText("字符");
		meta.setWidth(100);

		TableColumn line = new TableColumn(token, SWT.LEFT);
		line.setText("字符所在行");
		line.setWidth(100);

		TableColumn pointer = new TableColumn(token, SWT.LEFT);
		pointer.setText("类别码");
		pointer.setWidth(100);
	}

	private void createDownComposite(Composite down) {
		symbol = new Table(down, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		symbol.setHeaderVisible(true);
		symbol.setLinesVisible(true);

		TableColumn meta = new TableColumn(symbol, SWT.LEFT);
		meta.setText("字符");
		meta.setWidth(100);

		TableColumn line = new TableColumn(symbol, SWT.LEFT);
		line.setText("类型");
		line.setWidth(100);

		TableColumn value = new TableColumn(symbol, SWT.LEFT);
		value.setText("数值");
		value.setWidth(80);

		TableColumn pointer = new TableColumn(symbol, SWT.LEFT);
		pointer.setText("类别码");
		pointer.setWidth(80);

		TableColumn kind = new TableColumn(symbol, SWT.LEFT);
		kind.setText("种属");
		kind.setWidth(100);
	}

	public static void main(String[] args) {
		new Window();
	}
}
