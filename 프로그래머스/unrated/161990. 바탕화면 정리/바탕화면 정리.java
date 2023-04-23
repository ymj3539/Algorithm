import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        int r_min = Integer.MAX_VALUE;
        int c_min = Integer.MAX_VALUE;
        int r_max = Integer.MIN_VALUE;
        int c_max = Integer.MIN_VALUE;
        
        for(int i=0; i<wallpaper.length; i++){
            for(int j=0; j<wallpaper[i].length(); j++){
                if(wallpaper[i].charAt(j) == '#'){
                    r_min = Math.min(r_min, i);
                    c_min = Math.min(c_min, j);
                    r_max = Math.max(r_max, i);
                    c_max = Math.max(c_max, j);
                }
            }
        }
        
        answer[0] = r_min; 
        answer[1] = c_min; 
        answer[2] = r_max+1; 
        answer[3] = c_max+1; 
        
        return answer;
    }
}