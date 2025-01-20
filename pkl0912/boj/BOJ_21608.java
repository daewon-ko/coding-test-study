package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_21608 {
    static int n;
    static List<int[]> arr; 
    static int[][] graph; 
    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, -1, 0, 1}; 
    static int[][] likeCount; 
    static int[][] emptyCount; 
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        graph = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            arr.add(new int[]{p, a, b, c, d});
        }
        for (int i = 0; i < n * n; i++) {
            placeStudent(i);
        }
        calculateSatisfaction();
    
        System.out.println(answer);
    }

    public static void placeStudent(int index) {
        int person = arr.get(index)[0];
        int[] likes = Arrays.copyOfRange(arr.get(index), 1, 5);

        PriorityQueue<Seat> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 0) continue; 

                int like = 0;
                int empty = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    if (graph[nx][ny] == 0) {
                        empty++;
                    } else {
                        for (int t : likes) {
                            if (graph[nx][ny] == t) {
                                like++;
                                break;
                            }
                        }
                    }
                }

                pq.add(new Seat(i, j, like, empty));
            }
        }

        Seat bestSeat = pq.poll(); 
        graph[bestSeat.x][bestSeat.y] = person; 
    }


    public static void calculateSatisfaction() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int person = graph[i][j];
                int[] likes = null;

                for (int[] student : arr) {
                    if (student[0] == person) {
                        likes = Arrays.copyOfRange(student, 1, 5);
                        break;
                    }
                }

                int like = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    for (int t : likes) {
                        if (graph[nx][ny] == t) {
                            like++;
                            break;
                        }
                    }
                }

                if (like == 1) answer += 1;
                else if (like == 2) answer += 10;
                else if (like == 3) answer += 100;
                else if (like == 4) answer += 1000;
            }
        }
    }
}

class Seat implements Comparable<Seat> {
    int x, y; 
    int likeCount; 
    int emptyCount; 

    public Seat(int x, int y, int likeCount, int emptyCount) {
        this.x = x;
        this.y = y;
        this.likeCount = likeCount;
        this.emptyCount = emptyCount;
    }

    @Override
    public int compareTo(Seat ob) {
        if (this.likeCount != ob.likeCount) return ob.likeCount - this.likeCount; 
        if (this.emptyCount != ob.emptyCount) return ob.emptyCount - this.emptyCount; 
        if (this.x != ob.x) return this.x - ob.x; 
        return this.y - ob.y;
    }
}

