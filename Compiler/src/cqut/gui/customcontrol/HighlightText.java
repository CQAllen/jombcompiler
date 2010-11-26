package cqut.gui.customcontrol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import cqut.gui.listeners.FileEvent;
import cqut.gui.util.Util;
import cqut.util.EncodeTable;
import cqut.util.Token;

/**
 * 简单实现语法高亮的文本框,少量BUG,但是够这个项目用鸟
 * 
 * @author Cheng
 * 
 */
public class HighlightText extends StyledText {

	private static StyledText styledText;
	static TreeMap map = new TreeMap();
	private Color red;
	public static String[] keys;

	public HighlightText(Composite parent, int style) {
		super(parent, style);
		red = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		styledText = this;
		keys = EncodeTable.findCharactersByType(Token.ENCODETYPE_KEYWORD)
				.split(" ");
		addListener();
	}

	boolean a = true;

	private void addListener() {
		this.addExtendedModifyListener(new ExtendedModifyListener() {
			public void modifyText(ExtendedModifyEvent event) {
				map.clear();
				FileEvent.isModify = true;
				if (a) {
					String s = Display.getCurrent().getActiveShell().getText();
					s = "*" + s;
					Display.getCurrent().getActiveShell().setText(s);
					s = null;
					a = false;
				}
				int end = event.start + event.length - 1;
				List<StyleRange> ranges = new ArrayList<StyleRange>();
				if (event.start <= end) {
					String text = styledText.getText(event.start, end);
					if (text.equals(" ")) {// 手工录入代码
						int start = event.start >= 5 ? event.start - 5 : 0;
						String content = styledText.getText(start, end);
						for (String key : keys) {
							int index = content.indexOf(key);
							if (index > -1) {
								ranges.add(new StyleRange(start + index, key
										.length(), red, null, SWT.BOLD));
							}
						}
					} else if (text.length() > 1) {// 复制粘贴或者文件导入代码
						for (String key : keys) {
							Integer[] indexes = Util.search(0, text, key);
							if (indexes != null) {
								for (int index : indexes) {
									map.put(index, key.length());
								}
								Util.clear();
							}
						}
						Iterator iter = map.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry entry = (Map.Entry) iter.next();
							int key = (Integer) entry.getKey();
							int val = (Integer) entry.getValue();
							ranges.add(new StyleRange(key + event.start, val,
									red, null, SWT.BOLD));
						}
					}
					if (!ranges.isEmpty()) {
						styledText
								.replaceStyleRanges(event.start, event.length,
										(StyleRange[]) ranges
												.toArray(new StyleRange[ranges
														.size()]));
					}
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
	}

}
