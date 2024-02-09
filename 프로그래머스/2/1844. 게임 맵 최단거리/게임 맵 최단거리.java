import java.util.*;
import java.io.*;

class Solution {
    static int[][] Maps;
    static int N;
    static int M;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public int solution(int[][] maps) {
        int answer = 0;
        
        Maps = maps;
        N = maps.length;
        M = maps[0].length;
        
        answer = bfs();
        
        return answer;
    }
    
    static int bfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new Point(0, 0));
        visited[0][0] = true;
        
        int depth = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                Point cur = queue.poll();
                if(cur.r == N-1 && cur.c == M-1) {
                    return depth;
                }
                
                for(int i=0; i<4; i++){
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];
                    
                    if(!isIn(dr, dc)) continue;
                    if(visited[dr][dc]) continue;
                    if(Maps[dr][dc] == 0) continue;
                    
                    queue.offer(new Point(dr, dc));
                    visited[dr][dc] = true;
                    
                }
                
            }
            
            depth++;
        }
        return -1;
    }
    
    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
    
    static class Point{
        int r, c;
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}