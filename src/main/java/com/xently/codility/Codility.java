package com.xently.codility;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class Codility {
    public static void main(String[] args) {
        bufferedReaderAndWriter();
    }

    private static void bufferedReaderAndWriter() {
        var outputFilePath = Paths.get("data", "output", "output.txt");

        var inputFilePath = Paths.get("data", "sample-2mb-text-file.txt");

        try (var reader = new BufferedReader(new FileReader(inputFilePath.toFile()));
             var writer = new BufferedWriter(new FileWriter(outputFilePath.toFile()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Perform operations on each line if needed
                // For demonstration, let's just write the line to the output file
                writer.write(line);
                writer.newLine(); // Add a new line after each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int solution(int[] A) {
        return Arrays.stream(A)
                .sorted()
                .reduce(0, (a, b) -> {
                    int max = Math.max(a, b);
                    if (max - 1 != a) {
                        return a;
                    }
                    return max;
                }) + 1;
    }

    /**
     * Write a function that, given a string `s` containing `N` characters, returns the alphabetically
     * smallest string that can be obtained by removing exactly one letter from `s`.
     *
     * Examples:
     * 1. Given s = "acb", by removing one letter, you can obtain "ac", "ab" or "cb". Your function
     * should return "ab" (after removing "c") since it is alphabetically smaller than "ac" and "bc".
     * 2. Given s = "hot", your function should return "ho", which is alphabetically smaller than "ht"
     * and "ot".
     * 3. Given s = "codility", your function should return "cdility", which can be obtained by removing
     * the second letter.
     * 4. Given s = "aaaa", your function should return "aaa". Any occurrence of "a" can be removed.
     *
     * Write an efficient algorithm for the following assumptions:
     *  - `N` is an integer within the range [2..100,000];
     *  - string `s` is made of only lowercase letters (`a`-`z`).
     * @param s
     * @return
     */
    public String solution(String s) {
        String smallest = s;

        for (int i = 0; i < s.length(); i++) {
            String candidate = s.substring(0, i) + s.substring(i + 1);
            if (candidate.compareTo(smallest) < 0) {
                smallest = candidate;
            }
        }

        return smallest;
    }

    public static String invert(String s) {
        if (s == null) return "";
        return new StringBuilder(s).reverse().toString();
    }

}
