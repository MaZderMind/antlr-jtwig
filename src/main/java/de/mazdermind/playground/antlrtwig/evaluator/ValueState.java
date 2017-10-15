package de.mazdermind.playground.antlrtwig.evaluator;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ValueState implements ContextState {
	private Object value;

	public ValueState(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public ValueState setValue(Object value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
