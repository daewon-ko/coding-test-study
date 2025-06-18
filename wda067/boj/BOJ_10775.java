import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / 공항 / 골드2
https://www.acmicpc.net/problem/10775
 */
public class BOJ_10775 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());
            int dockingGate = find(gi);

            if (dockingGate == 0) {
                break;
            }
            union(dockingGate, dockingGate - 1);
            answer++;
        }

        System.out.println(answer);
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[a] = b;
    }
}

