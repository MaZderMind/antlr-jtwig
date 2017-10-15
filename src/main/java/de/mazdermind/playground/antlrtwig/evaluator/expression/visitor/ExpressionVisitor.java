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
		ValueState rhs = context.popState(ValueState.class);
		ValueState lhs = context.popState(ValueState.class);

		Integer lhsInt = (Integer) lhs.getValue();
		Integer rhsInt = (Integer) rhs.getValue();
		Integer result = null;

		switch (operation) {
			case "+":
				result = lhsInt + rhsInt;
				break;

			case "-":
				result = lhsInt - rhsInt;
				break;

			case "/":
				result = lhsInt / rhsInt;
				break;

			case "*":
				result = lhsInt * rhsInt;
				break;
		}

		log.debug("mathop: {} on lhs={} and rhs={} = {}", operation, lhs, rhs, result);

		context.pushState(new ValueState(result));
	}
}
