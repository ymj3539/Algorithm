import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static List<Integer>[] Tree;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        Tree = new List[N+1];

        for(int i=1; i<=N; i++){
            Tree[i] = new ArrayList<>();
        }

        for(int i=1; i<=N-1; i++){
            tokens = new StringTokenizer(input.readLine());

            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());

            Tree[start].add(end);
            Tree[end].add(start);
        }

        // dp[i][0] : i노드가 얼리어답터X 일때 최솟값,
        // dp[i][1] : i노드가 얼리어답터 일때 최솟값,
        dp = new int[N+1][2];


        dfs(1, 0);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int cur, int parent){
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for(int next : Tree[cur]){
            if(next == parent) continue;
            // 리프노드까지 초기화
            dfs(next, cur);
            // 올라오면서 dp값 구하기
            // 1. 현재 노드가 얼리어답터가 아닐때 -> 자식 노드가 무조건 얼리어답터
            dp[cur][0] += dp[next][1];
            // 2. 현재 노드가 얼리어답터일때 -> 자식 노드가 얼리어답터이거나 아닌 것 중 최솟값
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }


    }
}