import java.io.*;
import java.util.*;
class Solution {
    static boolean[] visited;
    static int[][] Computers;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        Computers = computers;
        
        int cnt = 0;
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            bfs(i, n);
            cnt++;
        }
        
        answer = cnt;
        
        return answer;
    }
    
    static void bfs(int num, int n){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        visited[num] = true;
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for(int i=0; i<n; i++){
                if(visited[i]) continue;
                if(Computers[cur][i] == 0) continue;
                
                queue.offer(i);
                visited[i] = true;
            }
        }
        
    }
}