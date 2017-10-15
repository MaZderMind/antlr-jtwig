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
public class StringExpressionTest {

	private static class TestCase {
		private String expression;
		private String expectedResult;

		TestCase(String expression, String expectedResult) {
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
				.add(new TestCase("\"foo\"", "foo"))
				.add(new TestCase("\"9f00\"", "9f00"))
				.add(new TestCase("\"foo \uD83D\uDE00 bar\"", "foo \uD83D\uDE00 bar"))
				.add(new TestCase("\"f'oo'oo\"", "f'oo'oo"))
				.add(new TestCase("\"f\\\"oo\\\"oo\"", "f\"oo\"oo"))

				.add(new TestCase("'foo'", "foo"))
				.add(new TestCase("'9f00'", "9f00"))
				.add(new TestCase("'foo \uD83D\uDE00 bar'", "foo \uD83D\uDE00 bar"))
				.add(new TestCase("'f\"oo\"oo'", "f\"oo\"oo"))
				.add(new TestCase("'f\\'oo\\'oo'", "f'oo'oo"))
				.build();
	}

	private TestCase testCase;

	public StringExpressionTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Test
	public void matchExpression() {
		assertThat(evaluate(testCase.expression), is(testCase.expectedResult));
	}
}
