package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_20166 {
    private static final int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
    private static final int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};

    private static char[][] map;
    private static Map<String, Integer> godLoveWords;

    private static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());
        k = Integer.parseInt(stz.nextToken());

        map = new char[n][m];
        for(int i=0; i<n; i++){
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        makeGodLoveWords();

        while (k-- > 0){
            String god = br.readLine();
            int num = godLoveWords.getOrDefault(god, 0);
            bw.write(num + System.lineSeparator());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void makeGodLoveWords(){
        int maxLen = 5;
        godLoveWords = new HashMap<>();

        for(int r=1; r<=maxLen; r++){
            for(int i=0; i<n; i++){
                for (int j = 0; j < m; j++) {
                    dfs(i, j, r, 1, String.valueOf(map[i][j]));
                }
            }
        }
    }

    private static void dfs(int x, int y, int r, int depth, String str) {
        if(depth == r){
            godLoveWords.put(str, godLoveWords.getOrDefault(str, 0 ) + 1);
            return;
        }

        for(int i=0; i<8; i++){
            int nx = (x + dx[i]) % n;
            int ny = (y + dy[i]) % m;

            dfs(nx, ny, r, depth + 1, str + map[nx][ny]);
        }
    }
}
