import java.io.*;
import java.util.*;

class Solution {
    static String Origin_word;
    static boolean Flag;
    static char[] arr = {'A', 'E', 'I', 'O', 'U'};
    static int Cnt;
    static int Answer;
    public int solution(String word) {
        int answer = 0;
        Origin_word = word;
        dfs("", 0);
        
       answer = Answer;
        
        return answer;
    }
    
    static void dfs(String word, int depth){
        if(Flag) return;
        
        if(word.equals(Origin_word)){
            Answer = Cnt;
            Flag = true;
            return;
        }
        
        if(depth == 5){
            return;
        }
        
        for(int i=0; i<5; i++){
            Cnt++;
            dfs(word+arr[i], depth+1);
        }
    }
}