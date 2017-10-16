package de.mazdermind.playground.antlrtwig.evaluator.expression.visitor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mazdermind.playground.antlrtwig.evaluator.ContextualTwigListener;
import de.mazdermind.playground.antlrtwig.evaluator.ValueState;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

public class PropertyAccessVisitor extends ContextualTwigListener {
	private static Logger log = LoggerFactory.getLogger(PropertyAccessVisitor.class);

	@Override
	public void exitPropertyAccessExpression(TwigParser.PropertyAccessExpressionContext ctx) {
		String keyValue = (String) ctx.getChild(1).getText();
		Map mapValue = (Map) context.popState(ValueState.class).getValue();

		Object accessValue = mapValue.get(keyValue);
		log.debug("map access with key = {} on list {} = {}", keyValue, mapValue, accessValue);

		context.pushState(new ValueState(accessValue));
	}
}
