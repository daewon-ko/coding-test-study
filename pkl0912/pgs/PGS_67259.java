package pkl0912.pgs;

import java.util.*;

public class PGS_67259 {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    int n = 0;
    int[][][] cost;
    public int solution(int[][] board) {
        n = board.length;
        cost = new int[n][n][4];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        bfs(board);
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i<4; i++){
            answer = Math.min(answer, cost[n-1][n-1][i]);
        }
        return answer;
    }
    void bfs(int[][]board){
        Queue<int[]> q = new LinkedList<>();
        for(int d = 0; d<4; d++){
            q.add(new int[]{0,0,d,0});
            cost[0][0][d] = 0;
        }
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            int d = pos[2];
            int c = pos[3];

            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<n && board[nx][ny]!=1){         
                    int newCost = c+(d==i?100:600);
                    
                    if(cost[nx][ny][i]>newCost){
                        cost[nx][ny][i] = newCost;
                        q.add(new int[]{nx, ny, i, newCost});
                    }
                }
            }
        }
    }
}