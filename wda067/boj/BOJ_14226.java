import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
백준 / 이모티콘 / 골드4
https://www.acmicpc.net/problem/14226
 */
public class BOJ_14226 {

    private static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Status> q = new LinkedList<>();
        boolean[][] visited = new boolean[S + 1][S + 1];  //화면 개수, 클립보드 개수

        q.add(new Status(1, 0, 0));
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Status cur = q.poll();
            int screen = cur.screen;
            int clipboard = cur.clipboard;

            if (screen == S) {
                return cur.time;
            }

            //클립보드에 저장
            if (!visited[screen][screen]) {
                visited[screen][screen] = true;
                q.add(new Status(screen, screen, cur.time + 1));
            }

            //화면에 붙혀넣기
            if (clipboard > 0 && screen + clipboard <= S) {
                if (!visited[screen + clipboard][clipboard]) {
                    visited[screen + clipboard][clipboard] = true;
                    q.add(new Status(screen + clipboard, clipboard, cur.time + 1));
                }
            }

            //화면에서 하나 삭제
            if (screen > 0 && !visited[screen - 1][clipboard]) {
                visited[screen - 1][clipboard] = true;
                q.add(new Status(screen - 1, clipboard, cur.time + 1));
            }
        }

        return -1;
    }

    private static class Status {

        int screen, clipboard, time;

        public Status(int screen, int clipboard, int time) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.time = time;
        }
    }
}
