import java.util.*;
import java.io.*;
class Solution {
    static char[][] map;
    static int N, M;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[1].length();
        
        map = new char[N][M];
        int rr = 0;
        int rc = 0;
        
        for(int r=0; r<board.length; r++){
            map[r] = board[r].toCharArray();
            for(int c=0; c<M; c++){
                if(map[r][c] == 'R') {
                    rr = r;
                    rc = c;
                }
            }
        }
        
        answer = bfs(rr, rc);
        
        
        return answer;
    }
    
    static int bfs(int rr, int rc){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new Point(rr, rc));
        visited[rr][rc] = true;
        
        int depth = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                Point cur = queue.poll();
            
            if(map[cur.r][cur.c]=='G') return depth;
            
            for(int i=0; i<4; i++){
                int dr = cur.r;
                int dc = cur.c;
                
                while(true){
                    dr += dy[i];
                    dc += dx[i];
                    if(!isIn(dr, dc)) break;
                    if(map[dr][dc] == 'D') break;
                }
                
                dr -= dy[i];
                dc -= dx[i];
                
                if(visited[dr][dc]) continue;
                
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