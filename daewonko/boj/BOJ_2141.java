package daewonko.boj;
import java.io.*;
import java.util.*;
public class BOJ_2141 {
    public static void main(String[] args) {


            public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int n = Integer.parseInt(br.readLine());
                long[][] villages = new long[n][2];
                long totalPopulation = 0;

                for (int i = 0; i < n; i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    villages[i][0] = Long.parseLong(st.nextToken());
                    villages[i][1] = Long.parseLong(st.nextToken());
                    totalPopulation += villages[i][1];
                }

                Arrays.sort(villages, Comparator.comparingLong(a -> a[0]));

                long currentPopulation = 0;
                for (int i = 0; i < n; i++) {
                    currentPopulation += villages[i][1];
                    if (currentPopulation >= (totalPopulation + 1) / 2) {
                        System.out.println(villages[i][0]);
                        return;
                    }
                }
            }
        }

    }

