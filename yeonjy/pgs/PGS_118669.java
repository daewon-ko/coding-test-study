package yeonjy.pgs;

import java.util.*;

class PGS_118669 {
    static final int INF = 987654321;
  
    public class Point implements Comparable<Point> {
        int n, c;

        public Point (int n, int c) {
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Point t) {
            return this.c - t.c;
        }
    }

    public List<List<Point>> map = new ArrayList<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        boolean[] isTop = new boolean[n+1];
        Arrays.sort(summits);
        for (int i = 0; i <= n; i++)
            map.add(new ArrayList<>());
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int c = paths[i][2];
            map.get(from).add(new Point(to, c));
            map.get(to).add(new Point(from, c));
        }
        for (int i = 0; i < summits.length; i++)
            isTop[summits[i]] = true;

        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < gates.length; i++) {
            dist[gates[i]] = 0;
            pq.add(new Point(gates[i], 0));
        }
        while(!pq.isEmpty()) {
            Point cur = pq.poll();
            if (isTop[cur.n]) continue;
            if (dist[cur.n] < cur.c) continue;
            for (int i = 0; i < map.get(cur.n).size(); i++) {
                int nextN = map.get(cur.n).get(i).n;
                int nextC = Math.max(map.get(cur.n).get(i).c, cur.c);
                if (dist[nextN] > nextC) {
                    dist[nextN] = nextC;
                    pq.add(new Point(nextN, nextC));
                }
            }
        }

        int resC = INF;
        int resT = 0;
        for (int i = 0; i < summits.length; i++) {
            if (dist[summits[i]] < resC) {
                resC = dist[summits[i]];
                resT = summits[i];
            }
        }
        return new int[]{resT, resC};
    }
}
