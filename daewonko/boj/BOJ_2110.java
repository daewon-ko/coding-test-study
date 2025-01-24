package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 공유기 설치
 class BOJ_2110_FAIL {
    static int n,c;
    static List<Integer> houseList = new ArrayList<>();
    static boolean [] wifiCompleted ;
    static int MAX = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());



        int listMax = 0;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            houseList.add(number);
            listMax = Math.max(listMax, number);
        }

        Collections.sort(houseList);


        // 배열의 크기가 10억인게 과연 적절한가?
        wifiCompleted = new boolean[listMax+1];


        dfs(0);
        System.out.println(MAX);

    }

    public static void dfs(int depth) {
        if (depth == c) {
            int[] arr = new int[c];
            int min = Integer.MAX_VALUE;

            int cnt = 0;
            for (int i = 0; i < houseList.size(); i++) {
                int house = houseList.get(i);
                // 와이파이가 설치되어있따면
                if (wifiCompleted[house]) {
                    arr[cnt++] = house;
                    if (cnt == 3) {
                        break;
                    }
                }
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = i; j < arr.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    min = Math.min(min, arr[j] - arr[i]);
                }
            }

            MAX = Math.max(min, MAX);

            return;
        }


        for (int i = 0; i < houseList.size(); i++) {
            if (!wifiCompleted[houseList.get(i)]) {
                wifiCompleted[houseList.get(i)] = true;
                dfs(depth + 1);
                wifiCompleted[houseList.get(i)] = false;

            }
        }

    }
}

public class BOJ_2110 {
     static int n,c;
     static int [] houses;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        houses = new int[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int low =1;
        int high = houses[n - 1] - houses[0];

        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (canPlace(mid)) {

                result = mid;
                low = mid + 1;
            }else
                high = mid - 1;
        }

        System.out.println(result);

    }

    public static boolean canPlace(int distance) {
        int start = houses[0];
        int cnt = 1;

        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - start >= distance) {
                cnt++;
                start = houses[i];
            }

            if (cnt >= c) {
                return true;
            }
        }
        return false;
    }


}
