import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 합이 0 / 골드4
https://www.acmicpc.net/problem/3151
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //10,000
        int[] students = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(students);
        long count = 0;

        for (int i = 0; i < N - 2; i++) {
            int s = i + 1;
            int e = N - 1;

            while (s < e) {
                int sum = students[i] + students[s] + students[e];

                if (sum == 0) {
                    if (students[s] == students[e]) {  //투 포인터의 값이 같을 때
                        int same = e - s + 1;  //같은 값 개수
                        count += (long) same * (same - 1) / 2;  //nC2
                        break;
                    } else {
                        int left = students[s];
                        int right = students[e];
                        int leftCount = 0;
                        int rightCount = 0;

                        //left와 같은 값의 개수
                        while (s <= e && students[s] == left) {
                            s++;
                            leftCount++;
                        }

                        //right와 같은 값의 개수
                        while (s <= e && students[e] == right) {
                            e--;
                            rightCount++;
                        }

                        count += (long) leftCount * rightCount;  //조합의 수
                    }
                } else if (sum < 0) {
                    s++;
                } else {
                    e--;
                }
            }
        }

        System.out.println(count);
    }
}
