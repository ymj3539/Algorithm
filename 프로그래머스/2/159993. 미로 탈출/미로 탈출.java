import java.util.*;
import java.io.*;
class Solution {
    static char[][] map;
    static int N, M;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public int solution(String[] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        
        int start_r = 0;
        int start_c = 0;
        
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                map[r][c] = maps[r].charAt(c);
                if(map[r][c] == 'S') {
                    start_r = r;
                    start_c = c;
            
                }
            }
        }
        
        answer = bfs(start_r, start_c);
        
        return answer;
    }
    
    static int bfs(int sr, int sc){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc, 0));
        boolean[][][] visited = new boolean[2][N][M];
        visited[0][sr][sc] = true;
        
        int depth = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                Point cur = queue.poll();
                
                if(cur.haveLever == 1 && map[cur.r][cur.c] == 'E') return depth;
                
                for(int i=0; i<4; i++){
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];
                    int next_lever = cur.haveLever;
                    
                    if(!isIn(dr, dc)) continue;
                    if(map[dr][dc] == 'X') continue;
                    if(visited[cur.haveLever][dr][dc]) continue;
                    
                    // 레버라면
                    if(map[dr][dc] == 'L'){
                        visited[0][dr][dc] = true;
                        visited[1][dr][dc] = true;
                        queue.offer(new Point(dr, dc, 1));
                    }
                    
                    visited[next_lever][dr][dc] = true;
                    queue.offer(new Point(dr, dc, next_lever));
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
        int haveLever;
        public Point(int r, int c, int haveLever){
            this.r = r;
            this.c = c;
            this.haveLever = haveLever;
        }
    }
}