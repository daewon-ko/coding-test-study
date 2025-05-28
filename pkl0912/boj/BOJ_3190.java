package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_3190 {
    static int[] dx = {-1,0,1,0}; //상왼하우
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for(int i = 0; i<k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            board[r][c] = -1;
        }
        Queue<String[]> switchDir = new LinkedList<>();
        Deque<int[]> mq = new LinkedList<>();
        int l = Integer.parseInt(br.readLine());
        for(int i = 0; i<l; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String time = st.nextToken();
            String dir = st.nextToken();
            switchDir.add(new String[]{time, dir});
        }
        int count = 0;
        mq.add(new int[]{0,0,3});
        board[0][0] = 1;
        while(!mq.isEmpty()){
            int[] tail = mq.peekFirst();
            int tx = tail[0];
            int ty = tail[1];
            
            int[] head = mq.peekLast();
            int hx = head[0];
            int hy = head[1];
            int hd = head[2];
            
            if(hx<0 || hx >n) break;
            

            int nx = 0;
            int nd = 0;
            int ny = 0;

            //다음 머리 이동
            if(switchDir.size()>0 && Integer.parseInt(switchDir.peek()[0])==count){ //방향 바뀔 타이밍이면
                nd = 0;
                if(switchDir.peek()[1].equals("D")) nd = (hd+3) %4;
                else nd = (hd+1)%4;
                nx = hx+dx[nd];
                ny = hy+dy[nd];
                switchDir.poll();
                if(0<=nx && nx<n && 0<=ny && ny<n && board[nx][ny]!=1){
                    mq.addLast(new int[]{nx, ny, nd});
                    count++;
                    //꼬리
                    if(board[nx][ny]==0){
                        int[] tailM = mq.pollFirst();
                        int x = tailM[0];
                        int y = tailM[1];
                        board[x][y] = 0;
                    }
                    board[nx][ny] = 1;
                }else{
                    count++;
                    break;
                }
            }else{
                nx = hx+dx[hd];
                ny = hy+dy[hd];
                if(0<=nx && nx<n && 0<=ny && ny<n && board[nx][ny]!=1) {

                    mq.addLast(new int[]{nx, ny, hd});
                    count++;
                    //꼬리
                    if(board[nx][ny]==0){
                        int[] tailM = mq.pollFirst();
                        int x = tailM[0];
                        int y = tailM[1];
                        board[x][y] = 0;
                    }
                    board[nx][ny] = 1;
                }else{
                    count++;
                    break;
                }
            }
            
            
        }
        System.out.println(count);
    }
}
