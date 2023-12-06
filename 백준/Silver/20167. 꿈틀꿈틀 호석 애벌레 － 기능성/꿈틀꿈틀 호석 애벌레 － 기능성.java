import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    static int[] food;
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        food = new int[N];

        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            food[i] = Integer.parseInt(tokens.nextToken());
        }

        dfs(0, 0, 0);
        System.out.println(MAX);
    }

    static void dfs(int idx, int total, int energy){
        if(idx == N) {
            MAX = Math.max(MAX, energy);
            return;
        }

        dfs(idx+1, 0, energy);

        if(total + food[idx] >= K){
            dfs(idx+1, 0, energy+(total+food[idx]-K));
        }else {
            dfs(idx+1, total+food[idx], energy);
        }

    }
}