package cqut.gui.customcontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import cqut.gui.Window;
import cqut.gui.util.SWTUtil;
import cqut.util.Symbol;
import cqut.util.Token;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
import cqut.util.entity.TokenMeta;

public class JomMenu extends Menu {

	private MenuItem file;// 文件菜单项
	private MenuItem edit;// 编辑菜单项
	private MenuItem lexical;// 词法分析菜单项
	private MenuItem grammar;// 语法分析菜单项
	private MenuItem intermediate;// 中间代码菜单项
	private MenuItem destination;// 目标代码生成菜单项
	private MenuItem check;// 查看
	private MenuItem help;// 帮助菜单项

	private Menu fileMenu;// 文件菜单
	private MenuItem openItem;// 打开
	private MenuItem saveItem;// 保存
	private MenuItem saveAsItem;// 另存为
	private MenuItem exitItem;// 退出

	private Menu editMenu;// 编辑菜单
	private MenuItem copyItem;// 复制
	private MenuItem cutItem;// 剪切
	private MenuItem pasteItem;// 粘贴

	private Menu lexicalMenu;// 词法分析菜单
	private MenuItem lexicalItem;// 词法分析
	private MenuItem regularToNFAItem;// 正规式==>NFA
	private MenuItem NFAToDFAItem;// NFA==>DFA
	private MenuItem minDFAItem;// DFA最小化

	private Menu grammarMenu;// 语法分析菜单
	private MenuItem grammerItem;// 语法分析
	private MenuItem predictiveParsingItem;// LL(1)预测分析
	private MenuItem operaterItem;// 运算符优先
	private MenuItem lranalysis;// LR分析

	private Menu intermediateMenu;

	private Menu destinationMenu;

	private Menu checkMenu;// 查看菜单
	private MenuItem checkItem;// 查看

	private Menu helpMenu;// 帮助菜单
	private MenuItem aboutItem;// 关于

	public JomMenu(Shell shell, int style) {
		super(shell, style);
		createMenu(shell);
	}

	private void createMenu(Shell shell) {
		file(shell);// 文件菜单
		edit(shell);// 编辑菜单
		lexical(shell);// 词法分析菜单
		grammer(shell);// 语法分析菜单
		intermediate(shell);
		destination(shell);
		check(shell);// 查看菜单
		help(shell);// 帮助菜单
		addFileListener();
		addEditListener();
		addLexicalListener();
		addGrammerListener();
		addIntermediateListener();
		addDestinationListener();
		addCheckListener();
		addHelpListener();
	}

	private void file(Shell shell) {
		file = new MenuItem(this, SWT.CASCADE);
		file.setText("文件(&F)");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		file.setMenu(fileMenu);

		// File-->openItem
		openItem = new MenuItem(fileMenu, SWT.PUSH);
		openItem.setText("打开(&O)...\tCtrl+O");
		openItem.setAccelerator(SWT.CTRL + 'O');

		// File-->saveItem
		saveItem = new MenuItem(fileMenu, SWT.PUSH);
		saveItem.setText("保存(&S)\tCtrl+S");
		saveItem.setAccelerator(SWT.CTRL + 'S');

		// File-->saveAsItem
		saveAsItem = new MenuItem(fileMenu, SWT.PUSH);
		saveAsItem.setText("另存为(&A)...");

		// 分隔符
		createSeparator(fileMenu);

		// File-->exitItem
		exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("退出");
	}

	private void edit(Shell shell) {
		// Edit
		edit = new MenuItem(this, SWT.CASCADE);
		edit.setText("编辑(&E)");
		editMenu = new Menu(shell, SWT.DROP_DOWN);
		edit.setMenu(editMenu);

		// Edit-->copyItem
		copyItem = new MenuItem(editMenu, SWT.PUSH);
		copyItem.setText("复制(&C)\tCtrl+C");
		copyItem.setAccelerator(SWT.CTRL + 'C');

		// Edit-->cutItem
		cutItem = new MenuItem(editMenu, SWT.PUSH);
		cutItem.setText("剪切(&X)\tCtrl+X");
		cutItem.setAccelerator(SWT.CTRL + 'X');
		cutItem.setEnabled(false);

		// Edit-->pasteItem
		pasteItem = new MenuItem(editMenu, SWT.PUSH);
		pasteItem.setText("粘贴(&V)\tCtrl+V");
		pasteItem.setAccelerator(SWT.CTRL + 'V');
		pasteItem.setEnabled(false);
	}

