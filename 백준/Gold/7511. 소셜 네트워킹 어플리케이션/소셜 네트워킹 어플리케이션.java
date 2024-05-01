import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] parents;
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=T; t++){
            sb.append("Scenario "+t+":\n");
            int N = Integer.parseInt(input.readLine());
            int K = Integer.parseInt(input.readLine());

            parents = new int[N];


            for(int i=0; i<N; i++){
                parents[i] = i;
            }

            for(int k=0; k<K; k++){
                tokens = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(tokens.nextToken());
                int b = Integer.parseInt(tokens.nextToken());

                union(a, b);
            }

            int M = Integer.parseInt(input.readLine());
            for(int m=0; m<M; m++){
                tokens = new StringTokenizer(input.readLine());
                int u = Integer.parseInt(tokens.nextToken());
                int v = Integer.parseInt(tokens.nextToken());

                if(find(u) == find(v)) sb.append(1+"\n");
                else sb.append(0+"\n");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);

        if(X > Y){
            parents[X] = Y;
        }else {
            parents[Y] = X;
        }
    }
}