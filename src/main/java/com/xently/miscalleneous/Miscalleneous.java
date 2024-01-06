package com.xently.miscalleneous;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserStats {
    private Long visitCount;

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Optional<Long> getVisitCount() {
        return Optional.of(visitCount);
    }
}

public class Miscalleneous {
    public static void main(String[] args) {
        long[] numbers = new long[]{1, 12, 123, 1000, 5821, 10500, 101800, 2000000, 7800000, 92150000, 123200000, 9999999};
        for (long n : numbers) {
            System.out.println(n + " => " + coolFormat(n, 0));
        }
    }

//    private static final char[] c = new char[]{'k', 'm', 'b', 't'};

    /**
     * Recursive implementation, invokes itself for each factor of a thousand, increasing the class on each invokation.
     *
     * @param n         the number to format
     * @param iteration in fact this is the class from the array c
     * @return a String representing the number n formatted in a cool looking way.
     */
    private static String coolFormat(double n, int iteration) {
        if (n < 1000) return String.valueOf(n)
                .replace(".0", "");
        double d = ((long) n / 100) / 10.0;
        boolean isRound = (d * 10) % 10 == 0;//true if the decimal part is equal to 0 (then it's trimmed anyway)
        //this determines the class, i.e. 'k', 'm' etc
        //this decides whether to trim the decimals
        // (int) d * 10 / 10 drops the decimal
        return d < 1000 ? //this determines the class, i.e. 'k', 'm' etc
                (isRound || d > 9.99 ? //this decides whether to trim the decimals
                        (int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops the decimal
                ) + "" + new char[]{'k', 'm', 'b', 't'}[iteration]
                : coolFormat(d, iteration + 1);

    }

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        return Arrays.stream(visits)
                .flatMap(map -> {
                    try {
                        return map.entrySet().stream();
                    } catch (NullPointerException e) {
                        return Stream.empty();
                    }
                })
                .filter(entry -> {
                    try {
                        Long.parseLong(entry.getKey());
                        return entry.getValue() != null
                                && entry.getValue()
                                .getVisitCount()
                                .isPresent();
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toMap(
                        entry -> Long.parseLong(entry.getKey()),
                        entry -> entry.getValue()
                                .getVisitCount()
                                .orElse(0L)
                        ,
                        Long::sum
                ));
    }
}
