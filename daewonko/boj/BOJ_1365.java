package daewonko.boj;
import java.util.*;
public class BOJ_1365 {


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            List<Integer> lis = new ArrayList<>();

            for (int num : arr) {
                int idx = Collections.binarySearch(lis, num);
                if (idx < 0) idx = -idx - 1;

                if (idx == lis.size()) {
                    lis.add(num);
                } else {
                    lis.set(idx, num);
                }
            }

            System.out.println(n - lis.size());
            sc.close();
        }
    }

