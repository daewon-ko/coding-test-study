import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/*
프로그래머스 / 등산코스 정하기 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/118669
 */
class PGS_118669 {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            graph.get(from).add(new int[]{to, weight});
            graph.get(to).add(new int[]{from, weight});
        }

        Set<Integer> gateSet = new HashSet<>();
        for (int gate : gates) {
            gateSet.add(gate);
        }

        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1.intensity));

        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.index;
            int nowIntensity = current.intensity;

            if (intensity[now] < nowIntensity) {
                continue;
            }
            if (summitSet.contains(now)) {
                continue;
            }

            for (int[] next : graph.get(now)) {
                int nextNode = next[0];
                int weight = next[1];
                int maxIntensity = Math.max(intensity[now], weight);

                if (intensity[nextNode] > maxIntensity) {
                    intensity[nextNode] = maxIntensity;
                    pq.offer(new Node(nextNode, maxIntensity));
                }
            }
        }

        int minSummit = 0;
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }

        return new int[]{minSummit, minIntensity};
    }

    private static class Node {
        int index;
        int intensity;

        public Node(int index, int intensity) {
            this.index = index;
            this.intensity = intensity;
        }
    }
}
