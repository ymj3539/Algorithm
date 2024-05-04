import java.io.*;
import java.util.*;

class Solution {
    static String[] Orders;
    static int[] Course;
    static Map<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> totalList = new ArrayList<>();
        
        Orders = orders.clone();
        Course = course.clone();
        
        
        for(int i=0; i<course.length; i++){
            map = new TreeMap<>();
            for(int j=0; j<orders.length; j++){
                if(orders[j].length() >= course[i]){
                    comb(0, j, course[i], new char[course[i]], 0);
                }
            }
            
            List<String> list = new ArrayList<>();
            int max = Integer.MIN_VALUE;
            for(String key : map.keySet()){
                // System.out.println(key+" "+map.get(key));
                if(map.get(key) < 2) continue;
                
                if(map.get(key) > max){
                    list = new ArrayList<>();
                    list.add(key);
                    max = map.get(key);
                }else if(map.get(key) == max){
                    list.add(key);
                }
            }
            
            for(String menu : list){
                totalList.add(menu);
            }
            
            Collections.sort(totalList);
            answer = new String[totalList.size()];
            for(int l=0; l<answer.length; l++){
                answer[l] = totalList.get(l);
            }
        }
        
        
        
        return answer;
    }
    
    static void comb(int nth, int orderIdx, int menuCnt, char[] choosed, int idx){
        if(nth == menuCnt){
            char[] newChoosed = choosed.clone();
            Arrays.sort(newChoosed);
            String course = String.valueOf(newChoosed);
            if(map.containsKey(course)){
                int cnt = map.get(course);
                map.put(course, ++cnt);
            }else {
                map.put(course, 1);
            }
            return;
        }
        
        for(int i=idx; i<Orders[orderIdx].length(); i++){
            choosed[nth] = Orders[orderIdx].charAt(i);
            comb(nth+1, orderIdx, menuCnt, choosed, i+1);
        }
    }
    
    
}