package de.mazdermind.playground.antlrtwig.evaluator.expression;

import static de.mazdermind.playground.antlrtwig.evaluator.expression.ExpressionEvaluatorHelper.evaluate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@RunWith(Parameterized.class)
public class PropertyAccessExpressionEvaluatorTest {
	private TestCase testCase;
	private ImmutableMap<String, Object> variables;

	public PropertyAccessExpressionEvaluatorTest(TestCase testCase) {
		this.testCase = testCase;
	}

	@Parameterized.Parameters(name = "{0}")
	public static Collection<TestCase> data() {
		return ImmutableList.<TestCase>builder()
				.add(new TestCase("map.text", "foo"))
				.add(new TestCase("map.number", 42))
				.add(new TestCase("map.object.text", "foo"))
				.add(new TestCase("map.object.number", 42))

				.add(new TestCase("map.list[0]", "foo"))
				.add(new TestCase("map.list[1].text", "foo"))
				.add(new TestCase("map.list[1].number", 42))

				.add(new TestCase("map.list[map.one].number", 42))

				.build();
	}

	@Before
	public void prepareTestdata() {
		variables = ImmutableMap.of(
				"map", ImmutableMap.of(
						"text", "foo",
						"number", 42,
						"one", 1,
						"object", ImmutableMap.of(
								"text", "foo",
								"number", 42
						),
						"list", ImmutableList.of(
								"foo",
								ImmutableMap.of(
										"text", "foo",
										"number", 42
								)
						)
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
