import java.util.*;
import java.io.*;
public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static long[] table;
    static long max = Long.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        table = new long[N];

        for(int i=0; i<N; i++){
            table[i] = Integer.parseInt(input.readLine());
            max = Math.max(max, table[i]);
        }


        long answer = binarySearch();
        System.out.println(answer);

    }

    static long binarySearch(){
        long left = 0;
        long right = max*M;

        while(left <= right){
            long mid = (left+right)/2;
            long cnt = 0;

            for(int i=0; i<N; i++){
                // 전체시간 기준 각 심사대별로 몇 명 처리할 수 있는지
                if(cnt > M) break;
                cnt += (mid/table[i]);
            }

            if(cnt < M){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return left;
    }
}