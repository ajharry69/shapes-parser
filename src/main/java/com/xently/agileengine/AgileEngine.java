package com.xently.agileengine;

import java.util.*;
import java.util.stream.Collectors;

public class AgileEngine {
    // Check if two strings are anagrams
    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : a.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for (char c : b.toCharArray()) {
            if (!charCount.containsKey(c) || charCount.get(c) == 0) return false;
            charCount.put(c, charCount.get(c) - 1);
        }
        return true;
    }

    // Helper function to check if a sentence can be formed by rearranging words from the word set
    public static boolean canFormSentence(String sentence, List<String> wordSet) {
        Set<String> words = new HashSet<>(List.of(sentence.split(" ")));
        for (String word : words) {
            if (!wordSet.contains(word)) return false;
        }
        return true;
    }

    public static int countSentences(String[] wordSet, String[] sentences) {
        List<String> wordSetList = Arrays.asList(wordSet);
        return (int) Arrays.stream(sentences)
                .filter(sentence -> {
                    String[] words = sentence.split(" ");
                    for (int i = 0; i < words.length; i++) {
                        for (int j = i + 1; j < words.length; j++) {
                            if (isAnagram(words[i], words[j])) {
                                String temp = words[i];
                                words[i] = words[j];
                                words[j] = temp;
                                if (canFormSentence(String.join(" ", words), wordSetList)) {
                                    return true;
                                }
                                temp = words[i];
                                words[i] = words[j];
                                words[j] = temp;
                            }
                        }
                    }
                    return false;
                })
                .count();
    }

    public static void main(String[] args) {
        System.out.println(findMaxOccurringLetter("helloworld"));
        String[] wordSet = {"listen", "silent", "it", "is"};
        String[] sentences = {"listen it is silent"};
        int count = countSentences(wordSet, sentences);
        System.out.println(count); // Output: 4
    }

    public static char findMaxOccurringLetter(String str) {
        if (str == null || str.isEmpty()) {
            return ' ';
        }
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum, HashMap::new))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
}

