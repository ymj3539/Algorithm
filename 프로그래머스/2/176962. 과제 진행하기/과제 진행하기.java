import java.util.*;
import java.io.*;
class Solution {
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        
        PriorityQueue<Task> pq = new PriorityQueue<>();
        
        for(String[] s : plans){
            pq.offer(new Task(s[0], StringToTime(s), Integer.parseInt(s[2])));
        }
        
        Stack<Task> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        
        while(!pq.isEmpty() || !stack.isEmpty()) {
            Task cur = pq.poll();
            int curTime = cur.start;
            
            // 새로운 과제가 남아 있는 경우
            if(!pq.isEmpty()){
                // 다음 과제
                Task next = pq.peek();
                
                // 과제 진행 중 새로운 과제할 시간이 됨
                if(next.start < curTime + cur.playTime){
                    // 현재 진행중이던 과제 중단
                    cur.playTime -= (next.start - curTime);
                    stack.add(cur);
                    
                }
                // 현재 과제를 다 마침
                else {
                    list.add(cur.name);
                    curTime += cur.playTime;
                    
                    // 만약 다음 과제 전까지 시간이 남는 다면
                    while(next.start >= curTime && !stack.isEmpty()){
                        // 중단했던 과제 실행
                        Task remain = stack.pop();
                        
                        // 남은 시간동안 멈춘 과제 끝낼 수 있는 경우
                        if(next.start >= curTime + remain.playTime){
                            list.add(remain.name);
                            curTime += remain.playTime;
                        }
                        // 남은 시간안에 멈췄던 과제를 끝낼 수 없는 경우
                        else {
                            remain.playTime -= (next.start - curTime);
                            stack.push(remain);
                            break;
                        }
                    }
                }
            }
            
            // 새로운 과제 없음
            else {
                // 현재 과제(cur) 수행
                list.add(cur.name);
                
                // 남은 과제가 있으면 수행
                while(!stack.isEmpty()){
                    list.add(stack.pop().name);
                }
            }
            
        }
        
        answer = list;
        
        return answer;
    }
    
    static int StringToTime(String[] sArr){
        String[] s1 = sArr[1].split(":");
        return Integer.parseInt(s1[0])* 60 + Integer.parseInt(s1[1]);
    }
    
    static class Task implements Comparable<Task>{
        String name;
        int start;
        int playTime;
        
        public Task(String name, int start, int playTime){
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
        
        
        @Override
        public int compareTo(Task o){
            return this.start - o.start;
        }
    }
}