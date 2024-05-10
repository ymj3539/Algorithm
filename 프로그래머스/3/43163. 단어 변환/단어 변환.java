import java.util.*;
import java.io.*;
class Solution {
    static String Target;
    static String[] Words;
    static int min = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        Words = words.clone();
        Target = target;
        List<String> list = new ArrayList<>(Arrays.asList(words));
        
        if(list.contains(target)){
            int[] visited = new int[words.length];
            Arrays.fill(visited, Integer.MAX_VALUE);
            dfs(begin, visited, 0);
            answer = min;
        }else {
            answer = 0;
        }
        
        return answer;
    }
    
    static void dfs(String cur, int[] visited, int cnt){
        if(cur.equals(Target)) {
            min = Math.min(min, cnt);
            return;
        }
        
        for(int i=0; i<Words.length; i++){
            if(visited[i] < cnt) continue;
            int diffCnt = 0;
            
            for(int j=0; j<cur.length(); j++){
                if(cur.charAt(j) != Words[i].charAt(j)) diffCnt++;
            }
            
            if(diffCnt == 1){
                visited[i]++;
                dfs(Words[i], visited, cnt+1);
                visited[i]--;
            }
        }
    }
}