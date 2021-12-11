package com.xently.parser.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TechSavanna {
    /*
     * Complete the function below.
     * https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=[topic]
     */
    static int getTopicCount(String topic) throws Exception {
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=" + topic);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder response = new StringBuilder();
        while (true) {
            String body = br.readLine();
            if (body == null) break;
            response.append(body.trim());
        }
        return getCount(getText(response.toString()), topic);
    }

    static String getText(String json) {
        String s = "(?<text>(?<tkey>(\"text\")\\s*:\\s*\\{(?<tvalue>(.*))}))";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) return matcher.group("tvalue");
        return "";
    }

    static int getCount(String lookup, String query) {
        if (lookup == null || lookup.isEmpty()) return 0;
        if (query == null || query.isEmpty()) return 0;
        Pattern pattern = Pattern.compile(query);
        Matcher matcher = pattern.matcher(lookup);
        int count = 0;
        while (matcher.find()) ++count;
        return count;
    }

    /*
     * Complete the 'minDiff' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    public static int minDiff(List<Integer> arr) {
        List<Integer> data = arr.stream().sorted().collect(Collectors.toList());
        int sum = 0;
        for (int j = 1, dataSize = data.size(); j < dataSize; j++) {
            int x = data.get(j - 1), y = data.get(j);
            sum += Math.abs(y - x);
        }
        return sum;
    }

    /*
     * Complete the 'maxStrength' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */
    public static int maxStrength(int n) {
        Set<Integer> members = new HashSet<>();
        int next = n, leader = n, size = 0;
        do {
            members.add(next);
            next = getNextMemberId(next);
            leader = Math.max(next, leader);
            size += 1;
        } while (next >= 0 && next <= (int) Math.pow(10, 6) && !members.contains(next));
        return leader * size;
    }

    static int getNextMemberId(int currentMemberId) {
        int nextId = 0;
        char[] digits = String.valueOf(currentMemberId).toCharArray();
        for (char d : digits) nextId += factorial(d);
        return nextId;
    }

    public static int factorial(Object n) {
        int x = Integer.parseInt(String.valueOf(n));
        if (x == 0) return 1;
        return x * factorial(x - 1);
    }

    public static String fizzBuzz(int n) {
        assert n > 1;
        StringBuilder r = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            String log = "";
            if (i % 3 == 0) log = "Fizz";
            if (i % 5 == 0) log += "Buzz";
            if (log.isEmpty()) log = "" + i;
            r.append(log).append("\n");
//            System.out.println(log);
        }
        return r.toString();
    }

    public static int smallestPositiveInteger(int[] A) {
        int output = 1;
        int[] sortedA = Arrays.stream(A).sorted().toArray();
        for (int n : sortedA) {
            if (output == n) output += 1;
            else if (output < n) break;
        }
        return output;
    }

    public static int solution(int[] A, int X) {
        int N = A.length;
        if (N == 0) return -1;
        int l = 0;
        int r = N - 1;

        while (l < r) {
            int m = ((l + r) / 2) + 1;
            if (A[m] > X) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        if (A[l] == X) return l;
        return -1;
    }

    public static int solution1(int[] A, int[] B) {
        int m = B.length;

        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0;
        for (int j : A) {
            if (i < m - 1 && B[i] <= j) i += 1;
            if (j == B[i]) return j;
        }
        return -1;
    }

    public static int getTruesCount(String input) {
        int inputCharCount = input.length();
        int count = 0;
        for (int i = 0; i < inputCharCount; i++) {
            if (input.charAt(i) != '+' && i - 1 >= 0 && i + 1 < inputCharCount && input.charAt(i - 1) == '+' && input.charAt(i + 1) == '+') {
                ++count;
            }
        }
        return count;
    }

    public static String getSecurityCredentials(Path path, String unencryptedSecurityCredential) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // https://docs.oracle.com/javase/8/docs/api/index.html?javax/crypto/Cipher.html
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Files.readAllBytes(path));
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedCredentials = cipher.doFinal(unencryptedSecurityCredential.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedCredentials);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getSecurityCredentials(Paths.get("/home/mitch/projects/android/SandboxCertificate.cer"), "Safaricom111"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
