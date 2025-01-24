package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 공유기 설치 / 골드4
https://www.acmicpc.net/problem/2110
 */
public class BOJ_2110 {

    static int N, C;
    static int[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //집의 개수
        C = Integer.parseInt(st.nextToken());  //공유기의 개수

        pos = new int[N];
        for (int i = 0; i < N; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(pos);  //이진 탐색을 하기 위해 좌표를 정렬

        int start = 1;  //최소 거리
        int end = pos[N - 1] - pos[0];  //최대 거리
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canInstall(mid)) {  //설치 가능하면 더 긴 거리로 탐색
                result = mid;
                start = mid + 1;
            } else {  //설치 불가능하면 거리를 줄여서 탐색
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean canInstall(int distance) {
        //첫 번째 집에 공유기 설치
        int lastInstalled = pos[0];
        int count = 1;

        //두 번째 집부터 탐색
        for (int i = 1; i < N; i++) {
            //마지막에 설치한 집과 거리가 distance 이상일 때 설치
            if (pos[i] - lastInstalled >= distance) {
                count++;
                lastInstalled = pos[i];  //마지막에 설치한 집 갱신
            }
        }

        return count >= C;
    }
}
