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
public class IntegerMathExpressionEvaluatorTest {

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
				.add(new TestCase("55+3+7", 65))
				.add(new TestCase("55-5-50", 0))
				.add(new TestCase("55+10-105",-40))

				.add(new TestCase("3*50*2", 300))
				.add(new TestCase("33/11", 3))
				.add(new TestCase("8*5/10", 4))

				.add(new TestCase("  55\n+\t5", 60))

				.add(new TestCase("5+10*3", 35))
				.add(new TestCase("10*3+5", 35))
				.add(new TestCase("(3+5)*10", 80))
				.add(new TestCase("10*(3+5)", 80))

				.add(new TestCase("5-10*3", -25))
				.add(new TestCase("10*3-5", 25))
				.add(new TestCase("(3-5)*10", -20))
				.add(new TestCase("10*(3-5)", -20))


				.add(new TestCase("5+10/2", 10))
				.add(new TestCase("10/2+5", 10))
				.add(new TestCase("(30+50)/10", 8))
				.add(new TestCase("10/(3+2)", 2))

				.add(new TestCase("5-10/2", 0))
				.add(new TestCase("10/2-5", 0))
				.add(new TestCase("(30-10)/10", 2))
				.add(new TestCase("10/(5-3)", 5))

				.build();
	}

	private TestCase testCase;

	public IntegerMathExpressionEvaluatorTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Test
	public void matchExpression() {
		assertThat(evaluate(testCase.expression), is(testCase.expectedResult));
	}
}
