import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        for(int[] i : targets){
            pq.offer(new Point(i[0], i[1]));
        }
        
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            answer++;
            int end = cur.e;
            
            while(!pq.isEmpty()){
                Point next = pq.poll();
                if(end <= next.s){
                    pq.offer(next);
                    break;
                } else end = Math.min(end, next.e);
            }
        }
        
        return answer;
    }
    
    static class Point implements Comparable<Point>{
        int s, e;
        public Point(int s, int e){
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Point p){
            if(p.s == this.s){
                return (this.e - this.s) - (p.e - p.s);
            }else return this.s - p.s;
        }
    }
}