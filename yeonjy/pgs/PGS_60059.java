package yeonjy.pgs;

class PGS_60059 {
    static int len;
    static int[][] key;
    static int[][] map;
    
    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        
        int keyLen = key.length;
        int lockLen = lock.length;
        
        // 1) map 확장
        len = lockLen + keyLen * 2 - 2;
        map = new int[len][len];
        for (int i = keyLen - 1; i < keyLen + lockLen - 1; i++) {
            for (int j = keyLen - 1; j < keyLen + lockLen - 1; j++) {
                map[i][j] = lock[i - keyLen + 1][j - keyLen + 1];
            }
        }
        
        // 2) 90도씩 회전 (4회 회전)
        for (int i = 0; i < 4; i++) {
            // 3) 열쇠 모든 위치 가능한지 탐색
            if (checkFit(lockLen)) {
                return true;
            }
            rotateKey();
        }
    
        return false;
    }
    
    static boolean checkFit(int lockLen) {
        int keyLen = key.length;
        for(int i = 0; i < len - keyLen + 1; i++){
            for(int j = 0; j < len - keyLen + 1; j++){
                
                for(int k = 0; k < keyLen; k++){
                    for(int l = 0; l < keyLen; l++){
                        map[i + k][j + l] += key[k][l];
                    }
                }
                
                boolean flag = true;
                for(int k = keyLen - 1; k < keyLen + lockLen - 1; k++){
                    for(int l = keyLen-1; l < keyLen + lockLen - 1; l++){
                        if(map[k][l] != 1){
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }
                
                if(flag) return true;
                
                for(int k = 0; k < keyLen; k++){
                    for(int l = 0; l < keyLen; l++){
                        map[i + k][j + l] -= key[k][l];
                    }
                }
                
            }
        }
        
        return false;
    }
    
    static void rotateKey() {
        int keyLen = key.length;
        int[][] temp = new int[keyLen][keyLen];
        for (int i = 0; i < keyLen; i++) {
            for(int j = 0; j < keyLen; j++) {
                temp[i][j] = key[keyLen - j - 1][i];
            }
        }
        
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                key[i][j] = temp[i][j];
            }
        }
    }
}
