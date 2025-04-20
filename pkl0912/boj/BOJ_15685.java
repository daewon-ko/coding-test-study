package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_15685 {
	static private int n;
	static private final boolean[][] map = new boolean[101][101];
	static private List<Integer> dirList;

	static private int[] dx={1,0,-1,0};
	static private int[] dy={0,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dirList = new LinkedList<Integer>();
			addDirAll(d,g);
			drawDragon(x,y);
		}
		
		int ans = checkSquare();
		System.out.println(ans);
		
	}
	
	public static void addDirAll(int d, int g) {
		dirList.add(d);
		
		for(int i=1; i<=g; i++) {
			for(int j=dirList.size()-1; j>=0; j--) {
				dirList.add((dirList.get(j)+1)%4);
			}
		}
		
	}
	
	public static void drawDragon(int x, int y) {
		map[x][y] = true;
		
		int nx=x , ny=y;
		for(int i=0; i<dirList.size(); i++) {
			int d = dirList.get(i);
			
			nx += dx[d];
			ny += dy[d];
			
			map[nx][ny] = true;
		}
	}
	
	public static int checkSquare() {
		int cnt=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) cnt++;
			}
		}
		
		return cnt;
	}
}

