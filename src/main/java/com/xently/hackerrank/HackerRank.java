package com.xently.hackerrank;

import java.util.List;

public class HackerRank {
    public static void main(String[] args) {
        System.out.println(
                diagonalDifference(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9))));
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        int lrs = 0;
        int rls = 0;

        for (int i = 0, j = 0, k = arr.size() - 1; i < arr.size(); i++, j++, k--) {
            lrs += arr.get(i).get(j);
            rls += arr.get(i).get(k);
        }
        return Math.abs(lrs - rls);
    }
}
