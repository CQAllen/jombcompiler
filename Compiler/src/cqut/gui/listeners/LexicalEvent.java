package cqut.gui.listeners;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import cqut.gui.Window;
import cqut.util.Symbol;
import cqut.util.Token;
import cqut.util.entity.ErrorFacade;
import cqut.util.entity.Error;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
import cqut.util.entity.TokenMeta;

public class LexicalEvent implements LexicalListener {

	@Override
	public void lexical() {
		Window.symbol.removeAll();
		Window.token.removeAll();
		Window.problems.removeAll();
		Token.getInstance().clear();
		Symbol.getInstance().clear();
		ErrorFacade.getInstance().clear();
		Source.getInstance().sort();
		List<TokenMeta> tokenMeta = Token.getInstance().getAllMeta();
		for (TokenMeta t : tokenMeta) {
			new TableItem(Window.token, SWT.CENTER).setText(new String[] {
					t.getMeta(), (t.getLine() + 1) + "", t.getPointer() + "" });
		}
		List<SymbolMeta> symbolMeta = Symbol.getInstance().getSymbol();
		for (SymbolMeta t : symbolMeta) {
			new TableItem(Window.symbol, SWT.CENTER).setText(new String[] {
					t.getMeta(), t.getType() + "", t.getValue() + "",
					t.getPointer() + "", t.getKind() + "" });
		}
		List<Error> errors = ErrorFacade.getInstance().getErrors();
		for (Error e : errors) {
			new TableItem(Window.problems, SWT.CENTER).setText(new String[] {
					e.getDescription(), e.getResource(), e.getPath(),
					"line "+e.getLocation(), e.getType() });
		}
	}

}
