package daewonko.boj;
//백준 우주신과의 교감

import java.util.*;
public class BOJ_1774 {

        static int[] p;
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            int N=sc.nextInt();
            int M=sc.nextInt();
            double[] x=new double[N];
            double[] y=new double[N];
            for(int i=0;i<N;i++){
                x[i]=sc.nextDouble();
                y[i]=sc.nextDouble();
            }
            p=new int[N];
            for(int i=0;i<N;i++){
                p[i]=i;
            }
            for(int i=0;i<M;i++){
                int a=sc.nextInt()-1;
                int b=sc.nextInt()-1;
                union(a,b);
            }
            List<Edge> edges=new ArrayList<>();
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    double dist=Math.sqrt(Math.pow(x[i]-x[j],2)+Math.pow(y[i]-y[j],2));
                    edges.add(new Edge(i,j,dist));
                }
            }
            Collections.sort(edges,(a,b)->Double.compare(a.w,b.w));
            double ans=0;
            for(Edge e:edges){
                if(find(e.a)!=find(e.b)){
                    union(e.a,e.b);
                    ans+=e.w;
                }
            }
            System.out.printf("%.2f\n",ans);
        }
        static int find(int a){
            if(p[a]==a) return a;
            return p[a]=find(p[a]);
        }
        static void union(int a,int b){
            a=find(a);
            b=find(b);
            if(a!=b) p[b]=a;
        }
        static class Edge{
            int a,b;
            double w;
            Edge(int a,int b,double w){
                this.a=a;
                this.b=b;
                this.w=w;
            }
        }
    }

