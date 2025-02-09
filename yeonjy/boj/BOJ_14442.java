package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
    static int N, M, K;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx= {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M][K+1];
        map = new char[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }
        simulate();
        if(answer == 0) {
            System.out.println("-1");
        }else {
            System.out.println(answer);
        }

    }

    public static void simulate() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            int r = temp.r;
            int c = temp.c;
            int moveCnt = temp.moveCnt;
            int wallBreakCount = temp.wallBreakCount;
            if(r == N-1 && c == M-1) {
                answer = moveCnt;
                return ;
            }
            for(int dir=0;dir<4;dir++) {
                int nr = r + dx[dir];
                int nc = c + dy[dir];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(map[nr][nc] == '0') {
                    if(visited[nr][nc][wallBreakCount] == true) continue;
                    visited[nr][nc][wallBreakCount] = true;
                    q.offer(new Node(nr, nc, moveCnt + 1, wallBreakCount));
                }
                if(map[nr][nc] == '1') {
                    if(wallBreakCount >= K) continue;
                    if(visited[nr][nc][wallBreakCount+1] == true) continue;
                    visited[nr][nc][wallBreakCount + 1] = true;
                    q.offer(new Node(nr, nc, moveCnt + 1, wallBreakCount + 1));
                }
            }


        }

    }

    static class Node{
        int r;
        int c;
        int moveCnt;
        int wallBreakCount = 0;
        public Node(int r, int c, int moveCnt, int wallBreakCount) {
            this.r=r;
            this.c=c;
            this.moveCnt = moveCnt;
            this.wallBreakCount = wallBreakCount;

        }
    }
}