	private void lexical(Shell shell) {
		lexical = new MenuItem(this, SWT.CASCADE);
		lexical.setText("词法分析(&W)");
		lexicalMenu = new Menu(shell, SWT.DROP_DOWN);
		lexical.setMenu(lexicalMenu);

		lexicalItem = new MenuItem(lexicalMenu, SWT.PUSH);
		lexicalItem.setText("词法分析(&W)\tCtrl+W");
		lexicalItem.setAccelerator(SWT.CTRL + 'W');
		lexicalItem.setEnabled(false);

		regularToNFAItem = new MenuItem(lexicalMenu, SWT.PUSH);
		regularToNFAItem.setText("正规式==>NFA");
		regularToNFAItem.setEnabled(false);

		NFAToDFAItem = new MenuItem(lexicalMenu, SWT.PUSH);
		NFAToDFAItem.setText("NFA==>DFA");
		NFAToDFAItem.setEnabled(false);

		minDFAItem = new MenuItem(lexicalMenu, SWT.PUSH);
		minDFAItem.setText("化简DFA");
		minDFAItem.setEnabled(false);
	}

	private void grammer(Shell shell) {
		grammar = new MenuItem(this, SWT.CASCADE);
		grammar.setText("语法分析(&G)");
		grammarMenu = new Menu(shell, SWT.DROP_DOWN);
		grammar.setMenu(grammarMenu);

		grammerItem = new MenuItem(grammarMenu, SWT.PUSH);
		grammerItem.setText("语法分析");
		grammerItem.setEnabled(false);

		predictiveParsingItem = new MenuItem(grammarMenu, SWT.PUSH);
		predictiveParsingItem.setText("LL预测分析");
		predictiveParsingItem.setEnabled(false);

		operaterItem = new MenuItem(grammarMenu, SWT.PUSH);
		operaterItem.setText("预算符优先");
		operaterItem.setEnabled(false);

		lranalysis = new MenuItem(grammarMenu, SWT.PUSH);
		lranalysis.setText("LR分析");
		lranalysis.setEnabled(false);
	}

	private void intermediate(Shell shell) {
		intermediate = new MenuItem(this, SWT.CASCADE);
		intermediate.setText("中间代码(&C)");
		intermediateMenu = new Menu(shell, SWT.DROP_DOWN);
		intermediate.setMenu(intermediateMenu);
	}

	private void destination(Shell shell) {
		destination = new MenuItem(this, SWT.CASCADE);
		destination.setText("目标代码生成(&C)");
		destinationMenu = new Menu(shell, SWT.DROP_DOWN);
		destination.setMenu(destinationMenu);
	}

	private void check(Shell shell) {
		check = new MenuItem(this, SWT.CASCADE);
		check.setText("查看(&C)");
		checkMenu = new Menu(shell, SWT.DROP_DOWN);
		check.setMenu(checkMenu);

		checkItem = new MenuItem(checkMenu, SWT.PUSH);
		checkItem.setText("查看");
	}

	private void help(Shell shell) {
		help = new MenuItem(this, SWT.CASCADE);
		help.setText("帮助(&H)");
		helpMenu = new Menu(shell, SWT.DROP_DOWN);
		help.setMenu(helpMenu);

		aboutItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutItem.setText("关于");
	}

	private MenuItem createSeparator(Menu m) {
		MenuItem Separator = new MenuItem(m, SWT.SEPARATOR);
		return Separator;
	}

	private void addFileListener() {
		openItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("open");
				String name = SWTUtil
						.openDialog(SWT.OPEN, new String[] { "*.jom;", "*.*" },// 打开书籍文件选择对话框
								new String[] { "jom源文件" + " (*.jom)",
										"所有文件" + " (*.*)" });
				if (name != null) {
					Shell currentShell = Display.getCurrent().getActiveShell();
					Cursor waitCursor = new Cursor(Display.getCurrent(),
							SWT.CURSOR_WAIT);
					currentShell.setCursor(waitCursor);// 设置鼠标忙

					currentShell.setText(currentShell.getText() + " --" + name);
					StringBuffer sb = new StringBuffer();
					for (String s : Source.getInstance(name).getSource()) {
						sb.append(s + "\n");
					}
					Window.highlightText.setText(sb.toString());
					lexicalItem.setEnabled(true);

					currentShell.setCursor(null);// 设置鼠标正常
					waitCursor.dispose();
				}
			}
		});
	}

	private void addEditListener() {
	}

	private void addLexicalListener() {
		lexicalItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("lexicalItem");
				Source.getInstance().sort();
				List<TokenMeta> tokenMeta = Token.getTokenTable();
				for (TokenMeta t : tokenMeta) {
					new TableItem(Window.token, SWT.CENTER)
							.setText(new String[] { t.getMeta(),
									t.getLine() + "", t.getPointer() + "" });
				}
				List<SymbolMeta> symbolMeta = Symbol.getInstance().getSymbol();
				for (SymbolMeta t : symbolMeta) {
					new TableItem(Window.symbol, SWT.CENTER)
							.setText(new String[] { t.getMeta(),
									t.getType() + "", t.getValue() + "",
									t.getPointer() + "", t.getKind() + "" });
				}
			}
		});
	}

	private void addGrammerListener() {
	}

	private void addIntermediateListener() {
	}

	private void addDestinationListener() {
	}

	private void addCheckListener() {
	}

	private void addHelpListener() {
	}

	@Override
	protected void checkSubclass() {
	}

}
