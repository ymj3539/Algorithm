import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int records_size = records.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        PriorityQueue<Record> pq_in = new PriorityQueue<>(new Comparator<Record>() {
            
            @Override
            public int compare(Record o1, Record o2){
                if(o1.car_num != o2.car_num){
                    return o1.car_num - o2.car_num;
                }
                else {
                    String[] time = o1.time.split(":");
                    int o1_time = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

                    time = o2.time.split(":");
                    int o2_time = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                    
                    return (o1_time - o2_time);
                }
            }
        });
        
        PriorityQueue<Record> pq_out = new PriorityQueue<>(new Comparator<Record>() {
            
            @Override
            public int compare(Record o1, Record o2){
                if(o1.car_num != o2.car_num){
                    return o1.car_num - o2.car_num;
                }
                else {
                    String[] time = o1.time.split(":");
                    int o1_time = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

                    time = o2.time.split(":");
                    int o2_time = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                    
                    return (o1_time - o2_time);
                }
            }
        });
        
        Record[] records_class = new Record[records_size];
        
        // 기본시간
        int BASIC_TIME = fees[0];
        // 기본요금
        int BASIC_FEE = fees[1];
        // 단위시간
        int UNIT_TIME = fees[2];
        // 단위요금
        int UNIT_FEE = fees[3];
        
        for(int i=0; i<records_size; i++){
            String[] record = records[i].split(" ");
            String time = record[0];
            int car_num = Integer.parseInt(record[1]);
            boolean in_out;
            
            if(record[2].equals("IN")) {
                in_out = true;
                pq_in.offer(new Record(time, car_num, in_out));
            }
            else {
                in_out = false;
                pq_out.offer(new Record(time, car_num, in_out));
            }
        }
        
        
        while(!pq_in.isEmpty()){
            Record cur_in = pq_in.poll();
            Record cur_out;
            
            // 출차가 된 차량
            if(pq_out.peek() != null && pq_out.peek().car_num == cur_in.car_num){
                cur_out = pq_out.poll();
            }
            // 출차가 안 된 차량
            else {
                cur_out = new Record("23:59", cur_in.car_num, false);
            }
            
            // System.out.println(cur_in.toString()+", "+cur_out.toString());
            
            // 시간 계산
                // 분 단위로 변환
                String[] time = cur_in.time.split(":");
                int time_in_MM = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            
                time = cur_out.time.split(":");
                int time_out_MM = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                
                // 주차 시간
                int parking_time = time_out_MM - time_in_MM;
                
                // treemap에 넣기
                // 이미 한번 주차했었던 차량이면
                if(map.get(cur_in.car_num) != null){
                    map.put(cur_in.car_num, map.get(cur_in.car_num) + parking_time);
                }else{
                    map.put(cur_in.car_num, parking_time);
                }
            
        }
        
        
        // 주차 요금 정산
        int map_size = map.size();
        answer = new int[map_size];
        
        Iterator<Integer> iter = map.keySet().iterator();
        int i = 0;
        
        while(iter.hasNext()){
            int key = iter.next();
            int time_MM = map.get(key);
            // System.out.println(key+": "+time_MM);
            // 기본 시간보다 적거나 같으면
            if(time_MM <= BASIC_TIME){
                answer[i++]  = BASIC_FEE;
                continue;
            }
            // 기본 시간 이상이면
            else {
                int over_time = time_MM - BASIC_TIME;
                int over_fee = (int)Math.ceil((double)over_time/UNIT_TIME);
                answer[i++] = BASIC_FEE + over_fee * UNIT_FEE;
                // System.out.println(over_time+", "+over_fee+" "+answer[i-1]);
            }
        }
        
        // System.out.println(Arrays.toString(answer));
        
        
        return answer;
    }
    
    static class Record{
        String time;
        int car_num;
        boolean in_out;
        
        public Record(String time, int car_num, boolean in_out){
            this.time = time;
            this.car_num = car_num;
            this.in_out = in_out;
        }
        
        @Override
        public String toString(){
            return this.time+" "+this.car_num+" "+this.in_out;
        }
    }
}