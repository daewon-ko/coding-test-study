package daewonko.boj;

import java.io.*;
import java.util.*;

public class BOJ_2042 {


    static int N, M, K;
    static long[] tree;
    static int offset;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: 배열 크기 N, 업데이트 횟수 M, 구간 질의 횟수 K
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1. 최소 k 구하기: 2^k >= N
        int length = N;
        int treeHeight = 0;
        while (length != 0) {
            length /= 2;
            treeHeight++;
        }



//        int k = (int) Math.ceil(Math.log(N) / Math.log(2));
        offset = (int) Math.pow(2, treeHeight);
        int treeSize = offset * 2; // 전체 트리 배열 크기는 2^(k+1)
        tree = new long[treeSize];


        for (int i = 0; i < N; i++) {
            tree[offset + i] = Long.parseLong(br.readLine());
        }
        // 남은 리프 노드가 있다면 0으로 채운다.
        for (int i = N; i < offset; i++) {
            tree[offset + i] = 0;
        }

        // 2^k-1부터 1씩 감소하며 tree를 채워넣는다.
        for (int i = offset - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }


        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) { // 업데이트
                int index = Integer.parseInt(st.nextToken());
                long newValue = Long.parseLong(st.nextToken());
                update(index - 1, newValue);
            } else if (type == 2) { // 구간 합 구하기
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                long result = query(l - 1, r - 1);
                bw.write(result + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    // update 메서드: 원본 배열의 index 위치에 있는 값을 newValue로 업데이트하고,
    // 리프 노드부터 루트까지 부모 노드의 값을 갱신한다.
    static void update(int index, long newValue) {
        int pos = offset + index; // 리프 노드 위치 계산
        tree[pos] = newValue;      // 해당 리프 노드 값 업데이트

        // tree의 인덱스는 1부터 시작하며 1일 경우에 루트이므로 해당 로직을 타면 안된다.
        // 따라서 pos>1의 조건으로 작성
        while (pos > 1) {
            pos /= 2;
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
        }
    }


    static long query(int l, int r) {
        long sum = 0;
        int start_index = offset + l;
        int end_index = offset + r;

        // 반복문으로 상위 노드로 올라가면서 구간에 해당하는 노드의 값을 선택
        while (start_index <= end_index) {
            // 만약 start_index가 홀수라면(즉, 부모의 오른쪽 자식)
            // 해당 노드는 구간의 왼쪽 경계에 딱 맞으므로 결과에 더하고, start_index를 1 증가
            if (start_index % 2 == 1) {
                sum += tree[start_index];
                start_index++;
            }
            // 만약 end_index가 짝수라면(즉, 부모의 왼쪽 자식)
            // 해당 노드는 구간의 오른쪽 경계에 딱 맞으므로 결과에 더하고, end_index를 1 감소
            if (end_index % 2 == 0) {
                sum += tree[end_index];
                end_index--;
            }
            // 둘 다 부모 노드로 이동
            start_index /= 2;
            end_index /= 2;
        }
        return sum;
    }
}


