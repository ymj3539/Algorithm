import java.io.*;
import java.util.*;

class Solution {
    static String Origin_word;
    static boolean Flag;
    static char[] arr = {'A', 'E', 'I', 'O', 'U'};
    static int Cnt;
    static List<String> list = new ArrayList<>();
    public int solution(String word) {
        int answer = 0;
        Origin_word = word;
        dfs("", 0);
        
        
        for(int i=0; i<list.size();  i++){
            if(list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        
       
        
        return answer;
    }
    
    static void dfs(String word, int depth){
        list.add(word);
        
        if(depth == 5){
            return;
        }
        
        for(int i=0; i<5; i++){
            dfs(word+arr[i], depth+1);
        }
    }
}