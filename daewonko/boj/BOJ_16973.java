package daewonko.boj;

import java.util.*;

//백준 직사각형 탈출
public class BOJ_16973 {


        static int N, M, H, W, Sr, Sc, Fr, Fc;
        static int[][] grid;
        static boolean[][] visited;

        static int[] dr = {-1, 1, 0, 0}; // 상하좌우 이동
        static int[] dc = {0, 0, -1, 1};

        public static void main (String[]args){
            Scanner sc = new Scanner(System.in);

            // 입력 받기
            N = sc.nextInt();
            M = sc.nextInt();
            grid = new int[N][M];

            // 격자 정보 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            // 직사각형 크기 및 시작, 목표 지점 입력
            H = sc.nextInt();
            W = sc.nextInt();
            Sr = sc.nextInt() - 1; // 1-indexed → 0-indexed 변환
            Sc = sc.nextInt() - 1;
            Fr = sc.nextInt() - 1;
            Fc = sc.nextInt() - 1;

            visited = new boolean[N][M];

            // 시작 위치가 벽을 포함하면 -1 반환
            if (!canMove(Sr, Sc)) {
                System.out.println(-1);
                return;
            }

            // BFS 탐색 시작
            System.out.println(bfs());
        }

        static int bfs () {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{Sr, Sc, 0});
            visited[Sr][Sc] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1], moves = cur[2];

                // 목표 지점 도달
                if (r == Fr && c == Fc) {
                    return moves;
                }


                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];


                    if (inRange(nr, nc) && !visited[nr][nc] && canMove(nr, nc)) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc, moves + 1});
                    }
                }
            }
            return -1; // 목표 지점 도달 불가
        }


        static boolean canMove ( int r, int c){
            if (!inRange(r, c)) return false;
            if (r + H - 1 >= N || c + W - 1 >= M) return false; // 직사각형이 격자 밖으로 나가는 경우

            // 직사각형 내부에 벽있는지 검사
            for (int i = r; i < r + H; i++) {
                for (int j = c; j < c + W; j++) {
                    if (grid[i][j] == 1) return false;
                }
            }
            return true;
        }


        static boolean inRange(int r, int c){
            return r >= 0 && c >= 0 && r < N && c < M;
        }
    }



