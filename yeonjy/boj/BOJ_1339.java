package yeonjy.boj;

import java.io.*;
import java.util.*;

public class BOJ_1339 {
    static int N;
    static String[] words;
    static Map<Character, Long> charWeights = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            calculateWeights(words[i]);
        }

        List<Character> chars = new ArrayList<>(charWeights.keySet());
        chars.sort((a, b) -> Long.compare(charWeights.get(b), charWeights.get(a)));

        Map<Character, Integer> charToNum = new HashMap<>();
        int num = 9;
        for (char c : chars) {
            charToNum.put(c, num--);
        }

        long total = 0;
        for (String word : words) {
            total += convertToNumber(word, charToNum);
        }
        System.out.println(total);
    }

    static void calculateWeights(String word) {
        int len = word.length();
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            long weight = (long) Math.pow(10, len - i - 1);
            charWeights.put(c, charWeights.getOrDefault(c, 0L) + weight);
        }
    }

    static long convertToNumber(String word, Map<Character, Integer> map) {
        long num = 0;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            num = num * 10 + map.get(word.charAt(i));
        }
        return num;
    }
}
