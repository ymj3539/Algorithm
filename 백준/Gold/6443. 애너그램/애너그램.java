import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static TreeSet<String> set;
    static int[] alphabet;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            String str = input.readLine();
            alphabet = new int[26];
            set = new TreeSet<>();
            for(int j=0; j<str.length(); j++){
                alphabet[str.charAt(j)-'a']++;
            }
            dfs(0, str.length(), "");
            for(String s : set){
                sb.append(s+"\n");
            }
        }
        System.out.println(sb);
    }
    static void dfs(int nth, int l, String str){
        if(nth == l){
            set.add(str);
            return;
        }

        for(int i=0; i<26; i++){
            if(alphabet[i] > 0){
                char ch = (char)(i+'a');
                alphabet[i]--;
                dfs(nth+1, l, str+ch);
                alphabet[i]++;
            }
        }
    }
}