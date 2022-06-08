package com.barzykin.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Statistic {

    public BigDecimal average(List<Integer> integers) {
        double averageDouble = integers.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0.0);
        return BigDecimal.valueOf(averageDouble).setScale(2, RoundingMode.HALF_UP);
    }
}
