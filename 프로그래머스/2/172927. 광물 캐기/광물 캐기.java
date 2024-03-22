import java.util.*;
import java.io.*;
class Solution {
    static int[] Picks;
    static String[] Minerals;
    static int[][] tired;
    static int MIN = Integer.MAX_VALUE;
    static Map<String, Integer> map = new HashMap<>();
    public int solution(int[] picks, String[] minerals) {
        int answer = 0; // 피로도
        Picks = picks;
        Minerals = minerals;
        
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        
        tired = new int[][] {
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
        };
        
        int[] choosed = new int[Picks[0] + Picks[1] + Picks[2]];
        perm(0, choosed);
        
        answer = MIN;
        
        return answer;
    }
    
    static void perm(int nth, int[] choosed){
        if(nth == choosed.length) {
            // System.out.println(Arrays.toString(choosed));
            
            // 피로도 계산
            int sum = 0;
            int idx = 0;
            int cnt = 5;
            for(int i =0; i<Minerals.length; i++){
                if(idx == choosed.length) break;
                
                // System.out.println(choosed[idx]+" "+i);
                
                sum += tired[choosed[idx]][map.get(Minerals[i])];
                cnt--;
                
                if(cnt == 0) {
                    cnt = 5;
                    idx++;
                }
                
                
                
            }
            
            MIN = Math.min(MIN, sum);
            
            return;
        }
        
        for(int i=0; i<Picks.length; i++){
            if(Picks[i] <= 0) continue;
            choosed[nth] = i;
            Picks[i]--;
            perm(nth+1, choosed);
            Picks[i]++;
        }
    }
}