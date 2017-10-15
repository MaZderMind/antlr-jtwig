package de.mazdermind.playground.antlrtwig.evaluator.expression.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mazdermind.playground.antlrtwig.evaluator.ContextualTwigListener;
import de.mazdermind.playground.antlrtwig.evaluator.ValueState;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

public class VariableVisitor extends ContextualTwigListener {
	private static Logger log = LoggerFactory.getLogger(VariableVisitor.class);

	@Override
	public void exitVariableExpression(TwigParser.VariableExpressionContext ctx) {
		String variableName = ctx.VARIABLE().getText();
		Object variableValue = context.getVariable(variableName);

		log.debug("variable: {}", variableName);
		context.pushState(new ValueState(variableValue));
	}
}
