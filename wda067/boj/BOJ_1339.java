import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
백준 / 단어 수학 / 골드4
https://www.acmicpc.net/problem/1339
 */
public class BOJ_1339 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();

        //알파벳별 가중치 계산
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int len = word.length();
            int pow = (int) Math.pow(10, len - 1);

            for (int j = 0; j < len; j++) {
                char c = word.charAt(j);
                map.put(c, map.getOrDefault(c, 0) + pow);
                pow /= 10;
            }
        }

        List<Integer> weights = new ArrayList<>(map.values());
        weights.sort(Comparator.reverseOrder());

        //가중치 순서대로 9부터 할당하여 최대합 계산
        int num = 9, result = 0;
        for (Integer w : weights) {
            result += w * num;
            num--;
        }

        System.out.println(result);
    }
}
