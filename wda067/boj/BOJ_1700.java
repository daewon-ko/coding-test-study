import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 / 멀티탭 스케줄링 / 골드1
https://www.acmicpc.net/problem/1700
 */
public class BOJ_1700 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < K; i++) {
            int cur = arr[i];

            if (set.contains(cur)) {  //이미 사용 중 패스
                continue;
            }

            if (set.size() < N) {  //빈 공간이 있을 경우 추가 후 패스
                set.add(cur);
                continue;
            }

            //이 외는 어떤 전기용품을 빼야 할지 결정해야 한다

            int far = -1;  //가장 멀리 떨어진 인덱스
            int remove = -1;  //삭제할 인덱스

            for (int plugged : set) {
                int next = Integer.MAX_VALUE;  //현재 용품이 다시 사용되는 인덱스 -> 사용되지 않으면 무한대

                for (int j = i + 1; j < K; j++) {  //다음 용품부터 탐색
                    if (arr[j] == plugged) {  //꽂혀있을 경우
                        next = j;
                        break;
                    }
                }

                if (next > far) {  //가장 말리 떨어진 인덱스 갱신
                    far = next;
                    remove = plugged;  //가장 나중에 쓰이는 용품을 제거
                }
            }

            set.remove(remove);  //가장 나중에 다시 쓰이거나, 아예 다시 안 쓰이는 용품을 제거
            set.add(cur);
            count++;
        }

        System.out.println(count);
    }
}
