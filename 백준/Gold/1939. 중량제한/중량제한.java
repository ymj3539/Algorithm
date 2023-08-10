import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int V, E;
    static StringTokenizer tokens;
    static List<Node>[] nodes;
    static int Start, End;
    static int low, high;
    public static void main(String[] args) throws Exception{

        tokens = new StringTokenizer(input.readLine());
        V = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());

        nodes = new List[V+1];
        low = Integer.MAX_VALUE;
        high = Integer.MIN_VALUE;

        for(int i=0; i<=V; i++){
            nodes[i] = new ArrayList();
        }

        for(int i=0; i<E; i++){
            tokens = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

            nodes[start].add(new Node(end, weight));
            nodes[end].add(new Node(start, weight));
            low = Math.min(low, weight);
            high = Math.max(high, weight);
        }

        tokens = new StringTokenizer(input.readLine());
        Start = Integer.parseInt(tokens.nextToken());
        End = Integer.parseInt(tokens.nextToken());

        int mid = 0;
        int ans = 0;

        while(low<=high){
            mid = (low + high)/2;

            // 건널 수 있으면 low 증가
            if(bfs(mid)){
                low = mid + 1;
                ans = mid;
            }else {
                high = mid-1;
            }


        }
        System.out.println(ans);

    }

    static boolean bfs(int weight){
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[V+1];
        queue.offer(new Node(Start, 0));

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            int cur_v = cur.end;
            if(cur_v == End) return true;

            for(Node node : nodes[cur_v]){
                if((node.weight >= weight) && !visited[node.end]){
                    queue.offer(node);
                    visited[node.end] = true;

                }
//                if(visited[node.end]) continue;
//                if(node.weight < weight) continue;

            }
        }

        return false;
    }

    static class Node{
        int end, weight;
        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
}