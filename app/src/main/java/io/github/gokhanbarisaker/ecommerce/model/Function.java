package io.github.gokhanbarisaker.ecommerce.model;

/**
 * Created by gokhanbarisaker on 1/24/16.
 * <p>
 * Represents a function that accepts one argument and produces a result.
 * <p>
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * backport clone whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public interface Function<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}
