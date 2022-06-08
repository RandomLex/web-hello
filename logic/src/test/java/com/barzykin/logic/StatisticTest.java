package com.barzykin.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticTest {
    Statistic statistic;

    @Before
    public void setUp() throws Exception {
        statistic = new Statistic();
    }

    @Test
    public void averageOfTwoExpectedInteger() {
        //дано given
        List<Integer> integers = List.of(6, 8);
        BigDecimal expectedAverage = new BigDecimal("7.00");

        //выполнение when
        BigDecimal actualAverage = statistic.average(integers);

        //тогда, результат then
        assertEquals(expectedAverage, actualAverage);
    }

    @Test
    public void averageOfTwoExpectedDecimal() {
        //дано given
        List<Integer> integers = List.of(6, 7);
        BigDecimal expectedAverage = new BigDecimal("6.50");

        //выполнение when
        BigDecimal actualAverage = statistic.average(integers);

        //тогда, результат then
        assertEquals(expectedAverage, actualAverage);
    }
}