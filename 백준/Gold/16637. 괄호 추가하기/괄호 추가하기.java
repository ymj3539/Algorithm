import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static char[] str;
    static int Max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        str = new char[N];
        str = input.readLine().toCharArray();

        if(N==1){
            Max = str[0] - '0';
        }else{
            dfs(2, str[0] - '0');
        }

        System.out.println(Max);
    }

    static void dfs(int i, int sum){
        if(i >= N){
            Max = Math.max(Max , sum);
            return;
        }

        // 숫자일 때
        if(i %2 == 0){
            int sum1 = sum; // 괄호 열었을 때 sum
            int sum2 = sum; //  괄호 안 열었을 때 sum

            int cur = str[i] - '0';
            char operator;

            // 괄호 열기
            if(i < N-2){
                // 먼저 계산
                operator = str[i+1];
                int next = str[i+2] -'0';

                int tmp = 0; // 먼저 계산한 것의 합


                // 계산
                if(operator == '+'){
                    tmp = cur + next;
                } else if (operator == '-') {
                    tmp = cur - next;
                }else {
                    tmp = cur * next;
                }

                // sum에 합치기
                operator = str[i-1];
                // 계산
                if(operator == '+'){
                    sum1 += tmp;
                } else if (operator == '-') {
                    sum1 -= tmp;
                }else {
                    sum1 *= tmp;
                }

                dfs(i+4, sum1);
            }

            // 괄호 열지 않기
            // sum에 합치기
            operator = str[i-1];
            // 계산
            if(operator == '+'){
                sum2 += (cur) ;
            } else if (operator == '-') {
                sum2 -= (cur);
            }else {
                sum2 *= (cur);
            }

            dfs(i+2, sum2);
        }
    }
}