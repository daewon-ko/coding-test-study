package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_2457 {
    static class Date implements Comparable<Date> {
        int start, end;
        Date(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Date ob) {
            if (this.start == ob.start) return ob.end - this.end;
            return this.start - ob.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Date> flowers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken()); 
            int sd = Integer.parseInt(st.nextToken()); 
            int em = Integer.parseInt(st.nextToken()); 
            int ed = Integer.parseInt(st.nextToken()); 
            
            int start = sm * 100 + sd;
            int end = em * 100 + ed;
            
            flowers.add(new Date(start, end));
        }

        Collections.sort(flowers);

        int cnt = 0; // 필요한 꽃의 개수
        int startDate = 301; // 기준 시작 날짜 
        int endDate = 1201; // 기준 종료 날짜 
        int maxEnd = 0;
        int idx = 0;

        while (startDate < endDate) {
            boolean found = false;

            // 현재 startDate 이전에 시작하는 꽃 중에서 가장 늦게 끝나는 꽃 선택
            while (idx < n && flowers.get(idx).start <= startDate) {
                maxEnd = Math.max(maxEnd, flowers.get(idx).end);
                idx++;
                found = true;
            }

            if (!found) {
                System.out.println(0);
                return;
            }
            cnt++;
            startDate = maxEnd;


            if (startDate >= endDate) {
                System.out.println(cnt);
                return;
            }
        }

        System.out.println(0);
    }
}
