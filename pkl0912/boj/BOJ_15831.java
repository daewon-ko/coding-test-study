package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_15831 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        int lt = 0, rt = 0;
        int bcnt = 0, wcnt = 0;
        int max = 0;

        while (rt < n) {
            if (s.charAt(rt) == 'W') {
                wcnt++;
            } else {
                bcnt++;
            }

            while (bcnt > b) {
                if (s.charAt(lt) == 'W') {
                    wcnt--;
                } else {
                    bcnt--;
                }
                lt++;
            }

            if (wcnt >= w) {
                max = Math.max(max, rt - lt + 1);
            }

            rt++;
        }

        System.out.println(max);
    }
}
