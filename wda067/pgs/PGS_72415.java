import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
프로그래머스 / 카드 짝 맞추기 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/72415
 */
class PGS_72415 {

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[][] board;
    int r, c;

    Map<Integer, List<Integer>> cardPos = new HashMap<>();  //카드 번호 -> 위치 2개
    int min = Integer.MAX_VALUE;
    List<Integer> cards;

    public int solution(int[][] board, int r, int c) {
        this.board = board;
        this.r = r;
        this.c = c;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int card = board[i][j];
                if (card != 0) {
                    cardPos.computeIfAbsent(card, k -> new ArrayList<>())
                            .addAll(List.of(i, j));
                }
            }
        }

        cards = new ArrayList<>(cardPos.keySet());
        permutation(0, new boolean[cardPos.size()], new ArrayList<>());

        return min;
    }

    //카드 순열 생성
    void permutation(int depth, boolean[] visited, List<Integer> order) {
        if (depth == cards.size()) {  //카드의 순서를 정했을 경우 탐색 시작
            dfs(0, r, c, board, order, 0);
            return;
        }

        for (int i = 0; i < cards.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                order.add(cards.get(i));
                permutation(depth + 1, visited, order);
                visited[i] = false;
                order.remove(order.size() - 1);
            }
        }
    }

    void dfs(int index, int r, int c, int[][] board, List<Integer> order, int cost) {
        if (cost >= min) {  //가지치기
            return;
        }

        if (index == order.size()) {  //마지막 카드까지 탐색 완료
            min = Math.min(min, cost);
            return;
        }

        int card = order.get(index);  //현재 카드 번호

        int[][] clone = new int[4][4];
        for (int i = 0; i < 4; i++) {
            clone[i] = board[i].clone();
        }

        int sr = cardPos.get(card).get(0);
        int sc = cardPos.get(card).get(1);
        int er = cardPos.get(card).get(2);
        int ec = cardPos.get(card).get(3);

        //(sr, sc) -> (er, ec)
        int d1 = bfs(r, c, sr, sc, clone);  //(r, c) -> (sr, sc)
        d1++;  //엔터
        d1 += bfs(sr, sc, er, ec, clone);  //(sr, sc) -> (er, ec)
        d1++;  //엔터
        clone[sr][sc] = clone[er][ec] = 0;  //카드 뒤집기
        dfs(index + 1, er, ec, clone, order, cost + d1);

        for (int i = 0; i < 4; i++) {
            clone[i] = board[i].clone();
        }

        //(er, ec) -> (sr, sc)
        int d2 = bfs(r, c, er, ec, clone);  //(r, c) -> (er, ec)
        d2++;  //엔터
        d2 += bfs(er, ec, sr, sc, clone);  //(er, ec) -> (sr, sc)
        d2++;  //엔터
        clone[sr][sc] = clone[er][ec] = 0;  //카드 뒤집기
        dfs(index + 1, sr, sc, clone, order, cost + d2);
    }

    //(sr, sc) -> (tr, tc) 최소 이동 경로 탐색
    int bfs(int sr, int sc, int tr, int tc, int[][] board) {
        if (sr == tr && sc == tc) {
            return 0;
        }

        boolean[][] visited = new boolean[4][4];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            for (int dir = 0; dir < 4; dir++) {
                //한 칸 이동
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
                    continue;
                }

                if (!visited[nr][nc]) {
                    if (nr == tr && nc == tc) {
                        return dist + 1;
                    }
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, dist + 1});
                }

                //컨트롤 이동
                int cr = r;
                int cc = c;

                while (true) {
                    int nrCtrl = cr + dr[dir];
                    int ncCtrl = cc + dc[dir];

                    if (nrCtrl < 0 || nrCtrl >= 4 || ncCtrl < 0 || ncCtrl >= 4) {
                        break;
                    }

                    cr = nrCtrl;
                    cc = ncCtrl;

                    if (board[cr][cc] != 0) {  //카드 발견
                        break;
                    }
                }

                if (!visited[cr][cc]) {
                    if (cr == tr && cc == tc) {
                        return dist + 1;
                    }
                    visited[cr][cc] = true;
                    q.add(new int[]{cr, cc, dist + 1});

                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
