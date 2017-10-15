package de.mazdermind.playground.antlrtwig.evaluator.expression.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mazdermind.playground.antlrtwig.evaluator.ContextualTwigListener;
import de.mazdermind.playground.antlrtwig.evaluator.ValueState;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

public class ExpressionVisitor extends ContextualTwigListener {
	private static Logger log = LoggerFactory.getLogger(ExpressionVisitor.class);

	@Override
	public void exitMathOp(TwigParser.MathOpContext ctx){
		String operation = ctx.getChild(1).getText();

		Integer rightHand = (Integer) context.popState(ValueState.class).getValue();
		Integer leftHand = (Integer) context.popState(ValueState.class).getValue();
		Integer result = null;

		switch (operation) {
			case "+":
				result = leftHand + rightHand;
				break;

			case "-":
				result = leftHand - rightHand;
				break;

			case "/":
				result = leftHand / rightHand;
				break;

			case "*":
				result = leftHand * rightHand;
				break;
		}

		log.debug("mathop: {} on lhs={} and rhs={} = {}", operation, leftHand, rightHand, result);

		context.pushState(new ValueState(result));
	}
}
