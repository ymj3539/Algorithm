import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] parents;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();


        for(int t=0; t<T; t++){
            int N = Integer.parseInt(input.readLine());

            parents = new int[N+1];
            visited = new boolean[N+1];
            answer = 0;

            for(int i=0; i<N-1; i++){
                tokens = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(tokens.nextToken());
                int b = Integer.parseInt(tokens.nextToken());

                parents[b] = a;
            }

            tokens = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());

            dfs(A);
            dfs(B);
            sb.append(answer+"\n");

        }
        System.out.println(sb);

    }

    static void dfs(int num){
        if(visited[num]){
            answer = num;
            return;
        }

        visited[num] = true;
        if(parents[num] != 0){
            dfs(parents[num]);
        }
    }
}