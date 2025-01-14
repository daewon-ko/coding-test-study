package pkl0912.BOJ;
import java.util.*;
import java.io.*;
public class BOJ_1074{
    static int count;
    static int r;
    static int c;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        int size = (int)Math.pow(2, n);
        count = 0;
        square(size, r, c);
        System.out.println(count);
    }
    public static void square(int size, int r, int c){
        if(size==1) return;
        if(r<size/2 && c<size/2){
            square(size/2, r, c);
        }
        else if(r<size/2 && c>=size/2){
            count+=size*size/4;
            square(size/2, r, c-size/2);
        }
        else if(r>=size/2 && c<size/2){
            count+=size*size/4*2;
            square(size/2, r-size/2, c);
        }else{
            count+=size*size/4*3;
            square(size/2, r-size/2, c-size/2);
        }
        
    }
}