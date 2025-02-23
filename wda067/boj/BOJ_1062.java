import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 가르침 / 골드4
https://www.acmicpc.net/problem/1062
 */
public class BOJ_1062 {

    private static final int ALPHABET_SIZE = 26;
    private static int N, K;
    private static int learned = 0;
    private static int max = 0;
    private static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //1~50
        K = Integer.parseInt(st.nextToken());  //0~26

        if (K < 5) {
            System.out.println(0);
            return;
        }

        //acint는 필수로 배워야 함
        for (char c : "acint".toCharArray()) {
            learned |= (1 << (c - 'a'));  //비트마스크로 체크
        }

        words = new int[N];
        for (int i = 0; i < N; i++) {
            for (char c : br.readLine().toCharArray()) {
                words[i] |= (1 << (c - 'a'));  //단어를 비트마스크로 저장
            }
        }

        recur(0, 5);

        System.out.println(max);
    }

    private static void recur(int index, int count) {
        if (count == K) {

            //배운 문자로 읽을 수 있는 단어 카운트
            int temp = 0;
            for (int word : words) {
                if ((word & learned) == word) {
                    temp++;
                }
            }

            max = Math.max(max, temp);
            return;
        }

        for (int i = index; i < ALPHABET_SIZE; i++) {
            int cur = 1 << i;  //현재 문자의 비트마스크
            if ((learned & cur) == 0) {  //배우지 않은 문자라면
                learned |= cur;  //i번째 문자 추가
                recur(i + 1, count + 1);
                learned &= ~cur;  //i번째 문자만 제거 (백트래킹)
            }
        }
    }
}
