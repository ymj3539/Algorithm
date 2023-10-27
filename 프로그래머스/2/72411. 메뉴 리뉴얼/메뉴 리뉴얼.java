import java.util.*;
import java.io.*;
class Solution {
    static Map<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        
        
        for(int j=0; j<course.length; j++){
            map = new HashMap<>();
            for(int i=0; i<orders.length; i++){
                if(course[j] > orders[i].length()) continue;
                char[] arr = orders[i].toCharArray();
                Arrays.sort(arr);
                comb(0, new char[course[j]], arr, 0, course[j]);
            }
            
            int max = Integer.MIN_VALUE;
                for(String key : map.keySet()){
                    max = Math.max(max, map.get(key));
                }        
                
                for(String key : map.keySet()){
                    if(map.get(key) >= 2) {
                        if(map.get(key) == max){
                            list.add(key);
                        }
                    }
                }  
        }
        
        
        
        Collections.sort(list);
        answer = new String[list.size()];
        int i = 0;
        for(String s : list){
            answer[i++] = s;
        }
        
        return answer;
    }
    
    static void comb(int nth, char[] choosed, char[] course, int idx, int n){
        if(nth == n){
            // String으로 바꾸기
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<n; i++){
                sb.append(choosed[i]);
            }
            String str = sb.toString();
            
            // Map에 넣기<String, Integer>
            if(map.get(str) != null){
                map.put(str, map.get(str) + 1);
            }else {
                map.put(str, 1);
            }
            return;
        }
        
        
        for(int i=idx; i<course.length; i++){
            choosed[nth] = course[i];
            comb(nth+1, choosed, course, i+1, n);
        }
    }
}