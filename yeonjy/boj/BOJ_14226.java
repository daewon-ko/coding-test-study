package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14226 {
    static class Emoticon {
        int clipboard;
        int display;
        int time;

        public Emoticon(int clipboard, int display, int time) {
            this.clipboard = clipboard;
            this.display = display;
            this.time = time;
        }
    }

    static int S;
    static boolean[][] isVisited = new boolean[2001][2001];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        bfs();
        System.out.println(result);
    }

    public static void bfs() {
        Queue<Emoticon> q = new LinkedList<>();
        q.add(new Emoticon(0, 1, 0));

        while (!q.isEmpty()) {
            Emoticon start = q.poll();
            if (start.display == S) {
                result = start.time;
                break;
            }

            // 현재 화면의 이모티콘 클립보드에 복사
            if (!isVisited[start.display][start.display]) {
                isVisited[start.display][start.display] = true;
                q.add(new Emoticon(start.display, start.display, start.time + 1));
            }

            // 클립보드에 있는 이모티콘 붙여넣기
            if (start.clipboard != 0 && start.display + start.clipboard <= 2000 &&
                    !isVisited[start.display + start.clipboard][start.clipboard]) {
                    isVisited[start.display + start.clipboard][start.clipboard] = true;
                    q.add(new Emoticon(start.clipboard, start.display + start.clipboard, start.time + 1));

            }

            // 이모티콘 1개 삭제
            if (start.display > 0 && !isVisited[start.display - 1][start.clipboard]) {
                isVisited[start.display - 1][start.clipboard] = true;
                q.add(new Emoticon(start.clipboard, start.display - 1, start.time + 1));
            }
        }
    }

}
