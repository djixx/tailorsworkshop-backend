package com.eonis.demo.core.mapper;

import java.util.List;
import static java.util.Objects.nonNull;

public interface Mapper<I, R> {
    /**
     * Maps input of type {@link I} to the result of type {@link R}
     *
     * @param input Input to map
     * @return Result of mapping
     */
    R map(I input);

    /**
     * Maps input type of {@link List<I>} to the result of type {@link List<R>}
     *
     * @param input List to map
     * @return List of results
     */
    default List<R> map(List<I> input) {
        return nonNull(input)
                ? input.stream().map(this::map).toList()
                : null;
    }

    /**
     * Maps input of type {@link I} to the result of type {@link R}, or to default value if input is null
     *
     * @param input        Input to map
     * @param defaultValue Value to return if input is null
     * @return Result of mapping
     */
    default R mapOrDefault(I input, R defaultValue) {
        return nonNull(input) ? this.map(input) : defaultValue;
    }

    /**
     * Maps input list of type {@link List<I>} to the result list of type {@link List<R>}, or to default value if input is null
     *
     * @param input        List to map
     * @param defaultValue Value to return if input list is null
     * @return Result list of mapping
     */
    default List<R> mapOrDefault(List<I> input, List<R> defaultValue) {
        return nonNull(input) ? this.map(input) : defaultValue;
    }

}
