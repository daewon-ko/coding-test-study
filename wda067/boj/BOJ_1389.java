import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준
public class BOJ_1389 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //인접 행렬 초기화
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 10_000_001);
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            //친구 관계의 가중치는 1
            dist[A][B] = 1;
            dist[B][A] = 1;
        }

        //플로이드-워셜
        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (dist[s][k] != 0 && dist[k][e] != 0) {
                        dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                    }
                }
            }
        }

        //케빈 베이컨의 수 계산
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int weight = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    weight += dist[i][j];
                }
            }

            result[i] = weight;
        }

        //케빈 베이컨의 수의 최솟값
        int min = Arrays.stream(result)
                .filter(i -> i != 0)
                .min()
                .orElseThrow();

        //최솟값의 인덱스 출력
        for (int i = 1; i <= N; i++) {
            if (result[i] == min) {
                System.out.println(i);
                return;
            }
        }
    }
}
