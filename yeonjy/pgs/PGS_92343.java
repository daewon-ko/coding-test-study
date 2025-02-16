package yeonjy.pgs;

public class PGS_92343 {
    static int maxSheep;

    public int solution(int[] info, int[][] edges) {
        dfs(0, new boolean[info.length], 0, 0, info, edges);
        return maxSheep;
    }

    private void dfs(int idx, boolean[] isVisited, int sheepCnt, int wolfCnt, int[] info, int[][] edges) {
        isVisited[idx] = true;

        if (info[idx] == 0) {
            sheepCnt++;
            maxSheep = Math.max(sheepCnt, maxSheep);
        } else {
            wolfCnt++;
        }

        if (sheepCnt <= wolfCnt) {
            return;
        }

        for (int[] edge : edges) {
            if (isVisited[edge[0]] && !isVisited[edge[1]]) {
                boolean[] newIsVisited = isVisited.clone();
                dfs(edge[1], newIsVisited, sheepCnt, wolfCnt, info, edges);
            }
        }
    }
}
