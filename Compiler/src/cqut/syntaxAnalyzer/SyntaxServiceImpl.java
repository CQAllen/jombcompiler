package cqut.syntaxAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cqut.util.SystemProperty;
import cqut.util.entity.Syntax;

public class SyntaxServiceImpl implements SyntaxService {

	public static Map<String, String> syntaxes;

	private static SyntaxService syntaxService;

	public static SyntaxService getInstance() {
		if (syntaxes == null) {
			syntaxes = SystemProperty.readProperties("src/syntax.properties");
			syntaxService = new SyntaxServiceImpl();
		}
		return syntaxService;
	}

	@Override
	public int size() {
		return syntaxes.size();
	}

	@Override
	public Syntax getSyntax(String starting) {
		Syntax syntax = new Syntax();
		syntax.setStarting(starting);
		List<String> pros = new ArrayList<String>();
		String[] str = syntaxes.get(starting).split("|");
		for (String s : str) {
			pros.add(s);
		}
		syntax.setProduction(pros);
		return syntax;
	}

	@Override
	public List<String> getNonTerminalStarting(String starting) {
		return null;
	}

	@Override
	public List<String> getNonTerminalStarting(String starting, int index) {
		return null;
	}

	@Override
	public List<String> getTerminalSymbol(String starting) {
		return null;
	}

	@Override
	public List<Character> getTerminalSymbol(String starting, int index) {
		return null;
	}

	@Override
	public List<Syntax> getSyntax() {
		return null;
	}

}
