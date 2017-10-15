package de.mazdermind.playground.antlrtwig.evaluator.expression;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import de.mazdermind.playground.antlrtwig.grammar.TwigLexer;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

class ExpressionEvaluatorHelper {
	static Object evaluate(String expression) {
		TwigLexer lexer = new TwigLexer(CharStreams.fromString(expression));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TwigParser parser = new TwigParser(tokens);

		ExpressionEvaluator evaluator = new ExpressionEvaluator(parser);

		return evaluator.evaluate();
	}
}
