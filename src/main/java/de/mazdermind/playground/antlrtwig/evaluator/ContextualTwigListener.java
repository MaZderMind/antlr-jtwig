package de.mazdermind.playground.antlrtwig.evaluator;

import de.mazdermind.playground.antlrtwig.grammar.TwigBaseListener;

public class ContextualTwigListener extends TwigBaseListener {
	protected EvaluationContext context;

	public ContextualTwigListener setContext(EvaluationContext context) {
		this.context = context;
		return this;
	}
}
