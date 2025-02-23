package yeonjy.boj;

import java.util.*;

public class BOJ_1062 {
    static int words, max, total = 0;
    static boolean[] isLearned = new boolean[26];
    static ArrayList<Character> alphabetList;
    static String[] wordArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        words = Integer.parseInt(st.nextToken());
        total = Integer.parseInt(st.nextToken());

        if (total<5) {
            System.out.println(0);
            return;
        } else if (total==26) {
            System.out.println(words);
            return;
        }

        isLearned['a'-'a'] = true;
        isLearned['n'-'a'] = true;
        isLearned['t'-'a'] = true;
        isLearned['i'-'a'] = true;
        isLearned['c'-'a'] = true;

        wordArr = new String[words];
        Set<Character> tempSet = new HashSet<>();

        for (int i = 0; i < words; i++) {
            String word = bf.readLine();

            word = word.replace("anta", "").replace("tica", "");
            wordArr[i] = word;
            int length = word.length();

            for (int j = 0; j < length; j++) {
                char now = word.charAt(j);
                if (now!='a' && now!='n' && now!='t' && now!='i' && now!='c') {
                    tempSet.add(now);
                }
            }
        }

        alphabetList = new ArrayList<>(tempSet);
        dfs(-1, 5);
        System.out.println(max);
    }

    public static void dfs(int index, int num) {
        int cnt = 0;
        for (int i = 0; i < words; i++) {
            boolean isOk = true;
            String nowStr = wordArr[i];
            int length = nowStr.length();

            for (int j = 0; j < length; j++) {
                char nowChar = nowStr.charAt(j);
                if (!isLearned[nowChar-'a']) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) cnt++;
        }
        max = Math.max(max, cnt);

        if (num==total) return;

        int size = alphabetList.size();
        for (int i = index+1; i < size; i++) {
            Character nowChar = alphabetList.get(i);
            if (!isLearned[nowChar-'a']) {
                isLearned[nowChar-'a'] = true;
                dfs(i, num+1);
                isLearned[nowChar-'a'] = false;
            }
        }
    }
}
