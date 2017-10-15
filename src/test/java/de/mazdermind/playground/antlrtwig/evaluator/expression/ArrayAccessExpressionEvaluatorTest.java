package de.mazdermind.playground.antlrtwig.evaluator.expression;

import static de.mazdermind.playground.antlrtwig.evaluator.expression.ExpressionEvaluatorHelper.evaluate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@RunWith(Parameterized.class)
public class ArrayAccessExpressionEvaluatorTest {
	private TestCase testCase;
	private ImmutableMap<String, Object> variables;

	public ArrayAccessExpressionEvaluatorTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameterized.Parameters(name = "{0}")
	public static Collection<TestCase> data() {
		return ImmutableList.<TestCase>builder()
				.add(new TestCase("list[0]", 1))
				.add(new TestCase("list[1]", 10))
				.add(new TestCase("list[2]", 100))
				.add(new TestCase("list[list[0]]", 10))

				.add(new TestCase("list[0] + list[1]", 11))
				.add(new TestCase("(list[1] + list[1]) * list[2]", 2000))

				.add(new TestCase("deeplist[0][0]", "A0"))
				.add(new TestCase("deeplist[1][0]", "A1"))
				.add(new TestCase("deeplist[0][1]", "B0"))
				.add(new TestCase("deeplist[1][1]", "B1"))

				.add(new TestCase("deeplist[list[0]][list[0]]", "B1"))

				.build();
	}

	@Before
	public void prepareTestdata() {
		variables = ImmutableMap.of(
				"list", ImmutableList.of(1, 10, 100),
				"deeplist", ImmutableList.of(
						ImmutableList.of("A0", "B0", "C0"),
						ImmutableList.of("A1", "B1", "C1"),
						ImmutableList.of("A2", "B2", "C2")
				)
		);
	}

	@Test
	public void matchExpression() {
		assertThat(evaluate(testCase.expression, variables), is(testCase.expectedResult));
	}

	private static class TestCase {
		private String expression;
		private Object expectedResult;

		TestCase(String expression, Object expectedResult) {
			this.expression = expression;
			this.expectedResult = expectedResult;
		}

		@Override
		public String toString() {
			return expression + " = " + expectedResult;
		}
	}

}
