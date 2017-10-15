package de.mazdermind.playground.antlrtwig.evaluator.expression;

import static de.mazdermind.playground.antlrtwig.evaluator.expression.ExpressionEvaluatorHelper.evaluate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.common.collect.ImmutableList;

@RunWith(Parameterized.class)
public class IntegerExpressionTest {

	private static class TestCase {
		private String expression;
		private int expectedResult;

		TestCase(String expression, int expectedResult) {
			this.expression = expression;
			this.expectedResult = expectedResult;
		}

		@Override
		public String toString() {
			return expression + " = " + expectedResult;
		}
	}

	@Parameterized.Parameters(name = "{0}")
	public static Collection<TestCase> data() {
		return ImmutableList.<TestCase>builder()
				.add(new TestCase("55", 55))
				.add(new TestCase("0", 0))
				.add(new TestCase("-10",-10))

				.add(new TestCase("2147483647", 2147483647))
				.add(new TestCase("-2147483648", -2147483648))

				.build();
	}

	private TestCase testCase;

	public IntegerExpressionTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Test
	public void matchExpression() {
		assertThat(evaluate(testCase.expression), is(testCase.expectedResult));
	}
}
