import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(input.readLine());
        }

        int[] dp = new int[N];
        int maxLength = Integer.MIN_VALUE;
        
        for(int i=0; i<N; i++){
            dp[i] = 1;

            // i 이전 값들을 살펴보면서 i위치의 값보다 작은 값 찾기
            for(int j=0; j<i; j++){
                if(arr[j] < arr[i]){
                    // 작은 값이 있으면 dp[i] = dp[j]+1
                    // 근데 이미 최장 증가 수열을 찾은 후 일수도 있으니까 기존의 dp[i]과 비교해서 더 큰값으로 넣어줌
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        System.out.println(N - maxLength);
    }

}