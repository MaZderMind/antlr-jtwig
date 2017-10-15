package de.mazdermind.playground.antlrtwig.evaluator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EvaluationContext {
	private Map<Class<?>, LinkedList<Object>> stacks = new HashMap<>();
	private Map<String, Object> variables = new HashMap<>();

	public <T> T pushState(T state) {
		ensureStackExists(state.getClass());
		stacks.get(state.getClass()).push(state);
		return state;
	}

	private void ensureStackExists(Class<?> type) {
		if (!stacks.containsKey(type)) {
			stacks.put(type, new LinkedList<>());
		}
	}

	public <T> T peekState(Class<T> type) {
		return type.cast(stacks.get(type).peek());
	}

	public <T> T getState(Class<T> type, int depth) {
		return type.cast(stacks.get(type).get(depth));
	}

	public <T> T popState(Class<T> type) {
		return type.cast(stacks.get(type).pop());
	}

	public EvaluationContext addVariables(Map<String, Object> variables) {
		variables.forEach(this::addVariable);
		return this;
	}

	public EvaluationContext addVariable(String key, Object value) {
		variables.put(key, value);
		return this;
	}

	public Object getVariable(String key) {
		return variables.get(key);
	}
}
