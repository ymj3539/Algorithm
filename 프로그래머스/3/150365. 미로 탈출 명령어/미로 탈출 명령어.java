import java.util.*;
import java.io.*;
class Solution {
    static int N, M, K;
    static int[][] map;
    static Point start, end;
    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, -1, 1, 0};
    static char[] strArr = {'d', 'l', 'r', 'u'};
    static String Ans = "impossible";
    static boolean flag = false;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
         
        N = n;
        M = m;
        map = new int[N+1][M+1];
        
        start = new Point(x, y);
        end = new Point(r, c);
        K = k;
        
        boolean[][][] visited= new boolean[K+1][N+1][M+1];
        visited[0][start.r][start.c] = true;
        char[] arr = new char[K];
        
        // 최단 경로의 길이
        int dis = checkMin(start.r, start.c, end.r, end.c);
        if(dis > K || (K-dis) %2 == 1) {
            
        }else {
            dfs(0, start.r, start.c, visited, arr);
        }
        
        
        answer = Ans;
        return answer;
    }
    
    static void dfs(int nth, int r, int c, boolean[][][] visited, char[] path){
        if(flag) return;
        if(nth + checkMin(r, c, end.r, end.c) > K) return;
        if(nth == K){
            if(r == end.r && c == end.c){
                Ans = String.valueOf(path);
                flag = true;
            }
            
            return;
        }
        
        for(int i=0; i<4; i++){
            int dr = r + dy[i];
            int dc = c + dx[i];
            
            if(!isIn(dr, dc)) continue;
            
            if(!visited[nth][dr][dc]){
                visited[nth][dr][dc] = true;
                path[nth] = strArr[i];
                dfs(nth+1, dr, dc, visited, path);
                visited[nth][dr][dc] = false;
            }
        }
    }
    
    static int checkMin(int r, int c, int rr, int cc){
        return Math.abs(r-rr) + Math.abs(c-cc);
    }
    
    static boolean isIn(int r, int c){
        return r>=1 && r<=N && c>=1 && c<=M;
    }
    
    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}