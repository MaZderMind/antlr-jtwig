package de.mazdermind.playground.antlrtwig.evaluator.expression.visitor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mazdermind.playground.antlrtwig.evaluator.ContextualTwigListener;
import de.mazdermind.playground.antlrtwig.evaluator.ValueState;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

public class ArrayAccessVisitor extends ContextualTwigListener {
	private static Logger log = LoggerFactory.getLogger(ArrayAccessVisitor.class);

	@Override
	public void exitArrayAccessExpression(TwigParser.ArrayAccessExpressionContext ctx) {
		Integer keyValue = (Integer) context.popState(ValueState.class).getValue();
		List listValue = (List) context.popState(ValueState.class).getValue();

		Object accessValue = listValue.get(keyValue);
		log.debug("array access with key = {} on list {} = {}", keyValue, listValue, accessValue);

		context.pushState(new ValueState(accessValue));
	}
}
