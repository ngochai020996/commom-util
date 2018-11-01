package com.axonactive.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author hntran
 *
 */
public class GenericBuilder<T> {

	private final Supplier<T> instantiator;
	
	private List<Consumer<T>> instanceModifiers = new ArrayList<>();
	
	public GenericBuilder(Supplier<T> instanstiator) {
		this.instantiator = instanstiator;
	}
	
	public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<>(instantiator);
    }
	
	public <U> GenericBuilder<T> with(BiConsumer<T, U> consumer, U value) {
        Consumer<T> c = instance -> consumer.accept(instance, value);
        instanceModifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        instanceModifiers.forEach(modifier -> modifier.accept(value));
        instanceModifiers.clear();
        return value;
    }

}