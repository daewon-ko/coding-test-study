package pkl0912.boj;
import java.util.*;

public class BOJ_1541 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] minus = str.split("-");
        int result = 0;
        for(int i = 0; i<minus.length; i++){
            String[] add = minus[i].split("\\+");
            int sum = 0;
            for(String num: add){
                sum += Integer.parseInt(num);
            }
            if(i==0){
                result+=sum;
            }else{
                result-=sum;
            }
        }
        System.out.println(result);
    }
}
