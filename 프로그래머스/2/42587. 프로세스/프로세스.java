import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        // 우선순위가 낮은 요소부터 빠져나가게됨
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<priorities.length; i++) {
            queue.offer(priorities[i]);
        }
        
        
        while(!queue.isEmpty()){
            for(int i=0; i<priorities.length; i++) {
                if(queue.peek() == priorities[i]){
                    queue.poll();
                    answer++;
                    if(location == i){
                        return answer;
                    }
                }
            }
            
        }
        return answer;
    }
}