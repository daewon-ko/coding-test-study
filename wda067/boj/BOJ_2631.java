import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
백준 / 줄세우기 / 골드4
https://www.acmicpc.net/problem/2631
 */
public class BOJ_2631 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //LIS -> 이진탐색 O(nlogn)
        List<Integer> lis = new ArrayList<>();  //실제 LIS가 아니라 길이만을 위한 가짜 수열
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int pos = Collections.binarySearch(lis, num);

            if (pos < 0) {  //lis안에 num이 없으면 -(삽입 위치 + 1)을 반환
                pos = -(pos + 1);  //실제 삽입 위치로 변환
            }

            if (pos >= lis.size()) {  //lis 끝에 추가할 수 있을 경우
                lis.add(num);
            } else {
                lis.set(pos, num);
            }
        }

        System.out.println(N - lis.size());
    }
}
