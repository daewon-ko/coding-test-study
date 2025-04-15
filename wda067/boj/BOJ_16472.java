import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
백준 / 고냥이 / 골드4
https://www.acmicpc.net/problem/16472
 */
public class BOJ_16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int len = str.length();

        int s = 0, e = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();

        int kind = 0;  //알파벳 종류 수

        while (e < len) {
            char end = str.charAt(e);
            map.put(end, map.getOrDefault(end, 0) + 1);
            if (map.get(end) == 1) {
                kind++;
            }

            while (kind > N) {  //종류의 최대 개수를 안넘을 때까지 윈도우 크기를 줄임
                char start = str.charAt(s);
                map.put(start, map.get(start) - 1);
                if (map.get(start) == 0) {
                    kind--;
                }
                s++;
            }

            max = Math.max(max, e - s + 1);  //현재 윈도우의 크기
            e++;
        }

        System.out.println(max);
    }
}
