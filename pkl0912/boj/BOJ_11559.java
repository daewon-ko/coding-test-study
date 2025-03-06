package pkl0912.boj;
import java.io.*;
import java.util.*;
public class BOJ_11559 {
    static char[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int count = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new char[12][6];
        for(int i = 0; i<12; i++){
            String s = br.readLine();
            for(int j = 0; j<s.length(); j++){
                graph[i][j] = s.charAt(j);
            }
        }
        while (true) {
            visited = new boolean[12][6];
            boolean isPuyoPopped = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (graph[i][j] != '.' && !visited[i][j]) {
                        if (bfs(i, j)) {
                            isPuyoPopped = true;
                        }
                    }
                }
            }

            if (!isPuyoPopped) {
                break;
            }

            gravity();
            count++;
        }

    }  
    static boolean bfs(int x , int y){
        Queue<int[]> q = new LinkedList<>(); 
        List<int[]> toPop = new ArrayList<>();
        char color = graph[x][y];
        q.add(new int[]{x,y});
        visited[x][y] = true;
        toPop.add(new int[]{x, y});

        for(int i = 0; i<12; i++){
            for(int j = 0; j<6; j++){
                if(graph[i][j]!= '.'){
                    
                }
            }
        }

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int px = pos[0], py = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 &&
                        !visited[nx][ny] && graph[nx][ny] == color) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    toPop.add(new int[]{nx, ny});
                }
            }
        }

        if (toPop.size() >= 4) {
            for (int[] pos : toPop) {
                graph[pos[0]][pos[1]] = '.';
            }
            return true;
        }
        return false;
    }
    static void gravity(){
        for(int j = 0; j<6; j++){
            for(int i = 11; i>=0; i--){
                int ni = i;
                while(ni+1<12 && graph[ni+1][j]=='.'){
                    graph[ni+1][j] = graph[ni][j];
                    graph[ni][j] = '.';
                    ni++;
                }
            }
        }
    }
}
