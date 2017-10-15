package de.mazdermind.playground.antlrtwig.evaluator.expression;

import static de.mazdermind.playground.antlrtwig.evaluator.expression.ExpressionEvaluatorHelper.evaluate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class VariableExpressionEvaluatorTest {
	@Test
	public void variableAccess() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("a", 5);
		assertThat(evaluate("a", variables), is(5));
	}

	@Test
	public void variableAccessInMultiplication() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("a", 5);
		assertThat(evaluate("5*a", variables), is(25));
	}

	@Test
	public void variableAccessInAddition() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("a", 5);
		assertThat(evaluate("5+(a+a+10)", variables), is(25));
	}

	@Test
	public void variableNamesWithLongName() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("testvariable", 6);
		assertThat(evaluate("5*testvariable", variables), is(30));
	}

	@Test
	public void variableNamesWithCamelCase() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("testVariable", 7);
		assertThat(evaluate("5*testVariable", variables), is(35));
	}

	@Test
	public void variableNamesWithUnderscoreCase() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("test_variable", 8);
		assertThat(evaluate("5*test_variable", variables), is(40));
	}

	@Test
	public void variableNamesWithUnderscoreUpperCase() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("TEST_VARIABLE", 9);
		assertThat(evaluate("5*TEST_VARIABLE", variables), is(45));
	}

	@Test
	public void variableNamesWithUpperCase() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("TESTVARIABLE", 10);
		assertThat(evaluate("5*TESTVARIABLE", variables), is(50));
	}

	@Test
	public void variableNamesWithNumbers() {
		ImmutableMap<String, Object> variables = ImmutableMap.of("test5variable99", 11);
		assertThat(evaluate("5*test5variable99", variables), is(55));
	}
}
