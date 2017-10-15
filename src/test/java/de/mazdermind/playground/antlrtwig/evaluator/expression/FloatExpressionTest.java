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
public class FloatExpressionTest {

	private static class TestCase {
		private String expression;
		private double expectedResult;

		TestCase(String expression, double expectedResult) {
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
				.add(new TestCase("1.5", 1.5))
				.add(new TestCase("0.0", 0.0))
				.add(new TestCase("-5.5",-5.5))
				.add(new TestCase("3.14159",3.14159))

				.add(new TestCase("-999999999999999999.0", -999999999999999999.0))
				.add(new TestCase("999999999999999999.0", 999999999999999999.0))

				.build();
	}

	private TestCase testCase;

	public FloatExpressionTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Test
	public void matchExpression() {
		assertThat(evaluate(testCase.expression), is(testCase.expectedResult));
	}
}
