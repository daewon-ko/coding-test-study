package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] lines = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken()); // A
            lines[i][1] = Integer.parseInt(st.nextToken()); // B
        }

        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLis = 1;

        for(int i = 1; i<n; i++){
            for(int j = 0; j<i; j++){
                if(lines[j][1]<lines[i][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }

            }
            maxLis = Math.max(maxLis, dp[i]);
        }
        System.out.println(n-maxLis);
    }
}
