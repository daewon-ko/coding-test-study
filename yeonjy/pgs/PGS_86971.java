package yeonjy.pgs;

import java.util.*;

class PGS_86971 {
    static int n;
    static List<Integer>[] tree;
    static boolean[] isVisited;
    static int count;
    static int a;
    static int b;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        
        // 1. tree 만들기
        tree = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int[] wire : wires) {
            tree[wire[0]].add(wire[1]);
            tree[wire[1]].add(wire[0]);
        }
        
        // 2. 하나씩 끊기
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            isVisited = new boolean[n + 1];
            count = 1;
            a = wire[0];
            b = wire[1];
            isVisited[1] = true;
            dfs(1);
            if (Math.abs(n - count * 2) < answer) {
                answer = Math.abs(n - count * 2);
            }
        }
        
        return answer;
    }
    
    static void dfs(int now) {
        List<Integer> linked = tree[now];
        for(int next : linked) {
            if ((now == a && next == b) || (now == b && next == a) || isVisited[next]) {
                continue;
            }
            isVisited[next] = true;
            count++;
            dfs(next);
        }
    }
}
