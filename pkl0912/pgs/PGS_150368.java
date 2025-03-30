package pkl0912.pgs;

import java.util.*;

public class PGS_150368 {
    public int[] solution(int[][] users, int[] emoticons) {
        int n = emoticons.length;
        int[] answer = new int[2];
        PriorityQueue<Result> q = new PriorityQueue<>();
        
        dfs(0, n, users, emoticons, new ArrayList<>(), q);
        
        Result result = q.poll();
        answer[0] = result.person;
        answer[1] = result.amount;
        return answer;
    }

    public void dfs(int cnt, int n, int[][] users, int[] emoticons, List<Integer> selected, PriorityQueue<Result> q) {
        if (cnt == n) {
            int person = 0;
            int amount = 0;
            
            for (int[] user : users) {
                int discountThreshold = user[0];
                int purchaseThreshold = user[1];
                
                int total = 0;
                for (int i = 0; i < n; i++) {
                    int discount = selected.get(i);
                    if (discount >= discountThreshold) {
                        int discountedPrice = (int)(emoticons[i] * (1 - (double) discount / 100));
                        total += discountedPrice;
                    }
                }

                if (total >= purchaseThreshold) {
                    person++;
                } else {
                    amount += total;
                }
            }

            q.add(new Result(person, amount));
            return;
        }

        for (int discount = 10; discount <= 40; discount += 10) {
            selected.add(discount);
            dfs(cnt + 1, n, users, emoticons, selected, q);
            selected.remove(selected.size() - 1);
        }
    }
}

class Result implements Comparable<Result> {
    int person;
    int amount;

    Result(int person, int amount) {
        this.person = person;
        this.amount = amount;
    }

    @Override
    public int compareTo(Result o) {
        if (this.person == o.person) return o.amount - this.amount;
        return o.person - this.person;
    }
}
