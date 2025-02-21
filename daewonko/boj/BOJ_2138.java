package daewonko.boj;
import java.util.*;
public class BOJ_2138 {


        static int INF = 1000000;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            String start = sc.next();
            String target = sc.next();

            int[] bulbs = new int[N];
            int[] goal = new int[N];

            for (int i = 0; i < N; i++) {
                bulbs[i] = start.charAt(i) - '0';
                goal[i] = target.charAt(i) - '0';
            }

            int result1 = switchBulbs(N, bulbs.clone(), goal, false);
            int result2 = switchBulbs(N, bulbs.clone(), goal, true);

            int answer = Math.min(result1, result2);
            System.out.println(answer == INF ? -1 : answer);
        }

        static int switchBulbs(int N, int[] bulbs, int[] goal, boolean firstSwitch) {
            int count = 0;

            if (firstSwitch) {
                toggle(bulbs, 0);
                count++;
            }

            for (int i = 1; i < N; i++) {
                if (bulbs[i - 1] != goal[i - 1]) {
                    toggle(bulbs, i);
                    count++;
                }
            }

            return Arrays.equals(bulbs, goal) ? count : INF;
        }

        static void toggle(int[] bulbs, int idx) {
            for (int i = idx - 1; i <= idx + 1; i++) {
                if (i >= 0 && i < bulbs.length) {
                    bulbs[i] ^= 1;
                }
            }
        }
    }

