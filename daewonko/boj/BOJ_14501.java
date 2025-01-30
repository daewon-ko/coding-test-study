package daewonko.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

//백준 퇴사
public class BOJ_14501 {


    /**
     * 백준 퇴사
     */
    static int n;
    static int[] t;
    static int[] p;
    static boolean[] visited;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        t = new int[n + 1];
        p = new int[n + 1];
        visited = new boolean[n + 1];


        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        func(1, 0);
        System.out.println(MAX);

    }

    public static void func(int day, int sum) {

        if (day > n + 1) {
            return;
        } else {

            MAX = Math.max(MAX, sum);

        }


        for (int i = day; i <= n; i++) {
//            if (!visited[i]) {
//                visited[i] = true;
            func(i + t[i], sum + p[i]);
//                visited[i] = false;
//            }
        }


    }
}

