package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BOJ_9519 {
    static int len;
    static List<Character> wordList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int X = Integer.parseInt(br.readLine());
        String word = br.readLine();
        List<String> words = new ArrayList<>();

        len = word.length();
        wordList = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            wordList.add(word.charAt(i));
        }
        words.add(wordList.toString());

        for (int i = 0; i < X; i++) {
            blink();
            String now = wordList.toString();
            if (words.contains(now)) {
                break;
            }
            words.add(now);
        }

        String answer = words.get(X % words.size());
        answer = answer.replace("[", "").replace("]", "").replace(" ", "").replace(",", "");

        System.out.println(answer);
    }

    static void blink() {
        int idx = len - 1;
        if (len % 2 == 1) {
            idx -= 1;
        }
        for (; idx > 0; idx -= 2) {
            char now = wordList.get(idx);
            wordList.remove(idx);
            wordList.add(now);
        }
    }
}
