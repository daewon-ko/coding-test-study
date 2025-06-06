package pkl0912.pgs;

import java.util.*;

class Point {
	int x;
	int y;

	Point(int a, int b) {
		x = a;
		y = b;
	}
}

class Solution {
	int[][] map;
	final int SIZE = 4;
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};
	int answer;
	int R, C;
	Point[][] points;
	boolean[] selected;
	boolean[] exists;
	int numCard;

	public int solution(int[][] board, int r, int c) {
		// init
		answer = Integer.MAX_VALUE;
		R = r;
		C = c;
		map = board;
		points = new Point[6 + 1][2];
		exists = new boolean[6 + 1];
		selected = new boolean[6 + 1];

		setCardInfomation();

		search(0, 0, new Point(r, c));

		return answer;
	}

	private void search(int value, int depth, Point current) {
		if (depth == numCard) {
			answer = Math.min(answer, value);
			return;
		}
		for (int i = 1; i <= numCard; ++i) {

			if (!selected[i] && exists[i]) {
				selected[i] = true;

			
				for (int j = 0; j < 2; ++j) {
					Point first = points[i][j];
					Point second = points[i][(j + 1) % 2];
					int cost = getMoveCount(current, first) + getMoveCount(first, second) + 2;
					map[first.x][first.y] = 0;
					map[second.x][second.y] = 0;

					
					search(value + cost, depth + 1, second);

					map[first.x][first.y] = i;
					map[second.x][second.y] = i;
				}
				selected[i] = false;
			}
		}
	}

	private int getMoveCount(Point start, Point end) {
		Queue<Point> q = new ArrayDeque();
		int[][] visit = new int[SIZE][SIZE];
		q.add(start);
		visit[start.x][start.y] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.x == end.x && cur.y == end.y)
				return visit[cur.x][cur.y] - 1;


			for (int d = 0; d < SIZE; ++d) {
				int nextX = cur.x + dx[d];
				int nextY = cur.y + dy[d];
				if (isInRange(nextX, nextY) && visit[nextX][nextY] == 0) {
					visit[nextX][nextY] = visit[cur.x][cur.y] + 1;
					q.add(new Point(nextX, nextY));
				}
			}

	
			for (int d = 0; d < 4; ++d) {
				int nextX = cur.x;
				int nextY = cur.y;

				while (isInRange(nextX, nextY)) {
					nextX = nextX + dx[d];
					nextY = nextY + dy[d];

					if (!isInRange(nextX, nextY)) {
						nextX = nextX - dx[d];
						nextY = nextY - dy[d];
						break;
					}
					if (map[nextX][nextY] != 0)
						break;
				}

				if (visit[nextX][nextY] == 0) {
					visit[nextX][nextY] = visit[cur.x][cur.y] + 1;
					q.add(new Point(nextX, nextY));
				}
			}
		}

		return visit[end.x][end.y] - 1;
	}

	public void setCardInfomation() {

		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				int value = map[i][j];
				if (value != 0) {
					if (!exists[value]) {
						exists[value] = true;
						numCard++;
						points[value][0] = new Point(i, j);
					} else {
						points[value][1] = new Point(i, j);
					}
				}
			}
		}
	}

	public boolean isInRange(int x, int y) {
		if (0 <= x && x < SIZE && 0 <= y && y < SIZE)
			return true;
		return false;
	}
}