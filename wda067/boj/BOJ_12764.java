import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 싸지방에 간 준하 / 골드3
https://www.acmicpc.net/problem/12764
 */
public class BOJ_12764 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //사람 수 1 ~ 100,000
        PriorityQueue<User> users = new PriorityQueue<>(Comparator.comparingInt((User o) -> o.s));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());  //시작 시간 0 ~ 1,000,000
            int Q = Integer.parseInt(st.nextToken());  //종료 시각 0 ~ 1,000,000
            users.add(new User(P, Q));
        }

        //(끝나는 시간, 컴퓨터 번호)
        PriorityQueue<int[]> endTime = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[0]));
        PriorityQueue<Integer> available = new PriorityQueue<>();
        int[] usageCount = new int[N + 1];  //각 컴퓨터 사용 횟수
        int count = 0;  //필요한 컴퓨터 수

        while (!users.isEmpty()) {
            User cur = users.poll();
            int start = cur.s;
            int end = cur.e;

            //사용 가능한 컴퓨터가 있을 경우
            while (!endTime.isEmpty() && endTime.peek()[0] <= start) {
                available.add(endTime.poll()[1]);  //사용 가능한 컴퓨터 번호 추가
            }

            int assignedPC;
            if (available.isEmpty()) {  //사용 가능한 컴퓨터가 없을 경우 다음 번호 할당
                assignedPC = count++;
            } else {  //사용 가능한 컴퓨터가 있을 경우
                assignedPC = available.poll();
            }

            endTime.add(new int[]{end, assignedPC});
            usageCount[assignedPC]++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        for (int i = 0; i < count; i++) {
            sb.append(usageCount[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static class User {
        private int s, e;

        public User(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
