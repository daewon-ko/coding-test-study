import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 선 긋기 / 골드5
https://www.acmicpc.net/problem/2170
 */
public class BOJ_2170 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new int[]{x, y});
        }

        //두 점의 좌표(x1, x2)에서 x1 오름차순 정렬, x1이 같다면 x2는 오름차순 정렬
        list.sort(Comparator.comparingInt((int[] o) -> o[0])
                .thenComparingInt((int[] o) -> o[1]));

        //첫 번째 좌표로 초기화
        int start = list.get(0)[0];
        int end = list.get(0)[1];
        int total = 0;

        for (int i = 1; i < N; i++) {
            int x1 = list.get(i)[0];
            int x2 = list.get(i)[1];
            if (x1 > end) {  //끝점보다 클 경우 새로운 선분
                total += end - start;  //기존 선분 길이 추가
                //새로운 선분
                start = x1;
                end = x2;
            } else {  //기존 선분일 경우 끝점 갱신
                end = Math.max(end, x2);
            }
        }

        total += end - start;  //마지막 선분 길이 추가
        System.out.println(total);
    }
}
