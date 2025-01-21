package pkl0912.pgs;

public class PGS_77485{
    class Solution {
        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];
            int[][] graph = new int[rows][columns];
    
            int num = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    graph[i][j] = num++;
                }
            }
    
            for (int q = 0; q < queries.length; q++) {
                int x1 = queries[q][0] - 1;
                int y1 = queries[q][1] - 1;
                int x2 = queries[q][2] - 1;
                int y2 = queries[q][3] - 1;
    
                int prev = graph[x1][y1];
                int min = prev;
    
                for (int i = y1+1; i <= y2; i++) {
                    min = Math.min(min, prev);
                    int tmp = graph[x1][i];
                    graph[x1][i] = prev;
                    prev = tmp;              
                }

                for (int i = x1+1; i <= x2; i++) {
                    min = Math.min(min, prev);
                    int tmp = graph[i][y2];
                    graph[i][y2] = prev;
                    prev = tmp;      
                }
                for (int i = y2-1; i >= y1; i--) {
                    min = Math.min(min, prev);
                    int tmp = graph[x2][i];
                    graph[x2][i] = prev;
                    prev = tmp;  
                }
    
                for (int i = x2-1; i >= x1; i--) {
                    min = Math.min(min, prev);
                    int tmp = graph[i][y1];
                    graph[i][y1] = prev;
                    prev = tmp;  
                }
                answer[q] = min;
            }
    
            return answer;
        }
    }
}