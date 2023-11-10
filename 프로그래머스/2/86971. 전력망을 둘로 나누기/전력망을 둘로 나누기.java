import java.util.*;
import java.io.*;
class Solution {
    
    static int[][] graph;
    static int N;
    static int MIN = Integer.MAX_VALUE;
    public int solution(int n, int[][] wires) {
        int answer = -1;
        N = n;
        
        // 인접행렬 만들기
        graph = new int[n+1][n+1];
        
        for(int i=0; i<wires.length; i++){
            int start = wires[i][0];
            int end = wires[i][1];
            
            graph[start][end] = 1;
            graph[end][start] = 1;
        }
        
        
        // 간선 하나씩 제거 
        for(int i=0; i<wires.length; i++){
            int start = wires[i][0];
            int end = wires[i][1];
            
            graph[start][end] = 0;
            graph[end][start] = 0;
            
            // bfs 돌기
            bfs(start);
            
            
            // 간선 다시 추가
            graph[start][end] = 1;
            graph[end][start] = 1;
        }
        
        
        answer = MIN;
        
       
        
        
        return answer;
    }
    
    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        int cnt = 0;
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            cnt++;
            
            for(int i=1; i<=N; i++){
                if(graph[cur][i] != 1) continue;
                if(visited[i]) continue;
                
                queue.offer(i);
                visited[i] = true;
            }
            
        }
        
        MIN = Math.min(Math.abs(cnt - (N-cnt)), MIN);
        
    }
}