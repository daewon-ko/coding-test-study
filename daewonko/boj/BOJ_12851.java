package daewonko.boj;
import java.util.*;
public class BOJ_12851 {


        static int N, K;
        static int[] time = new int[100001];
        static int minTime = Integer.MAX_VALUE, count = 0;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            K = sc.nextInt();
            sc.close();

            if (N >= K) {
                System.out.println((N - K) + "\n1");
                return;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(N);
            time[N] = 1;

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                if (time[cur] > minTime) break;

                for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                    if (next < 0 || next > 100000) continue;

                    if (time[next] == 0 || time[next] == time[cur] + 1) {
                        time[next] = time[cur] + 1;
                        queue.add(next);

                        if (next == K) {
                            minTime = time[next] - 1;
                            count++;
                        }
                    }
                }
            }

            System.out.println(minTime);
            System.out.println(count);
        }
    }

