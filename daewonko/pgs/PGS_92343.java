package daewonko.pgs;

import java.util.*;
public class PGS_92343 {


    static int maxSheep = 0;

    public static int solution(int[] info, int[][] edges) {
        List<Integer>[] tree = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) tree[i] = new ArrayList<>();
        for (int[] edge : edges) tree[edge[0]].add(edge[1]);

        List<Integer> available = new ArrayList<>();
        available.add(0);
        dfs(0, 1, 0, tree, info, available);

        return maxSheep;
    }

    static void dfs(int node, int sheep, int wolves, List<Integer>[] tree, int[] info, List<Integer> available) {
        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> nextAvailable = new ArrayList<>(available);
        nextAvailable.remove(Integer.valueOf(node));

        for (int child : tree[node]) {
            nextAvailable.add(child);
        }

        for (int nextNode : nextAvailable) {
            if (info[nextNode] == 0) {
                dfs(nextNode, sheep + 1, wolves, tree, info, nextAvailable);
            } else if (sheep > wolves + 1) {
                dfs(nextNode, sheep, wolves + 1, tree, info, nextAvailable);
            }
        }
    }
}


