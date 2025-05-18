import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 저울 / 골드2
https://www.acmicpc.net/problem/2437
 */
public class BOJ_2437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //1,000
        int[] weigths = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weigths[i] = Integer.parseInt(st.nextToken());  //1,000,000
        }

        Arrays.sort(weigths);

        int sum = 0;  //지금까지 만들 수 있는 최대 무게
        for (int w : weigths) {  //추를 1개씩 사용
            if (w > sum + 1) {  //현재 추가 sum + 1보다 크다면 그 무게는 만들 수 없음
                break;
            }
            sum += w;  //만들 수 있는 범위 확장
        }

        System.out.println(sum + 1);
    }
}

