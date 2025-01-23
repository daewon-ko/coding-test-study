package pkl0912.boj;
import java.util.*;

public class BOJ_9663 {
    static int n;
    static int answer;
    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dfs(0, new int[n][n]);
        System.out.println(answer);

    }
    public static void dfs(int row, int[][]board){
        if(row==n){
            answer++;
            return;
        }
        for(int i = 0; i<n;i++){
            if(canPlace(row, i,board)){
                board[row][i] = 1;
                dfs(row+1, board);
                board[row][i]= 0;
            }
        }
            


    }
    public static boolean canPlace(int row, int col,  int[][] board){
        if(row==0) return true;
        for(int i = 0; i<row; i++){
            if(board[i][col]==1) return false;
        }
        for(int i = 1; i<row+1; i++){
            if(col-i>=0 && board[row-i][col-i]==1) return false;
        }
        for(int i = 1; i<row+1; i++){
            if(col+i<n && board[row-i][col+i]==1) return false;
        }
        return true;
    }
}
