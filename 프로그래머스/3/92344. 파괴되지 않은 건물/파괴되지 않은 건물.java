import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        for(int i=0; i<skill.length; i++){
            int[] skill_1 = skill[i];
            int degree = 0;
            if(skill_1[0] == 1) degree = skill_1[5] * (-1);
            else degree = skill_1[5];
            
            int r1 = skill_1[1];
            int c1 = skill_1[2];
            int r2 = skill_1[3]+1;
            int c2 = skill_1[4]+1;
            
            
            sum[r1][c1] += degree;
            sum[r1][c2] -= degree;
            sum[r2][c1] -= degree;
            sum[r2][c2] += degree;
            
        }
        
        // 누적합 (오른쪽 누적)
        for(int i=0; i<sum.length; i++){ 
            for(int j=0; j<sum[0].length-1; j++){
                sum[i][j+1] += sum[i][j];
            }
        }
        
        // 누적합 (아래로 누적)
        for(int i=0; i<sum.length; i++){ 
            for(int j=0; j<sum.length-1; j++){
                sum[j+1][i] += sum[j][i];
            }
        }
        
        
        // 합치기
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] += sum[i][j];
                if(board[i][j] >= 1) answer++;
            }
        }
        
        
        return answer;
    }
}