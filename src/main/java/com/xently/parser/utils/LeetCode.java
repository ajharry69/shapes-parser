package com.xently.parser.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCode {
    public static long carPackingRoof(List<Long> cars, int k) {
        Collections.sort(cars);
        int numberOfCars = cars.size();
        return Math.min(cars.get(k - 1) - cars.get(0), cars.get(numberOfCars - 1) - cars.get(numberOfCars - k)) + 1;
    }

    public static void main(String[] args) {
        System.out.println(LeetCode.carPackingRoof(Arrays.asList(1L, 2L, 3L, 10L), 4));
    }
}
