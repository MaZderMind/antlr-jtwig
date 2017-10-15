package de.mazdermind.playground.antlrtwig.evaluator.expression;

import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.google.common.collect.ImmutableMap;

import de.mazdermind.playground.antlrtwig.grammar.TwigLexer;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

class ExpressionEvaluatorHelper {
	static Object evaluate(String expression) {
		return evaluate(expression, ImmutableMap.of());
	}

	static Object evaluate(String expression, Map<String, Object> variables) {
		TwigLexer lexer = new TwigLexer(CharStreams.fromString(expression));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TwigParser parser = new TwigParser(tokens);

		ExpressionEvaluator evaluator = new ExpressionEvaluator(parser);
		evaluator.getContext().addVariables(variables);

		return evaluator.evaluate();
	}
}
