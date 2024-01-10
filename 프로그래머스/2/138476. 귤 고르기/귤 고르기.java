import java.util.*;
import java.io.*;
class Solution {
    static Integer[] arr;
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        arr = new Integer[10000000+1];
        Arrays.fill(arr, 0);
        int N = tangerine.length;
        
        for(int i=0; i<N; i++){
            int idx = tangerine[i];
            arr[idx]++;
        }
        
        Arrays.sort(arr, Collections.reverseOrder());
        
        int sum = 0;
        for(int i=0; i<100000; i++){
            sum += arr[i];
            if(sum >= k) {
                answer = i+1;
                break;
            }
        }
        
        return answer;
    }
}