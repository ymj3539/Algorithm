import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        tokens = new StringTokenizer(input.readLine());

        arr = new long[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 0;

        for(int i=0; i<N; i++){
            long target = arr[i];
            int start = 0; // 포인터1 인덱스
            int end = N-1; // 포인터2 인덱스

            while(start < end){
                long sum = arr[start] + arr[end];

                // 더해서 target이 됨
                if(target == sum){
                    // 자기 자신이 합에 포함되면 안 됨
                    if( i != start && i != end) {
                        cnt++;
                        break;
                    }
                    else if(i == start){
                        start++;
                    } else if(i == end){
                        end--;
                    }
                }

                // 더했는데 target이 아님
                // 더 작은 경우 -> start 인덱스 조절 
                else if(sum < target ){
                    start++;
                }
                // 더 큰 경우 -> end 인덱스 조절
                else {
                    end--;
                }
            }
        }

        System.out.println(cnt);
    }

}