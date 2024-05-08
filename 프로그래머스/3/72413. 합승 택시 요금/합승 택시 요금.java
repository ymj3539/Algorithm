import java.util.*;
import java.io.*;
class Solution {
    static int N;   // 정점 갯수 
    static int E;   // 간선 갯수
    static List<Node>[] list;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        N = n;
        E = fares.length;
        list = new List[N+1];
        
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<E; i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }
        
        int[] aAlone = dijkstra(a); // a를 시작점으로 하는 모든 정점에 대한 최소비용 거리
        int[] bAlone = dijkstra(b); // b를 시작점으로 하는 모든 정점에 대한 최소비용 거리
        int[] together = dijkstra(s); // s를 시작점으로 하는 모든 정점에 대한 최소비용 거리
        
        answer = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            // 그래프가 양방향이라 가능
            // (s -> i 까지 가는 비용) + (i -> a 가는 비용) + (i -> b 가는 비용)
            int totalFee = together[i] + aAlone[i] + bAlone[i];
            answer = Math.min(answer, totalFee);
        }
        
        
        return answer;
    }
    
    static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0; // start에서 각 지점까지 가는데 드는 최소비용
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            // 현재 위치까지 오는 비용보다 더 작은 값이 dist 배열에 들어가있으면 이미 최소비용경로를 찾은것이기 때문에 넘어감
            if(dist[cur.num] < cur.cost) continue;
            
            for(Node next : list[cur.num]) {
                // 다음 정점으로 가는 이미 저장되어 있는 비용이 (현재 정점까지의 비용 + 현재 정점 -> 다음 정점으로 가는 비용) 보다 크다면 진행
                if(dist[next.num] > cur.cost + next.cost){
                    // 갱신
                    dist[next.num] = cur.cost + next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        
        return dist;
    }
    
    static class Node implements Comparable<Node>{
        int num, cost;
        public Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }
}