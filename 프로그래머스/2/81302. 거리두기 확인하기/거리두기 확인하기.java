import java.io.*;
import java.util.*;

class Solution {
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    static char[][] map;
    static List<Point> list;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = {};
        
        int T = places.length;
        answer = new int[T];
        
        
        for(int t=0; t<T; t++){
            
            map = new char[places.length][5];
            list = new ArrayList<>();
            
            for(int r=0; r<5; r++){
                String str = places[t][r];
                for(int c=0; c<5; c++){
                    map[r][c] = str.charAt(c);
                    if(map[r][c] == 'P') list.add(new Point(r, c));
                }
            }
            
            
            boolean flag = true;
            for(Point p : list){
                map[p.r][p.c] = 'S';
                boolean result = bfs(p);
                map[p.r][p.c] = 'P';
                if(!result) {
                    flag = false;
                    break;
                }
                
            }
            
            answer[t] = (flag ? 1 : 0);
            
        }
        
        return answer;
    }
    
    static boolean bfs(Point p){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(p);
        boolean[][] visited = new boolean[5][5];
        visited[p.r][p.c] =  true;
        
        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            if(depth == 3) return true;
            while(size-->0){
                Point cur = queue.poll();
                
                if(map[cur.r][cur.c] == 'P') return false;
                
                for(int i=0; i<4; i++){
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];
                    
                    if(!isIn(dr, dc)) continue;
                    if(visited[dr][dc]) continue;
                    if(map[dr][dc] == 'X') continue;
                    
                    queue.offer(new Point(dr,dc));
                    visited[dr][dc] = true;
                    
                }
            }
            depth++;
        }
        return true;
    }
    
    static boolean isIn(int r, int c){
        return r>=0 && r<5 && c>=0 && c<5;
    }
}