import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
백준 / 꼬인 전깃줄 / 골드2
https://www.acmicpc.net/problem/1365
 */
public class BOJ_1365 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] poles = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> lis = new ArrayList<>();  //가장 긴 증가하는 부분 수열
        for (int pole : poles) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < pole) {
                lis.add(pole);
            } else {
                int pos = Collections.binarySearch(lis, pole);  //이진 탐색으로 위치 탐색
                if (pos < 0) {
                    pos = -(pos + 1);
                }
                lis.set(pos, pole);
            }
        }

        System.out.println(N - lis.size());
    }
}
