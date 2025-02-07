package daewonko.boj;


import java.io.*;
import java.util.*;

public class BOJ_8983 {


    static int M, N, L;
    static int[] hunters;
    static int[][] animals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 사대의 수
        N = Integer.parseInt(st.nextToken()); // 동물의 수
        L = Integer.parseInt(st.nextToken()); // 사정거리

        hunters = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            hunters[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(hunters); // 사대 위치 정렬 (이진 탐색을 위해)

        animals = new int[N][2];
        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y > L) continue; // y 좌표가 L보다 크면 절대 사냥 불가

            int left = 0, right = M - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int hunterX = hunters[mid];

                if (Math.abs(hunterX - x) + y <= L) { // 사정거리 내인지 확인
                    count++;
                    break;
                }
                if (hunterX < x) left = mid + 1;
                else right = mid - 1;
            }
        }

        System.out.println(count);
    }
}


