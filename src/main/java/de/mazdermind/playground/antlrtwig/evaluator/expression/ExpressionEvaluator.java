package de.mazdermind.playground.antlrtwig.evaluator.expression;

import de.mazdermind.playground.antlrtwig.evaluator.EvaluationContext;
import de.mazdermind.playground.antlrtwig.evaluator.ValueState;
import de.mazdermind.playground.antlrtwig.evaluator.VisitorConfigurator;
import de.mazdermind.playground.antlrtwig.grammar.TwigParser;

public class ExpressionEvaluator {
	private EvaluationContext context = new EvaluationContext();
	private TwigParser parser;

	public ExpressionEvaluator(TwigParser parser) {
		this.parser = parser;
		VisitorConfigurator.configureVisitor(parser, context);
	}

	public Object evaluate() {
		parser.expression();
		return context.popState(ValueState.class).getValue();
	}

	public EvaluationContext getContext() {
		return context;
	}
}
