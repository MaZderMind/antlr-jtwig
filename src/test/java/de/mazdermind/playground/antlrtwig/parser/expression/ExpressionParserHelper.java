package de.mazdermind.playground.antlrtwig.parser.expression;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import de.mazdermind.playground.antlrtwig.grammar.TwigLexer;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

class ExpressionParserHelper {
	static TwigParser.ExpressionContext parse(String s) {
		TwigLexer lexer = new TwigLexer(CharStreams.fromString(s));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TwigParser parser = new TwigParser(tokens);
		return parser.expression();
	}
}
