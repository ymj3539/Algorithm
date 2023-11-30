import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String answer;
    static boolean Flag = false;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        String str = "";
        perm(str);
        System.out.println(answer);
    }

    static void perm(String str){
        if(Flag) return;
        if(str.length() == N){
            answer = str.toString();
            Flag = true;
            return;
        }


        for(int i=1; i <=3; i++){
            // 좋은 순열인지 체크
            if(isGood(str, i)){
                perm(str.toString() + i);
            }
        }
    }

    static boolean isGood(String str, int num){
        str+=num;
        int back_end_idx = str.length();

        for(int i=1; i<=str.length()/2; i++){
            int front_end_idx = back_end_idx - i;
            String front = str.substring(front_end_idx - i, front_end_idx);
            String back = str.substring(back_end_idx - i, back_end_idx);
            if(front.equals(back)) return false;
        }

        return true;

    }
}