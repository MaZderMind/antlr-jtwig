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

		if(literalExpression.INT() != null) {
			Integer integer = Integer.valueOf(literalExpression.INT().getText());
			log.debug("literal INT: {}", integer);
			context.pushState(new ValueState(integer));
		}
		else if(literalExpression.STRING() != null) {
			String string = literalExpression.STRING().getText();
			log.debug("literal STRING: {}", string);
			context.pushState(new ValueState(string));
		}
	}
}
