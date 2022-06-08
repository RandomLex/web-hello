package com.barzykin.logic;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class StatisticTest {
    Statistic statistic;

    @BeforeEach
    public void setUp() {
        statistic = new Statistic();
    }

    @ParameterizedTest
    @MethodSource("intIntBigDecimal")
    void average(int a, int b, BigDecimal expectedAverage) {
        List<Integer> integers = List.of(a, b);

        //выполнение when
        BigDecimal actualAverage = statistic.average(integers);

        //тогда, результат then
        assertEquals(expectedAverage, actualAverage);
    }

    public static Stream<Arguments> intIntBigDecimal() {
        return Stream.of(
                arguments(6, 8, new BigDecimal("7.00")),
                arguments(6, 7, new BigDecimal("6.50"))
        );
    }

}