package daewonko.boj;

import java.util.*;
public class BOJ_21610 {

        static int N,M;
        static int[][] A;
        static List<int[]> clouds;
        static boolean[][] visited;
        static int[] dx={0,-1,-1,-1,0,1,1,1};
        static int[] dy={-1,-1,0,1,1,1,0,-1};
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            N=sc.nextInt();
            M=sc.nextInt();
            A=new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    A[i][j]=sc.nextInt();
                }
            }
            clouds=new ArrayList<>();
            clouds.add(new int[]{N-1,0});
            clouds.add(new int[]{N-1,1});
            clouds.add(new int[]{N-2,0});
            clouds.add(new int[]{N-2,1});
            for(int i=0;i<M;i++){
                int d=sc.nextInt()-1;
                int s=sc.nextInt();
                moveClouds(d,s);
                rain();
                waterCopy();
                makeClouds();
            }
            long ans=0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    ans+=A[i][j];
                }
            }
            System.out.println(ans);
        }
        static void moveClouds(int d,int s){
            for(int i=0;i<clouds.size();i++){
                int[] c=clouds.get(i);
                int r=(c[0]+dx[d]*s)%N;
                int col=(c[1]+dy[d]*s)%N;
                if(r<0) r+=N;
                if(col<0) col+=N;
                clouds.set(i,new int[]{r,col});
            }
        }
        static void rain(){
            visited=new boolean[N][N];
            for(int[] c:clouds){
                A[c[0]][c[1]]++;
                visited[c[0]][c[1]]=true;
            }
        }
        static void waterCopy(){
            for(int[] c:clouds){
                int count=0;
                for(int i=1;i<=4;i++){
                    int nr=c[0]+dx[i*2-1];
                    int nc=c[1]+dy[i*2-1];
                    if(nr>=0&&nr<N&&nc>=0&&nc<N&&A[nr][nc]>0){
                        count++;
                    }
                }
                A[c[0]][c[1]]+=count;
            }
        }
        static void makeClouds(){
            clouds.clear();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(A[i][j]>=2&&!visited[i][j]){
                        A[i][j]-=2;
                        clouds.add(new int[]{i,j});
                    }
                }
            }
        }
    }

