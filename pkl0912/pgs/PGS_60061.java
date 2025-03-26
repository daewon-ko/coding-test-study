package pkl0912.boj;

class BOJ_60061 {
    boolean[][] pillar;
    boolean[][] beam;
    int count;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        pillar = new boolean[n+1][n+1];
        beam = new boolean[n+1][n+1];
        
        for(int[] b:build_frame){
            int c = b[0];
            int r = b[1];
            int type = b[2];
            int constructionType = b[3];
            if(type==0){ //기둥
                if(constructionType==1 && canConstructPillar(r, c)){
                    pillar[r][c] = true;
                    count++;      
                }
                else if(constructionType==0){
                    pillar[r][c] = false;
                    if(!canDelete(n)){
                        pillar[r][c] = true;
                    }
                    else{
                        count--;
                    }
                }
            }
            else{ //보
                if(constructionType==1 && canConstructBeam(r, c)){
                    beam[r][c] = true;
                    count++;
                }
                else if(constructionType==0){
                    beam[r][c] = false;
                    if(!canDelete(n)){
                        beam[r][c] = true;
                    }
                    else{
                        count--;
                    }
                }
            }
        }
        
        answer = new int[count][3];
        int idx = 0;
        for(int c=0; c<=n; c++){
            for(int r=0; r<=n; r++){
                if(pillar[r][c]){
                    answer[idx][0] = c;
                    answer[idx][1] = r;
                    answer[idx++][2] = 0;
                }
                if(beam[r][c]){
                    answer[idx][0] = c;
                    answer[idx][1] = r;
                    answer[idx++][2] = 1;
                }
            }
        }
        
        return answer;
    }
    
    public boolean canDelete(int n){
        
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(pillar[i][j] && !canConstructPillar(i, j)) return false;
                if(beam[i][j] && !canConstructBeam(i, j)) return false;
            }
        }
        return true;
    }
    
    public boolean canConstructPillar(int r, int c){
        return r==0 || (r>0 && pillar[r-1][c]) || (c>0 && beam[r][c-1]) || beam[r][c];
    }
    
    public boolean canConstructBeam(int r, int c){
        return (r>0 && pillar[r-1][c]) || (r>0 && pillar[r-1][c+1]) || (c>0 && beam[r][c-1] && beam[r][c+1]);
    }

}