import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int V, E;
    static List<Node>[] nodes;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        V = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());

        nodes = new List[V+1];

        int K = Integer.parseInt(input.readLine());
        for(int i=1; i<=V; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            tokens = new StringTokenizer(input.readLine());

            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

            nodes[start].add(new Node(end, weight));
        }

        int[] dist = new int[V+1]; // 시작점에서 i점 까지의 최솟값
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[K] = 0;
        pq.offer(new Node(K, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(!visited[cur.vertex]) {
                visited[cur.vertex] = true;

                for(int i=0; i<nodes[cur.vertex].size(); i++){
                    Node next = nodes[cur.vertex].get(i);
                    if(dist[next.vertex] > dist[cur.vertex] + next.weight){
                        dist[next.vertex] = dist[cur.vertex] + next.weight;
                        pq.offer(new Node(next.vertex,  dist[next.vertex]));
                    }
                }
            }
        }

        for(int i=1; i<=V; i++){
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }

    }

    static class Node implements Comparable<Node>{
        int vertex, weight;
        public Node(int end, int weight){
            this.vertex = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node){
            return this.weight - node.weight;
        }
    }
}