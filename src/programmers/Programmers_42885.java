package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Programmers_42885 {
	
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i : people){
            deque.addLast(i);
        }
        
        while(deque.size() >1){
            int a = deque.peekFirst();
            int b = deque.peekLast();
            if(a+b <= limit){
                deque.removeFirst();
                deque.removeLast();
                answer++;
            }else{
                deque.removeLast();
                answer++;
            }
        }
        
        if(deque.size() == 1){
            answer++;
        }
        
        return answer;
    }
}
