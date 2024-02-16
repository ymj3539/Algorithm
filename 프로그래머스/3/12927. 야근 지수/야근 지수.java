import java.io.*;
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<works.length; i++){
            pq.offer(works[i]);
        }
        
        while(n>0){
            if(pq.isEmpty()) break;
            int tmp = pq.poll();
            n--;
            tmp-=1;
            if(tmp>0) pq.offer(tmp);
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}