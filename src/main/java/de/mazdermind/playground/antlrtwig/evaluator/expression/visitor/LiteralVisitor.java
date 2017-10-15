package de.mazdermind.playground.antlrtwig.evaluator.expression.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mazdermind.playground.antlrtwig.evaluator.ContextualTwigListener;
import de.mazdermind.playground.antlrtwig.evaluator.ValueState;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

public class LiteralVisitor extends ContextualTwigListener {
	private static Logger log = LoggerFactory.getLogger(LiteralVisitor.class);

	@Override
	public void exitLiteral(TwigParser.LiteralContext ctx) {
		TwigParser.LiteralExpressionContext literalExpression = ctx.literalExpression();

		if(literalExpression.literalInteger() != null) {
			Integer integer = Integer.valueOf(literalExpression.literalInteger().getText());
			log.debug("literal INT: {}", integer);
			context.pushState(new ValueState(integer));
		}
		else if(literalExpression.literalFloat() != null) {
			Double number = Double.valueOf(literalExpression.literalFloat().getText());
			log.debug("literal FLOAT: {}", number);
			context.pushState(new ValueState(number));
		}
		else if(literalExpression.literalString() != null) {
			String string = literalExpression.literalString().STRING().getText();
			String quoteChar = string.substring(0, 1);

			string = string.substring(1, string.length()-1);
			string = string.replace("\\"+quoteChar, quoteChar);

			log.debug("literal STRING: {}", string);
			context.pushState(new ValueState(string));
		}
	}
}
