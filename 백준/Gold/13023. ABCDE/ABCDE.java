import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static List<Integer>[] list;
    static int answer = 0;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        list = new List[N];

        for(int i=0; i<N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for(int i=0; i<N; i++){
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, visited, 1);
            if(answer == 1) break;
        }

        System.out.println(answer);
    }

    static void dfs(int idx, boolean[] visited, int cnt){
        if(cnt == 5) {
            answer = 1;
            return;
        }

        for(int i=0; i<list[idx].size(); i++){
            if(visited[list[idx].get(i)]) continue;
            visited[list[idx].get(i)] = true;
            dfs(list[idx].get(i), visited, cnt+1);
            visited[list[idx].get(i)] = false;
        }


    }
}