package daewonko.boj;

import java.util.*;
import java.io.*;

public class BOJ_12970 {

        static int n, k;
        static char[] ans;
        static boolean flag = false;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            ans = new char[n];

            if (k == 0) {
                flag = true;
                Arrays.fill(ans, 'A');
            } else {
                greedy();
            }

            if (flag) {
                bw.write(new String(ans));
            } else {
                bw.write("-1");
            }

            bw.close();
            br.close();
        }

        static void greedy() {
            int b = n;
            int a = 0;

            while (a <= n) {
                if (a * b < k) {
                    a++;
                    b--;
                    continue;
                }

                Arrays.fill(ans, 'B');
                for (int i = 0; i < a - 1; i++) {
                    ans[i] = 'A';
                }

                int res = (a - 1) * b;
                int remain = k - res;
                ans[(n - 1) - remain] = 'A';
                flag = true;
                break;
            }
        }
    }

