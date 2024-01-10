import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static Queue<Integer> origin_belt;
    static Stack<Integer> temp_belt;
    static Queue<Integer> truck;
    public int solution(int[] order) {
        int answer = 0;
        N = order.length;
        
        truck = new LinkedList<>();
        origin_belt = new LinkedList<>();
        temp_belt = new Stack<>();
        
        for(int i = 1; i<=N; i++){
            origin_belt.offer(i);
        }
        
        for(int i=0; i<N; i++){
            int num = order[i];
            
            // stack 확인 - 임시 컨테이너
            if(!temp_belt.isEmpty()){
                if(temp_belt.peek() == num){
                    int tmp = temp_belt.pop();
                    truck.offer(tmp);
                }else {
                    if(origin_belt.isEmpty()) break;
                    while(!origin_belt.isEmpty()){
                        int tmp = origin_belt.poll();
                        if(tmp == num){
                            truck.offer(tmp);
                            break;
                        } else {
                            temp_belt.push(tmp);
                        }    
                    }
                };
            }
            
            // queue 확인 - 원래 컨테이너
            else {
                while(!origin_belt.isEmpty()){
                    int tmp = origin_belt.poll();
                    if(tmp == num){
                        truck.offer(tmp);
                        break;
                    } else {
                        temp_belt.push(tmp);
                    }    
                }
                
            }
            
        }
        
        answer = truck.size();
        
        return answer;
    }
    
}