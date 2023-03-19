import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        // 왼손 위치 1차원 배열
        int[] lefthand = {3,0};
        // 오른손 위치 1차원 배열
        int[] righthand = {3,2};
        // 키패드 위치 2차원 배열
        int[][] keypad = {
            {3,1}, // 0 
            {0,0}, // 1
            {0,1}, // 2
            {0,2}, // 3
            {1,0}, // 4
            {1,1}, // 5
            {1,2}, // 6
            {2,0}, // 7
            {2,1}, // 8
            {2,2}, // 9
        };
        
        
        for(int i=0; i<numbers.length; i++){
            // 왼쪽 번호일 때
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                // 문자열에 L추가
                sb.append("L");
                // 왼손 위치 갱신
                lefthand = keypad[numbers[i]];
            }
            
            // 오른쪽 번호일 때
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                // 문자열에 R추가
                sb.append("R");
                // 오른손 위치 갱신
                righthand = keypad[numbers[i]];
            }
            
            // 중앙 번호일 때
            else{
                // 왼손과의 위치 비교
                int dl = Math.abs(lefthand[0] - keypad[numbers[i]][0]) + Math.abs(lefthand[1] - keypad[numbers[i]][1]);
                // 오른손과의 위치 비교
                int dr = Math.abs(righthand[0] - keypad[numbers[i]][0]) + Math.abs(righthand[1] - keypad[numbers[i]][1]);
                
                // 왼손 오른손 비교
                // 1. 왼손이 더 가까우면
                if(dl < dr){
                    // 문자열에 L추가
                    sb.append("L");
                    // 왼손 위치 갱신
                    lefthand = keypad[numbers[i]];
                }
                // 2. 오른손이 더 가까우면 
                else if(dr < dl){
                    // 문자열에 R추가
                    sb.append("R");
                    // 오른손 위치 갱신
                    righthand = keypad[numbers[i]];
                }
                // 3. 같으면
                else{
                    if(hand.equals("right")){
                        // 문자열에 R추가
                        sb.append("R");
                        // 오른손 위치 갱신
                        righthand = keypad[numbers[i]];
                    }else{
                        // 문자열에 L추가
                        sb.append("L");
                        // 왼손 위치 갱신
                        lefthand = keypad[numbers[i]];
                    }
                }
            
            }
        }        
        answer = sb.toString();
        return answer;
    }
}