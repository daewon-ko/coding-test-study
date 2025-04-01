package yeonjy.pgs;

class PGS_388352 {
    int count = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        
        pickNumber(1, 1, n, q, ans, new boolean[n+1]);
        answer = count;
        
        return answer;
    }
    
    private void pickNumber(int position, int min, int n, int[][] q, int[] ans, boolean[] pickedNumbers) {
        if(position > 5) {
            for(int o=0;o < q.length;o++) {
                int matchCnt = 0;

                for(int p=0;p < 5;p++) {
                    if(matchCnt > ans[o]) break;
                    if(pickedNumbers[q[o][p]]) matchCnt++;
                }

                if(ans[o] != matchCnt) return;
            }

            count++;
            return;
        }
        
        for(int i=min;i<=n;i++) {
            pickedNumbers[i] = true;
            pickNumber(position+1, i+1, n, q, ans, pickedNumbers);
            pickedNumbers[i] = false;
        }
    }
}
