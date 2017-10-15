package de.mazdermind.playground.antlrtwig.evaluator.expression;

import static de.mazdermind.playground.antlrtwig.evaluator.expression.ExpressionEvaluatorHelper.evaluate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class MathExpressionEvaluatorTest {
	@Test
	public void addition() {
		assertThat(evaluate("55+3+7"), is(65));
	}

	@Test
	public void additionWithWhitespace() {
		assertThat(evaluate("  55\n+\t5"), is(60));
	}

	@Test
	public void subtraction() {
		assertThat(evaluate("55-5-50"), is(0));
	}

	@Test
	public void additionAndSubtraction() {
		assertThat(evaluate("55+10-105"), is(-40));
	}

	@Test
	public void multiplication() {
		assertThat(evaluate("3*50*2"), is(300));
	}

	@Test
	public void division() {
		assertThat(evaluate("33/11"), is(3));
	}

	@Test
	public void multiplicationAndDivision() {
		assertThat(evaluate("8*5/10"), is(4));
	}

	@Test
	public void additionBeforeMultiplication() {
		assertThat(evaluate("5+10*3"), is(35));
	}

	@Test
	public void additionAfterMultiplication() {
		assertThat(evaluate("10*3+5"), is(35));
	}

	@Test
	public void additionBeforeMultiplicationWithBrackets() {
		assertThat(evaluate("(3+5)*10"), is(80));
	}

	@Test
	public void additionAfterMultiplicationBrackets() {
		assertThat(evaluate("10*(3+5)"), is(80));
	}

	@Test
	public void subtractionBeforeDivision() {
		assertThat(evaluate("5-10/5"), is(3));
	}

	@Test
	public void subtractionAfterDivision() {
		assertThat(evaluate("10/5-5"), is(-3));
	}

	@Test
	public void subtractionBeforeDivisionWithBrackets() {
		assertThat(evaluate("(10-5)/5"), is(1));
	}

	@Test
	public void subtractionAfterDivisionBrackets() {
		assertThat(evaluate("10/(10-5)"), is(2));
	}
}
