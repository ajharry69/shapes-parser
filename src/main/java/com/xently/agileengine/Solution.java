package com.xently.agileengine;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static int countSubstrings(String s) {
        Set<String> set = new HashSet<>();
        int sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < sLength && s.charAt(l) == s.charAt(r)) {
                set.add(s.substring(l, r + 1));
                l--;
                r++;
            }
            l = i;
            r = i +1;
            while (l >= 0 && r < sLength && s.charAt(l) == s.charAt(r)) {
                set.add(s.substring(l, r + 1));
                l--;
                r++;
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(3/2);
//        String s = "mokkori";
//        String s = "aabaa";
//        int result = countSubstrings(s);
//        System.out.println(result); // Output: 7
//        System.out.println(countSentences(List.of("cat", "the", "bats"), List.of("cat the bats", "act the bats","cat the tabs", "act the tabs")));
    }
    public static List<Long> countSentences(List<String> wordSet, List<String> sentences) {
        return sentences.stream()
                .map(sentence -> countValidSentences(sentence, wordSet))
                .collect(Collectors.toList());
    }

    private static long countValidSentences(String sentence, List<String> wordSet) {
        Set<String> words = new HashSet<>(Arrays.asList(sentence.split(" ")));
        // Filter out sentences containing words not in the wordSet
        words.retainAll(wordSet);

        if (words.size() == sentence.split(" ").length) {
            return generatePermutations(new ArrayList<>(words));
        }
        return 0;
    }

    private static int generatePermutations(List<String> words) {
        if (words.size() == 1) {
            return 1;
        }

        int count = 0;
        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.remove(i);
            count += generatePermutations(words);
            words.add(i, currentWord); // Backtrack by adding the removed word back
        }
        return count;
    }

    public static List<Long> countSentences1(List<String> wordSet, List<String> sentences) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordSet) {
            String signature = getSignature(word);
            map.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
        }

        List<Long> result = new ArrayList<>();

        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            Set<String> strings = new HashSet<>();
            generateSentences(words, 0, new ArrayList<>(), map, strings);
            result.add((long) strings.size());
        }

        return result;
    }

    private static String getSignature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private static void generateSentences(String[] words, int index, List<String> sentence,
                                          Map<String, List<String>> map, Set<String> result) {
        if (index == words.length) {
            result.add(String.join(" ", sentence));
            return;
        }

        String wordSignature = getSignature(words[index]);
        if (map.containsKey(wordSignature)) {
            for (String anagram : map.get(wordSignature)) {
                sentence.add(anagram);
                generateSentences(words, index + 1, sentence, map, result);
                sentence.remove(sentence.size() - 1);
            }
        }
    }
}
