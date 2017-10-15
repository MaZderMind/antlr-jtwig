package de.mazdermind.playground.antlrtwig.parser.expression;

import static de.mazdermind.playground.antlrtwig.parser.expression.ExpressionParserHelper.parse;
import static de.mazdermind.playground.antlrtwig.parser.matcher.AntlrParseTreeMatcher.matchesParseTree;
import static org.hamcrest.MatcherAssert.assertThat;

public class MathExpressionParserTest {
	@org.junit.Test
	public void addition() {
		assertThat(
				parse("50+33+7"),
				matchesParseTree("MathOp{MathOp{Literal{LiteralExpression{50}}+Literal{LiteralExpression{33}}}+Literal{LiteralExpression{7}}}"));
	}

	@org.junit.Test
	public void subtraction() {
		assertThat(
				parse("10-5"),
				matchesParseTree("MathOp{Literal{LiteralExpression{10}}-Literal{LiteralExpression{5}}}"));
	}

	@org.junit.Test
	public void multiplicaton() {
		assertThat(
				parse("3*7"),
				matchesParseTree("MathOp{Literal{LiteralExpression{3}}*Literal{LiteralExpression{7}}}"));
	}

	@org.junit.Test
	public void division() {
		assertThat(
				parse("99/9"),
				matchesParseTree("MathOp{Literal{LiteralExpression{99}}/Literal{LiteralExpression{9}}}"));
	}

	@org.junit.Test
	public void additionBeforeMultiplicaton() {
		assertThat(
				parse("5+99*9"),
				matchesParseTree("MathOp{Literal{LiteralExpression{5}}+MathOp{Literal{LiteralExpression{99}}*Literal{LiteralExpression{9}}}}"));
	}

	@org.junit.Test
	public void additionAfterMultiplicaton() {
		assertThat(
				parse("99*9+5"),
				matchesParseTree("MathOp{MathOp{Literal{LiteralExpression{99}}*Literal{LiteralExpression{9}}}+Literal{LiteralExpression{5}}}"));
	}

	@org.junit.Test
	public void additionAndMultiplicatonWithBraces() {
		assertThat(
				parse("99*(9+5)"),
				matchesParseTree("MathOp{Literal{LiteralExpression{99}}*Group{(MathOp{Literal{LiteralExpression{9}}+Literal{LiteralExpression{5}}})}}"));
	}
}
