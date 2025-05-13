package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_22944{
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, H, D;
	static int answer = -1;
	static char[][] board;
	static int[][] visit; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		board = new char[N][N];
		visit = new int[N][N];

		int startR = 0, startC = 0;
		boolean flag = false;

		for(int i=0;i<N;i++) {
			board[i] = br.readLine().toCharArray();
			if(!flag)
				for(int j=0;j<N;j++) {
					if(board[i][j] == 'S') {
						startR = i;
						startC = j;
						flag = true;
					}
				}
		}
		
		BFS(startR, startC);
		System.out.println(answer);

	}

	static void BFS(int startR, int startC) {
		Queue<Rain> q = new LinkedList<>();
		q.offer(new Rain(startR, startC, H, 0, false, 0));
		visit[startR][startC] = H;

		while(!q.isEmpty()) {
			Rain now = q.poll();

			for(int i=0;i<4;i++) {
				int nextR = now.r + dir[i][0];
				int nextC = now.c + dir[i][1];

				if(nextR < 0 || nextR >= N || nextC < 0   || nextC >= N)
					continue;

				if(board[nextR][nextC] == 'U') { 
					if(visit[nextR][nextC] >= now.h + D - 1)
						continue;
					visit[nextR][nextC] = D - 1 + now.h;
					q.offer(new Rain(nextR, nextC, now.h, now.cnt + 1, true, D - 1));
				}
				else if(board[nextR][nextC] == '.') { 
					if(now.umbrella) { 
						if(visit[nextR][nextC] >= now.h + now.umbrellaH - 1) 
							continue;
						if(now.umbrellaH - 1 == 0) 
							q.offer(new Rain(nextR, nextC, now.h, now.cnt + 1, false, 0));
						else 
							q.offer(new Rain(nextR, nextC, now.h, now.cnt + 1, true, now.umbrellaH - 1));
						visit[nextR][nextC] = now.h + now.umbrellaH - 1;
					}
					else { 
						if(now.h - 1 == 0 || visit[nextR][nextC] >= now.h - 1) 
							continue;
						visit[nextR][nextC] = now.h - 1;
						q.offer(new Rain(nextR, nextC, now.h - 1, now.cnt + 1, false, 0));
					}
				}
				else if(board[nextR][nextC] == 'E') {
					answer = now.cnt + 1;
					return;
				}
			}
		}
	}

	static class Rain {
		boolean umbrella = false; 
		int umbrellaH = 0; 
		int r, c, h; 
		int cnt; 

		Rain(int r, int c, int h, int cnt, boolean umbrella, int umbrellaH) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.cnt = cnt;
			this.umbrella = umbrella;
			this.umbrellaH = umbrellaH;
		}
	}

}
