package daewonko.boj;
import java.io.*;
import java.util.*;
public class BOJ_3020 {


        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            int[] bottom = new int[H + 1];
            int[] top = new int[H + 1];

            for (int i = 0; i < N; i++) {
                int height = Integer.parseInt(br.readLine());
                if (i % 2 == 0) bottom[height]++;
                else top[H - height + 1]++;
            }

            for (int i = H - 1; i >= 1; i--) bottom[i] += bottom[i + 1];
            for (int i = 1; i <= H; i++) top[i] += top[i - 1];

            int minObstacles = Integer.MAX_VALUE;
            int count = 0;

            for (int i = 1; i <= H; i++) {
                int total = bottom[i] + top[i];
                if (total < minObstacles) {
                    minObstacles = total;
                    count = 1;
                } else if (total == minObstacles) {
                    count++;
                }
            }

            System.out.println(minObstacles + " " + count);
        }
    }

