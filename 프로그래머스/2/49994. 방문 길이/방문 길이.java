import java.util.*;
import java.io.*;
class Solution {
    static int[][] map = new int[11][11];
    static char[] Dirs;
    static Map<Character, int[]> dirMap;
    public int solution(String dirs) {
        int answer = 0;
        
        Dirs = new char[dirs.length()];
        
        Dirs = dirs.toCharArray();
        
        dirMap = new HashMap<>();
        
        dirMap.put('U', new int[] {-1, 0});
        dirMap.put('D', new int[] {1, 0});
        dirMap.put('R', new int[] {0, 1});
        dirMap.put('L', new int[] {0, -1});
        
        int cr = 5;
        int cc = 5;
        
        boolean[][][][] visited = new boolean[11][11][11][11];
        
        // 범위 : 0~11, 시작점 : 5,5
        for(int i=0; i<dirs.length(); i++){
            
            char dir = Dirs[i];
            
            int dr = cr + dirMap.get(dir)[0];
            int dc = cc + dirMap.get(dir)[1];
            
            if(!isIn(dr, dc)) continue;
            if(!visited[cr][cc][dr][dc]) {
                answer++;
            }
            
            visited[cr][cc][dr][dc] = true;
            visited[dr][dc][cr][cc] = true;
            cr = dr;
            cc = dc;
        }
        
        
        return answer;
    }
    
    static boolean isIn(int r, int c){
        return r>=0 && r<11 && c>=0 && c<11;
    }
}