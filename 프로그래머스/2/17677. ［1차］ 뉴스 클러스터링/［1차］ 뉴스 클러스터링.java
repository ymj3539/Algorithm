import java.io.*;
import java.util.*;

class Solution {
    static List<String> list1 = new ArrayList<>();
    static List<String> list2 = new ArrayList<>();
    public int solution(String str1, String str2) {
        int answer = 0;
        
        makeGroup(str1, list1);
        makeGroup(str2, list2);
        
        int sameGroupSize = 0;
        
        int size1 = list1.size();
        int size2 = list2.size();
        
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        
        if(list1.size() >= list2.size()){
            list3 = list1;
            list4 = list2;
        }else {
            list3 = list2;
            list4 = list1;
        }

        boolean[] check2 = new boolean[list4.size()];
        
        for(int i=0; i<list3.size(); i++){
           String s = list3.get(i);
            if(list4.contains(s)){
                sameGroupSize++;
                list4.remove(s);
            }
        }
        
        double tmp;
        if(size1 == 0 && size2 == 0){
            tmp = 1;
        }else {
            int sumGroupSize = size1 + size2 - sameGroupSize;
            tmp = ((double)sameGroupSize / (double)sumGroupSize);
            
        }
        
        tmp *= 65536;
        answer = (int)tmp;
        
        return answer;
    }
    
    static void makeGroup(String str, List<String> list){
        char ch1 = str.charAt(0);
        for(int i=1; i<str.length(); i++){
            char ch2 = str.charAt(i);
            
            if(!((ch1 >= 'A' && ch1 <= 'Z') || (ch1>='a' && ch1<='z'))) {
                ch1 = ch2;
                continue;
            }
            
            if(!((ch2 >= 'A' && ch2 <= 'Z') || (ch2>='a' && ch2<='z'))) {
                ch1 = ch2;
                continue;
            }
            String new_str = String.valueOf(ch1) + String.valueOf(ch2);
            ch1 = ch2;
            list.add(new_str.toUpperCase());
            
            
        }
        
        
    }
}