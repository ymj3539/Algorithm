import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static List<Node>[] list;
    static int total_dist;
    static boolean flag = false;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        list = new List[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            tokens = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int dist = Integer.parseInt(tokens.nextToken());

            list[start].add(new Node(end, dist));
            list[end].add(new Node(start, dist));
        }

        // 거리 구하기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());

            boolean[] visited = new boolean[N+1];
            visited[start] = true;
            total_dist = 0;
            flag = false;
            dfs(start, end, 0, visited);
            sb.append(total_dist+"\n");
        }

        System.out.println(sb);
    }

    static void dfs(int start, int end, int sum, boolean[] visited){
        if(flag) return;
        if(start == end){
            total_dist = sum;
            flag = true;
            return;
        }

        for(int i=0; i<list[start].size(); i++){
            Node node = list[start].get(i);
            if(visited[node.end]) continue;
            visited[node.end] = true;
            dfs(node.end, end, sum + node.dist, visited);
            visited[node.end] = false;
        }

    }

    static class Node{
        int end, dist;
        public Node(int end, int dist){
            this.end = end;
            this.dist = dist;
        }
    }
}