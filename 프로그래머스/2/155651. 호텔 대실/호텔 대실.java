import java.util.*;
import java.io.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[][] bookTime = new int[book_time.length][2];
        
        // string -> int로 바꾸기
        for(int i=0; i<book_time.length; i++){
            int tmp1 = StringToInt(book_time[i][0], 0);
            int tmp2 = StringToInt(book_time[i][1], 1);
            
            bookTime[i][0] = tmp1;
            bookTime[i][1] = tmp2;
        }
        
        // bookTime 재정렬
        Arrays.sort(bookTime, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[]o2){
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }else return o1[0] - o2[0];
            }
        });
        
        // pq 만들기
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int[] time : bookTime){
            if(pq.size() == 0){
                pq.add(time[1]);
                continue;
            }
            // 퇴실 시간이라면 
            else if(pq.peek() <= time[0]){
                pq.poll();
                pq.add(time[1]);
            }else{
                pq.add(time[1]);
            }
        }
        answer = pq.size();
        
        return answer;
    }
    
    static int StringToInt(String s, int idx){
        String[] arr = s.split(":");
        int tmp1 = Integer.parseInt(arr[0]);
        int tmp2 = Integer.parseInt(arr[1]);
        if(idx == 1){
            tmp2 += 10;
            if(tmp2 >= 60) {
                tmp1 += 1;
                tmp2 -= 60;
            }   
        
        }
        
        tmp1 *= 100;
        
        System.out.println(tmp1 + tmp2);
        
        return tmp1 + tmp2;
    }
}