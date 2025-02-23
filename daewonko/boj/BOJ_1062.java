package daewonko.boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;




/*
boj 1062 가르침
 */
public class BOJ_1062 {



        static int n, k;
        static String[] arr;
        static int cnt = 0;
        static boolean[] visited;
        static final int ALPHABET_SIZE = 26;
        static char[] crr = new char[]{'a', 'n', 't', 'i', 'c'};

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new String[n];  // n개의 단어를 저장하기 위한 배열 크기 설정
            visited = new boolean[ALPHABET_SIZE];

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                if (input != null) {
                    arr[i] = input.replaceAll("[antic]", "");
                }
            }

            if (k >= 5) {
                visited['a' - 'a'] = true;
                visited['n' - 'a'] = true;
                visited['t' - 'a'] = true;
                visited['i' - 'a'] = true;
                visited['c' - 'a'] = true;

                func(0, 0);
            }

            System.out.println(cnt);
        }

        private static void func(int start, int depth) {
            if (depth == (k - 5)) {
                int readableCount = 0;
                for (String s : arr) {
                    if (s != null) { // null 체크
                        boolean readable = true;
                        for (int i = 0; i < s.length(); i++) {
                            if (!visited[s.charAt(i) - 'a']) {
                                readable = false;
                                break;
                            }
                        }
                        if (readable) {
                            readableCount++;
                        }
                    }
                }
                cnt = Math.max(cnt, readableCount);
                return;
            }

            for (int i = start; i < ALPHABET_SIZE; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    func(i + 1, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }


