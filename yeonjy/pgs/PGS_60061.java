package yeonjy.pgs;

public class PGS_60061 {
    public static boolean[][] pillars;
    public static boolean[][] beams;
    
    public static int count = 0;
    public static int length;
    
    public static boolean checkBeam(int x, int y) {
    	if(y - 1 >= 0 && y - 1 < length) {
			if(pillars[x][y - 1] == true) {
				return true;
			}
			if(x + 1 >= 0 && x + 1 < length) {
				if(pillars[x + 1][y - 1] == true) {
					return true;
				}
			}
			
			if(x - 1 >= 0 && x - 1 < length && x + 1 >= 0 && x + 1 < length) {
				if(beams[x - 1][y] == true && beams[x + 1][y] == true) {
					return true;
				}
			}
		}
    	
    	return false;
    }
    
    public static boolean checkPillar(int x, int y) {
		if(y == 0) {
			return true;
		}
		
		if(beams[x][y] == true) {
			return true;
		}
		
		if(x - 1 >= 0 && x - 1 < length) {
			if(beams[x-1][y] == true) {
				return true;
			}
		}
		
		if(y - 1 >= 0 && y - 1 < length) {
			if(pillars[x][y - 1] == true) {
				return true;
			}
		}
		
		return false;
    }
    
    public static void workPillar(int x, int y, int a, int b) {
    	if(b == 1) {
    		if(checkPillar(x, y) == true) {
    			pillars[x][y] = true;
    			count++;
    		}
    	}
    	else {
    		pillars[x][y] = false;
    		count--;
    		
    		for(int i = 0; i < length; i++) {
    			for(int j = 0; j < length; j++) {
    				
    				if(pillars[i][j] == true) {
    					if(checkPillar(i,j) == false) {
    						pillars[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    				
    				if(beams[i][j] == true) {
    					if(checkBeam(i,j) == false) {
    						pillars[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    			}
    		}
    	}
    }

    public static void workBeam(int x, int y, int a, int b) {
    	if(b == 1) {
    		if(checkBeam(x,y) == true) {
    			beams[x][y] = true;
    			count++;
    		}
    	}
    	else {
    		beams[x][y] = false;
    		count--;
    		
    		for(int i=0; i < length; i++) {
    			for(int j=0; j < length; j++) {
    				if(pillars[i][j] == true) {
    					if(checkPillar(i,j) == false) {
    						beams[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    				
    				if(beams[i][j] == true) {
    					if(checkBeam(i,j) == false) {
    						beams[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    			}
    		}
    	}
    }
    
    public static int[][] solution(int n, int[][] build_frame) {
    	length = n+1;
    	
    	pillars = new boolean[length][length];
    	beams = new boolean[length][length];
    	
        for(int i = 0; i < build_frame.length; i++){
            int[] curPos = build_frame[i];
            
            int x = curPos[0];
            int y = curPos[1];
            
            int a = curPos[2];
            int b = curPos[3];
                        
            if(a == 0) {
            	workPillar(x, y, a, b);
            }
            else {
            	workBeam(x, y, a, b);
            }
        }
        
        int[][] answer = new int[count][3];
        int index = 0;

        for(int i=0; i<length; i++) {
        	for(int j=0; j<length; j++) {
        		if(pillars[i][j] == true) {
        			answer[index] = new int[] {i,j,0};
        			index++;
        		}
        		if(beams[i][j] == true) {
        			answer[index] = new int[] {i,j,1};
        			index++;
        		}
        	}
        }
        return answer; 
    }
}
