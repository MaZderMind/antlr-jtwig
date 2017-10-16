package de.mazdermind.playground.antlrtwig.evaluator;

import java.util.List;

import org.antlr.v4.runtime.Parser;

import com.google.common.collect.ImmutableList;

import de.mazdermind.playground.antlrtwig.evaluator.expression.visitor.ArrayAccessVisitor;
import de.mazdermind.playground.antlrtwig.evaluator.expression.visitor.ExpressionVisitor;
import de.mazdermind.playground.antlrtwig.evaluator.expression.visitor.LiteralVisitor;
import de.mazdermind.playground.antlrtwig.evaluator.expression.visitor.PropertyAccessVisitor;
import de.mazdermind.playground.antlrtwig.evaluator.expression.visitor.VariableVisitor;

public class VisitorConfigurator {
	private static List<Class<? extends ContextualTwigListener>> listenerClasses = ImmutableList.<Class<? extends ContextualTwigListener>>builder()
			.add(ExpressionVisitor.class)
			.add(LiteralVisitor.class)
			.add(VariableVisitor.class)
			.add(ArrayAccessVisitor.class)
			.add(PropertyAccessVisitor.class)
			.build();

	public static void configureVisitor(final Parser parser, EvaluationContext context) {
		listenerClasses.forEach(clazz -> {
			try {
				ContextualTwigListener listener = clazz.newInstance().setContext(context);
				parser.addParseListener(listener);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}
}
