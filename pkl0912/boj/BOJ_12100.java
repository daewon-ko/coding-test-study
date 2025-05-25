package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_12100 {
    
	static int N;
	static int[][] map; 
	
	static int[] swipeArr; 
	
	static int max = 0; 
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	
    	map = new int[N][N];
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for(int j=0; j<N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	swipeArr = new int[5]; 
    	
    	bt(0); 
    	
    	System.out.println(max);
    }
    
    static void bt(int depth) {

    	if(depth == 5) {
    		// deep-copy
    		int[][] newMap = new int[N][N];
    		for(int i=0; i<N; i++) {
    			for(int j=0; j<N; j++) {
    				newMap[i][j] = map[i][j];
    			}
    		}

    		for(int i=0; i<5; i++) {
    			int s = swipeArr[i];
    			newMap = swipeAll(s, newMap);
    	
    		int num = 0;
    		for(int i=0; i<N; i++) {
    			for(int j=0; j<N; j++) {
    				num = Math.max(num, newMap[i][j]);
    			}
    		}
    		
   
    		max = Math.max(max, num);
    		
    		return;
    	}
    	
    
    	for(int i=0; i<4; i++) {
    		swipeArr[depth] = i;
    		bt(depth + 1);
    	}
    }
    
    static int[][] swipeAll(int s, int[][] newMap) {
    	
    	switch(s) {
	
	        case 0:
	            for(int i = 0; i < N; i++) {
	                int index = 0; 
	                int block = 0; 
	                for(int j = 0; j < N; j++) {

	                    if(newMap[j][i] != 0) {
 
	                        if(block == newMap[j][i]) {

	                        	newMap[index - 1][i] = block * 2; 
 
	                            block = 0;
         
	                            newMap[j][i] = 0;
	                        }
            
	                        else {
         
	                            block = newMap[j][i];
               
	                            newMap[j][i] = 0;
          
	                            newMap[index][i] = block;
                  
	                            index++;
	                        }
	                    }
	                }
	            }
	            break;

	        case 1:
	            for(int i = 0; i < N; i++) {
	                int index = N - 1;
	                int block = 0;
	                for(int j = N - 1; j >= 0; j--) {
	                    if(newMap[j][i] != 0) {
	                        if(block == newMap[j][i]) {
	                        	newMap[index + 1][i] = block * 2;
	                            block = 0;
	                            newMap[j][i] = 0;
	                        }
	                        else {
	                            block = newMap[j][i];
	                            newMap[j][i] = 0;
	                            newMap[index][i] = block;
	                            index--;
	                        }
	                    }
	                }
	            }
	            break;

	        case 2:
	            for(int i = 0; i < N; i++) {
	                int index = 0;
	                int block = 0;
	                for(int j = 0; j < N; j++) {
	                    if(newMap[i][j] != 0) {
	                        if(block == newMap[i][j]) {
	                        	newMap[i][index - 1] = block * 2;
	                            block = 0;
	                            newMap[i][j] = 0;
	                        }
	                        else {
	                            block = newMap[i][j];
	                            newMap[i][j] = 0;
	                            newMap[i][index] = block;
	                            index++;
	                        }
	                    }
	                }
	            }
	            break;
	        // 우
	        case 3:
	            for(int i = 0; i < N; i++) {
	                int index = N - 1;
	                int block = 0;
	                for(int j = N - 1; j >= 0; j--) {
	                    if(newMap[i][j] != 0) {
	                        if(block == newMap[i][j]) {
	                        	newMap[i][index + 1] = block * 2;
	                            block = 0;
	                            newMap[i][j] = 0;
	                        }
	                        else {
	                            block = newMap[i][j];
	                            newMap[i][j] = 0;
	                            newMap[i][index] = block;
	                            index--;
	                        }
	                    }
	                }
	            }
	            break;
    	}
    	return newMap;
    }
    
}