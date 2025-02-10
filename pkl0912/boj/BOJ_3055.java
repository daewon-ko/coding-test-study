package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_3055 {
    static int r;
    static int c;
    static Character [][]graph;
    static List<int[]> waters = new ArrayList<>();
    static int[] beaver;
    static int[] gosm;
    static List<int[]> rocks = new ArrayList<>();
    static List<int[]> empties= new ArrayList<>();
    static int[]dx = {-1,0,1,0};
    static int[]dy = {0,-1,0,1};
    static int[][] waterdist;
    static int[][] gosmdist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new Character[r][c];
        waterdist = new int[r][c];
        gosmdist = new int[r][c];
        gosm = new int[2];

        for(int i = 0; i<r; i++){
            String sentence = br.readLine();
            for(int j = 0; j<c; j++){
                Character s = sentence.charAt(j);
                if(s=='*') waters.add(new int[]{i,j});
                else if(s=='D') beaver = new int[]{i,j};
                else if(s=='S'){
                    gosm[0] = i;
                    gosm[1] = j;
                }
                else if(s=='X') rocks.add(new int[]{i,j});
                else empties.add(new int[]{i,j});

                graph[i][j] = s;
                waterdist[i][j] = -1;
                gosmdist[i][j] = -1;
            }
        }
        moveWater();
        moveGosm();
        System.out.println("KAKTUS");
        
    }
    public static void moveGosm(){
        Queue<int[]>q = new LinkedList<>();
        q.add(gosm);
        gosmdist[gosm[0]][gosm[1]] = 0;
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x =  pos[0];
            int y = pos[1];
            if(graph[x][y]=='D'){ //비버 집이면 끝
                System.out.println(gosmdist[x][y]);
                System.exit(0);
            }
            for(int i = 0; i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<r && 0<=ny && ny<c && graph[nx][ny]!='X' && (waterdist[nx][ny] == -1 || gosmdist[x][y] + 1 < waterdist[nx][ny]) && gosmdist[nx][ny]==-1){
                    q.add(new int[]{nx, ny});
                    gosmdist[nx][ny] = gosmdist[x][y]+1;
                }
            }
        }
        
    }
    public static void moveWater(){
        Queue<int[]>q = new LinkedList<>();
        for(int[] w: waters){
            q.add(w);
            waterdist[w[0]][w[1]] = 0;
        }

        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x =  pos[0];
            int y = pos[1];
            for(int i = 0; i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<r && 0<=ny && ny<c && graph[nx][ny]!='X' && graph[nx][ny]!='D' && waterdist[nx][ny]==-1){
                    q.add(new int[]{nx, ny});
                    waterdist[nx][ny] = waterdist[x][y]+1;
                }
            }
        }
        
    }
}
