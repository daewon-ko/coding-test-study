package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_8983{
    static int L;
    static int n; 
    static int[] square;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        square = new int[n];
        for(int i = 0; i<n; i++){
            square[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(square);
        List<Animal> animals = new ArrayList<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animals.add(new Animal(x, y));
        }    
        int cnt = 0;
        for(Animal animal: animals){
            if(canCatch(animal.x,animal.y)) cnt++;
        }
        System.out.println(cnt);
    }
    static boolean canCatch(int x, int y){
        int lt = 0;
        int rt = n-1;

        while(lt<rt){
            int mid = (lt+rt) / 2;
            if(square[mid]<x){
                lt = mid+1;
            }else{
                rt = mid;
            }
        }
        int close = square[lt];
        if(lt>0 && Math.abs(square[lt-1]-x)<Math.abs(square[lt]-x)) close = square[lt-1];
        int dist = Math.abs(close - x);
        if(y<=L-dist) return true;
        else return false;
    }
}
class Animal implements Comparable<Animal>{
    int x;
    int y;
    Animal(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Animal ob){
        if(this.x==ob.x) return this.y-ob.y;
        else return this.x - ob.x;
    }
}