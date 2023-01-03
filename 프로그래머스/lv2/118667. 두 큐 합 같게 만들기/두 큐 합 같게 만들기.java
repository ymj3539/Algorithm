
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        long total = 0;
        long half = 0;
        
        // 배열 -> 큐에 넣기
        for(int i=0; i<queue1.length; i++) {
        	long tmp1 = queue1[i];
        	long tmp2 = queue2[i];
        	q1.offer(tmp1);
        	q2.offer(tmp2);
        	
        	sum1 += queue1[i];
        	sum2 += queue2[i];
        	
        }
        
        total = sum1 + sum2;
        half = total/2;
        
        // 몇 번 교환했나?
        int tmp = 0;
        
        // 반복문
       for(int i=0; i<=queue1.length*3; i++) {
    	   // sum이 큰 쪽에서 작은 쪽으로
    	   if(sum1 == sum2) {
    		   answer = tmp;
    		   break;
    	   }
    	   else if(sum1 > sum2) {
    		   long num = q1.poll();
    		   sum1 -= num;
    		   q2.offer(num);
    		   sum2 += num;
    	   }else {
    		   long num = q2.poll();
    		   sum2 -= num;
    		   q1.offer(num);
    		   sum1 += num;
    	   }
    	   tmp++;
    	   
       }
        
        return answer;
    }
}
