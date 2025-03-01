import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 개똥벌레 / 골드5
https://www.acmicpc.net/problem/3020
 */
public class BOJ_3020 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("wda067/io/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] bottom = new int[H + 1];  //석순
        int[] top = new int[H + 1];  //종유석

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[h]++;
            } else {
                top[h]++;
            }
        }

        //장애물 개수 누적
        for (int i = H - 1; i > 0; i--) {
            bottom[i] += bottom[i + 1];
            top[i] += top[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int h = 1; h <= H; h++) {  //각 높이의 장애물 개수 탐색
            int obstacles = bottom[h] + top[H - h + 1];

            if (obstacles < min) {  //새로운 최솟값일 경우 갱신
                min = obstacles;
                count = 1;
            } else if (obstacles == min) {  //최솟값인 경우 카운트
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
}
