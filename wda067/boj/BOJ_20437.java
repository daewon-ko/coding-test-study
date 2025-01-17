import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
백준 / 문자열 게임 2 / 골드5
https://www.acmicpc.net/problem/20437
 */
public class BOJ_20437 {

    private static final int ALPHABET_COUNT = 26;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                sb.append(1).append(" ").append(1).append("\n");
                continue;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            //문자열의 각 알파벳의 인덱스 저장
            List<List<Integer>> indices = new ArrayList<>();
            for (int i = 0; i < ALPHABET_COUNT; i++) {
                indices.add(new ArrayList<>());
            }

            for (int i = 0; i < W.length(); i++) {
                indices.get(W.charAt(i) - 'a').add(i);

            }

            //모든 알파벳의 대해 탐색
            for (int i = 0; i < ALPHABET_COUNT; i++) {
                //현재 알파벳의 인덱스 리스트
                List<Integer> curIndices = indices.get(i);

                if (curIndices.size() < K) {  //알파벳의 개수가 K가 안되면 스킵
                    continue;
                }

                //현재 알파벳을 K개 포함하는 문자열의 최소/최대 길이 계산
                for (int j = 0; j <= curIndices.size() - K; j++) {
                    int firstIndex = curIndices.get(j);
                    int lastIndex = curIndices.get(j + K - 1);
                    int length = lastIndex - firstIndex + 1;
                    min = Math.min(min, length);
                    max = Math.max(max, length);
                }
            }
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(min).append(" ").append(max).append("\n");
            }
        }

        System.out.println(sb);
    }
}
