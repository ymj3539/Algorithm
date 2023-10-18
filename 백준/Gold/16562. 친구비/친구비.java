import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, k;
    static int[] cost;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        cost = new int[N+1];
        parents = new int[N+1];

        tokens = new StringTokenizer(input.readLine());
        for(int i=1; i<=N; i++){
            cost[i] = Integer.parseInt(tokens.nextToken());
            parents[i] = i;
        }

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int v = Integer.parseInt(tokens.nextToken());
            int w = Integer.parseInt(tokens.nextToken());
            // 유니온
            union(v, w);
        }

        boolean[] visited = new boolean[N+1];
        int sum = 0;
        for(int i=1; i<=N; i++){
            int tmp = find(i);
            if(visited[tmp]) continue;
            visited[tmp] = true;
            sum += cost[tmp];
        }

        System.out.println(sum > k ? "Oh no" : sum);
    }

    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);
        if(X != Y) {
            cost[Y] = Math.min(cost[X], cost[Y]);
            parents[X] = Y;
        }
    }
}