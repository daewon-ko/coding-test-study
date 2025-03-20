import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 가장 긴 증가하는 부분 수열 4 / 골드4
https://www.acmicpc.net/problem/14002
 */
public class BOJ_14002 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];  //dp[i]: i번째까지 LIS
        int[] prev = new int[N];  //트래킹 배열
        Arrays.fill(prev, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0, lastIndex = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;  //최소 길이는 1
            for (int j = 0; j < i; j++) {
                //j -> i가 증가하면서 j번째까지 LIS에 arr[i]을 추가 한 것이 더 클 경우
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;  //이전 인덱스 저장
                }
            }

            if (dp[i] > max) {  //LIS 길이 갱신
                max = dp[i];
                lastIndex = i;  //LIS 마지막 원소의 인덱스
            }
        }

        List<Integer> lis = new ArrayList<>();
        while (lastIndex != -1) {
            lis.add(arr[lastIndex]);
            lastIndex = prev[lastIndex];
        }
        Collections.sort(lis);  //역순이므로 정렬

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n");  //LIS의 최대 길이
        for (Integer num : lis) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }
}

