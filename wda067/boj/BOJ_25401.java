import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 카드 바꾸기 / 골드5
https://www.acmicpc.net/problem/25401
 */
public class BOJ_25401 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //2 ~ 500
        int[] cards = new int[N];  //-1,000,000 ~ 1,000,000

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int min = N - 1;  //최대 변경 횟수

        //모든 카드 쌍으로 가능한 공차 탐색
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int diff = cards[j] - cards[i];
                int dist = j - i;

                //a1 = 3, a3 = 7일 경우
                //공차는 (7 - 3) / 2
                int d = diff / dist;
                int count = 0;

                //현재 공차를 기준으로 등차수열 생성
                for (int n = 0; n < N; n++) {
                    int a1 = cards[i];  //초항
                    //등차수열의 일반항
                    int an = a1 + (n - i) * d;
                    if (cards[n] != an) {
                        count++;
                    }
                }

                min = Math.min(min, count);
            }
        }

        System.out.println(min);
    }
}