import java.util.*;
import java.io.*;
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        String storeyStr = String.valueOf(storey);
        int[] arr = new int[storeyStr.length()];
        
        // 입력값 배열로 바꾸기
        for(int i=0; i<storeyStr.length(); i++){
            arr[i] = storeyStr.charAt(i) - '0';
        }
        
        
        int totalCnt = 0;
        for(int i=arr.length-1; i>=0; i--){
            int num = arr[i];
            
            // num이 5일 때
            if(num == 5) {
                // 앞자리 확인하기
                if(i>0 && arr[i-1] >= 5){
                    arr[i-1]++;
                    totalCnt += 5;
                }else {
                    totalCnt += num;
                }
            }
            
            // num이 5초과일때
            else if(num > 5 ){
                totalCnt += (10 - num);
                
                // 맨앞자리 수라면 그 앞자리로 올라간 것도 버튼 눌러야돼
                if(i == 0) totalCnt++;
                else arr[i-1]++;
                
            }
            
            // num이 5미만일때
            else {
                totalCnt += num;
            }
            
        }
        
        answer = totalCnt;
        
        return answer;
    }
}