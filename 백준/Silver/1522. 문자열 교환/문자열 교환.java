import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        String str = input.readLine();
        int len = str.length(); // 입력 문자열의 길이
        int min = Integer.MAX_VALUE;

        int a_cnt = 0;

        // a 갯수 카운트
        for(int i=0; i<len; i++){
            if(str.charAt(i) == 'a') a_cnt++;
        }

        // a 문자열 돌면서 a 문자열의 길이에 해당되는 b의 갯수 카운트
        for(int i=0; i< len; i++){
            int b_cnt = 0;
            for(int j=i; j<i+a_cnt; j++){
                if(str.charAt(j % len) == 'b') b_cnt++;
            }

            min = Math.min(min, b_cnt);
        }

        System.out.println(min);
    }
}