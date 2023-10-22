import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        Map<Integer, Info> infoMap = new HashMap<>();
        Map<Integer, Integer> timeMap = new HashMap<>();
        
        for(int i=0; i<records.length; i++){
            String[] str = records[i].split(" ");
            String[] time = str[0].split(":");
            int HH = Integer.parseInt(time[0]);
            int MM = Integer.parseInt(time[1]);
            MM += HH * 60;
            
            int carNum = Integer.parseInt(str[1]);
            String inout = str[2];
            
            if(inout.equals("IN")){
                // infoMap에 추가
                infoMap.put(carNum, new Info(carNum, MM, inout));
            }else if(inout.equals("OUT")){
                // infoMap에서 in 정보 찾아오기
                Info in = infoMap.remove(carNum);
                
                // 주차 시간 계산
                int newTime = MM - in.time;
                
                // 주차 시간 map에 넣어주기
                // 요금 맵에 있는지 확인
                if(timeMap.get(in.carNum) != null){
                    int tmp = timeMap.get(in.carNum);
                    timeMap.put(in.carNum, newTime + tmp);
                }else {
                    timeMap.put(in.carNum, newTime);
                }
                
            }
        }
        
        // 안나간 애들 처리
        for(Integer key : infoMap.keySet()){
            Info in = infoMap.get(key);
            // 주차 시간 계산
            int newTime = (23*60 +59) - in.time;

           // 주차 시간 map에 넣어주기
            // 요금 맵에 있는지 확인
            if(timeMap.get(in.carNum) != null){
                int tmp = timeMap.get(in.carNum);
                timeMap.put(in.carNum, newTime + tmp);
            }else {
                timeMap.put(in.carNum, newTime);
            }
        }
        
            // 요금 계산
            int[][] arr = new int[timeMap.size()][2];
            int i = 0;
            for(Integer key : timeMap.keySet()){
                int totalFee;
                int totalTime = timeMap.get(key);
                
                if(totalTime <= basicTime){
                totalFee = basicFee;
                }else {
                    int overTime = (totalTime - basicTime)%unitTime;
                    if(overTime == 0) {
                        totalFee = basicFee + ((totalTime - basicTime)/unitTime * unitFee);
                    }else {
                        totalFee = basicFee + (((totalTime - basicTime)/unitTime + 1) * unitFee);

                    }

                }
                
                arr[i++] = new int[] {key, totalFee};
            }
        
            
            
        
        
        
        Arrays.sort(arr, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
            
        });
        

            
        answer = new int[arr.length];
        for(int j=0; j<arr.length; j++){
            answer[j] = arr[j][1]; 
        }
        
        
        return answer;
    }
    
    static class Info{
        int carNum;
        int time;
        String inout;
        int fee;
        
        public Info(int c, int t, String i){
            this.carNum = c;
            this.time = t;
            this.inout = i;
        }
        
    }
}