import java.io.*;
import java.util.*;

class Solution {
    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, -1, 1, 0};
    static String[] str_arr = {"d", "l", "r", "u"};
    static int N, M, X, Y, R, C, K;
    static String Answer = "impossible";
    static boolean Flag;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        N = n;
        M = m;
        X = x-1;
        Y = y-1;
        R = r-1;
        C = c-1;
        K = k;
        
        // 최단 경로의 길이 구하기
        int dis = checkDistance(R, C, X, Y);
        
        // 최단경로가 K보다 크거나, K - dis 가 홀수면 impossible
        if( dis > K ||  ((K-dis) % 2 ==1)) answer = "impossible";
        else {
            dfs("", X, Y);
            answer = Answer;
        }
        
        
        return answer;
    }
    
    static int checkDistance(int r, int c, int x, int y){
        return Math.abs(r-x) + Math.abs(c-y);
    }
    
    static void dfs(String str, int r, int c){
        if(Flag) return;
        // 백트래킹 추가 : (지나온 거리 + 현재 위치~최단거리) > K 면 더 진행안해도 됨(어차피 못감)
        if(str.length() + checkDistance(r, c, R, C) > K) return;
        if(str.length() == K){
            if(r == R && c == C){
                Answer = str;
                Flag = true;
            }
            return;
        }
        
        for(int i=0; i<4; i++){
            int dr = r + dy[i];
            int dc = c + dx[i];
            if(!isIn(dr, dc)) continue;
            dfs(str+str_arr[i], dr, dc);
        }
        
    }
    
    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
    
    static class Point{
        int r, c;
        String path;
        public Point(int r, int c, String path){
            this.r = r;
            this.c = c;
            this.path = path;
        }
    }
}