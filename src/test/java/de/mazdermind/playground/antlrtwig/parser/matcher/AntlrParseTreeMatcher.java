package de.mazdermind.playground.antlrtwig.parser.matcher;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class AntlrParseTreeMatcher extends TypeSafeMatcher<ParseTree> {

	private String expectedParseTreeString;

	private AntlrParseTreeMatcher(String parseTreeString) {
		expectedParseTreeString = parseTreeString;
	}

	public static AntlrParseTreeMatcher matchesParseTree(String parseTreeString) {
		return new AntlrParseTreeMatcher(parseTreeString);
	}

	protected boolean matchesSafely(ParseTree parseTree) {
		String parseTreeString = parseTreeToString(parseTree);
		return parseTreeString.equals(expectedParseTreeString);
	}

	@Override
	protected void describeMismatchSafely(ParseTree parseTree, Description description) {
		description.appendText("was ");
		description.appendText(parseTreeToString(parseTree));
	}

	public void describeTo(Description description) {
		description.appendText("to match parse-tree ");
		description.appendText(expectedParseTreeString);
	}

	private String parseTreeToString(ParseTree parseTree) {
		StringBuilder s = new StringBuilder();

		String contextName = parseTree.getClass().getSimpleName();
		String contextShortName = contextName.substring(0, contextName.length() - "Context".length());

		s.append(contextShortName);
		s.append("{");
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			if (child instanceof TerminalNode) {
				s.append(child.getText());
			} else {
				s.append(parseTreeToString(child));
			}
		}
		s.append("}");

		return s.toString();
	}
}
