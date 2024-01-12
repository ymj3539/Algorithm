import java.util.*;
import java.io.*;

class Solution {
    static int[][] Land;
    static int[][] visited;
    static int index = 0;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static Map<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        
        Land = land;
        visited = new int[land.length][land[0].length];
        
        // for(int r=0; r<land.length; r++){
        //     System.out.println(Arrays.toString(Land[r]));
        // }
        
        // 열마다 시추관 내려보기
        for(int c=0; c<Land[0].length; c++){
            int cnt = 0;
            Set<Integer> set = new HashSet<>();
            for(int r=0; r<Land.length; r++){
                if(visited[r][c] == 0 && Land[r][c] == 1) {
                    ++index;
                    int temp = bfs(r, c);
                    map.put(index, temp);
                }
                
                if(Land[r][c] == 1){
                    set.add(visited[r][c]);
                }
            }
            
            for(int i : set){
                cnt += map.get(i);
            }
            
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
    
    static int bfs(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r,c));
        visited[r][c] = index;
        int cnt = 1;
        
        while(!queue.isEmpty()){
            Point cur = queue.poll();
            
            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];
                
                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc]>0) continue;
                if(Land[dr][dc] == 0) continue;
                
                queue.offer(new Point(dr, dc));
                visited[dr][dc] = index;
                cnt++;
            }
        }
        
        return cnt;
    }
    
    static boolean isIn(int r, int c){
        return r>=0 && r<Land.length && c>=0 && c<Land[0].length;
    }
    
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}