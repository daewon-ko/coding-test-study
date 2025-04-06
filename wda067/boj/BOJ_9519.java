import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
백준 / 졸려 / 골드5
https://www.acmicpc.net/problem/9519
 */
public class BOJ_9519 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        String observed = br.readLine();

        String original = findOriginalString(X, observed);
        System.out.println(original);
    }

    private static String findOriginalString(int X, String observed) {
        String current = observed;
        List<String> cycle = new ArrayList<>();
        cycle.add(current);

        while (true) {
            current = shuffle(current);
            if (cycle.contains(current)) {
                break;
            }
            cycle.add(current);
        }

        int cycleLength = cycle.size();
        int effectiveShuffles = X % cycleLength;

        current = observed;
        for (int i = 0; i < effectiveShuffles; i++) {
            current = shuffle(current);
        }

        return current;
    }

    private static String shuffle(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i += 2) {
            sb.append(s.charAt(i));
        }
        for (int i = len - 1 - (len % 2); i > 0; i -= 2) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
