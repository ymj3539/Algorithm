import java.util.*;
import java.io.*;
class Solution {
    static int[][] Arr;
    static int[] cntArr;
    public int[] solution(int[][] arr) {
        int[] answer = {};
        cntArr = new int[2];
        Arr = arr;
        
        dfs(arr.length, 0, 0);
        answer = cntArr;
        return answer;
    }
    
    static void dfs(int size, int r, int c){
        if(check(size, r, c)) {
            cntArr[Arr[r][c]]++;
            return;
        }else {
            int mid = size/2;
            // 1사분면
            dfs(mid, r, c);
            
            // 2사분면
            dfs(mid, r, c+mid);
            
            // 3사분면
            dfs(mid, r+mid, c);
            
            
            // 4사분면
            dfs(mid, r+mid, c+mid);
        }
    }
    
    static boolean check(int size, int start_x, int start_y){
        int start = Arr[start_x][start_y];
        for(int i=start_x; i<start_x+size; i++){
            for(int j=start_y; j<start_y+size; j++){
                if(Arr[i][j] != start) return false;
            }
        }
        return true;
    }
    
}