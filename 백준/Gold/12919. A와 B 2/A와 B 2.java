import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int answer = 0;
    public static void main(String[] args) throws Exception{
        String str1 = input.readLine();
        String str2 = input.readLine();

        dfs(str1, str2);

        System.out.println(answer);
    }

    static void dfs(String str1, String str2){
        if(str1.length() == str2.length()){
            if(str1.equals(str2)) {
                answer = 1;
            }

            return;
        }

        String str = str2;

        char s2_s = str2.charAt(0);
        char s2_e = str2.charAt(str2.length() - 1);

        if(s2_s == 'B'){
            // 돌리고 B 제거
            StringBuilder sb = new StringBuilder(str2);
            sb.reverse();
            str2 = sb.toString();
            str2 = str2.substring(0, str2.length()-1);
            dfs(str1, str2);
        }

        if(s2_e == 'A'){
            str = str.substring(0, str.length()-1);
            dfs(str1, str);
        }
    }
}