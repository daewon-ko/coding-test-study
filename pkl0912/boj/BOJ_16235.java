package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_16235 {
    static int[][] graph, yb;
    static List<Tree> trees;
    static int[]dx = {-1,-1,0,1,1,1,0,-1};
    static int[]dy = {0,-1,-1,-1,0,1,1,1};
    static int n;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        yb = new int[n][n];
        trees = new LinkedList<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                graph[i][j] = 5;
                yb[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x-1, y-1, age));
        }
        Collections.sort(trees);
        for(int i = 0; i<k; i++){
            SpringSummer();
            FallWinter();
        }
        
        System.out.println(trees.size());

    }
    static void SpringSummer() {
        List<Tree> aliveTrees = new ArrayList<>();
        List<Tree> deadTrees = new ArrayList<>();
        
        for(Tree t: trees){
            if (graph[t.x][t.y] < t.age) {  // 양분이 부족하면
                deadTrees.add(t);
            } else {
                graph[t.x][t.y] -= t.age; // 양분 섭취
                aliveTrees.add(new Tree(t.x, t.y, t.age+1));
            }
        }
        for(Tree t: deadTrees){
            graph[t.x][t.y] += t.age/2;
        }
        trees = new LinkedList<>(aliveTrees);
        
    }
    
    static void FallWinter(){
        List<Tree> newTrees = new ArrayList<>();
        for(Tree t: trees){
            if(t.age%5==0){ //나이가 5의 배수
                for(int i = 0; i<8; i++){ 
                    int nx = t.x+dx[i];
                    int ny = t.y+dy[i];
                    if(0<=nx && nx<n && 0<=ny && ny<n){
                        newTrees.add(new Tree(nx, ny,1)); //나이가 1인 나무
                    }
                }
            }
        }
        trees.addAll(0, newTrees);
        for(int i = 0; i<n; i++){ //양분 추가
            for(int j = 0; j<n; j++){
                graph[i][j] += yb[i][j];
            }
        }
    }
}
class Tree implements Comparable<Tree>{
    int x;
    int y;
    int age;

    Tree(int x, int y, int age){
        this.x = x;
        this.y = y;
        this.age = age;
    }
    @Override
    public int compareTo(Tree ob){
        return this.age - ob.age;
    }
}
