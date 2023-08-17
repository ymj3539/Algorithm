import java.io.*;
import java.util.*;
class Solution {
    static int[] per = {10, 20, 30, 40};
    static int max_sub =Integer.MIN_VALUE;
    static int max_income = Integer.MIN_VALUE;
    static int[][] Users;
    static int[] Emoticons;
    public int[] solution(int[][] users, int[] emoticons) {
        int size = emoticons.length;
        Emoticons = emoticons;
        Users = users;
        perm(0, new int[size], new boolean[4]);
        
        int[] answer = {max_sub, max_income};
        return answer;
    }
    
    static void perm(int nth, int[] choosed, boolean[] visited){
        if(nth == choosed.length){
            // 이모티콘 총 수익액
            int total_income = 0;
            
            // 총 가입자 수
            int sub_cnt = 0;
            
            // 정해진 할인율 배열 각 사용자마다 돌기
            for(int i=0; i<Users.length; i++){
                // 사용자의 총 지불액
                int pay = 0;
                boolean flag = true;
                
                outer : for(int j=0; j<choosed.length; j++){
                    // 사용자의 할인 기준 확인
                    if(Users[i][0] > choosed[j]) continue;
                    
                    // 할인된 이모티콘 가격 계산
                    int price = (int)((100 - choosed[j]) * Emoticons[j] * 0.01);
                    
                    // 사용자의 총 지불액 + 이모티콘 가격
                    pay += price;
                    
                    // 이모티콘 구매 비용이 기준 이상인지 확인
                    if(pay >= Users[i][1]) {
                        // 이상이면 가입자 +1, 다음 사용자로 넘어가
                        sub_cnt++;
                        flag = false;
                        break outer;
                    }
                    
                }
                
                // 끝까지 임티플 가입하지 않은 사람의 지불액만 total에 저장
                    if(flag) {
                        total_income += pay;
                    }
                
                
            }
            
            
            // 최대값 확인
            if(max_sub < sub_cnt){
                max_sub = sub_cnt;
                max_income = total_income;
            }else if(max_sub == sub_cnt){
                max_income = Math.max(max_income, total_income);
            }
            
            return;
        }
        
        for(int i=0; i<per.length; i++){
            choosed[nth] = per[i];
            perm(nth+1, choosed, visited);
        }
    }
}