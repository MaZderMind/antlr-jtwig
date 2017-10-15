package de.mazdermind.playground.antlrtwig.parser.expression;

import static de.mazdermind.playground.antlrtwig.parser.expression.ParserHelper.parse;
import static de.mazdermind.playground.antlrtwig.parser.matcher.AntlrParseTreeMatcher.matchesParseTree;
import static org.hamcrest.MatcherAssert.assertThat;

public class Math {
	@org.junit.Test
	public void addition() {
		assertThat(
				parse("50+33+7"),
				matchesParseTree("MathOp{MathOp{Literal{50}+Literal{33}}+Literal{7}}"));
	}

	@org.junit.Test
	public void subtraction() {
		assertThat(
				parse("10-5"),
				matchesParseTree("MathOp{Literal{10}-Literal{5}}"));
	}

	@org.junit.Test
	public void multiplicaton() {
		assertThat(
				parse("3*7"),
				matchesParseTree("MathOp{Literal{3}*Literal{7}}"));
	}

	@org.junit.Test
	public void division() {
		assertThat(
				parse("99/9"),
				matchesParseTree("MathOp{Literal{99}/Literal{9}}"));
	}

	@org.junit.Test
	public void additionBeforeMultiplicaton() {
		assertThat(
				parse("5+99*9"),
				matchesParseTree("MathOp{Literal{5}+MathOp{Literal{99}*Literal{9}}}"));
	}

	@org.junit.Test
	public void additionAfterMultiplicaton() {
		assertThat(
				parse("99*9+5"),
				matchesParseTree("MathOp{MathOp{Literal{99}*Literal{9}}+Literal{5}}"));
	}

	@org.junit.Test
	public void additionAndMultiplicatonWithBraces() {
		assertThat(
				parse("99*(9+5)"),
				matchesParseTree("MathOp{Literal{99}*Group{(MathOp{Literal{9}+Literal{5}})}}"));
	}
}
