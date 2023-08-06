import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static StringTokenizer tokens;
    static int[] nums;
    static int min  = Integer.MAX_VALUE;
    static int ans;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        tokens = new StringTokenizer(input.readLine());
        nums = new int[N];

        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(tokens.nextToken());
        }

        int low_i = 0;
        int high_i = N-1;

        while(true){
            if(low_i == high_i) break;
            // 큰값 + 작은 값
            int tmp = nums[high_i] + nums[low_i];

            // 최소값 비교(0과 얼마나 차이 나는지)
            if((Math.abs(tmp) - 0)  < min){
                min = Math.abs(tmp) - 0;
                ans = tmp;
            }

            // 0보다 크면 -> high index 이동
            if(tmp > 0) {
                high_i--;
            }
            // 0보다 작으면 -> low index 이동
            else if(tmp <0){
                low_i++;
            }
            // 같으면 그게 정답이니까 break;
            else {
                ans = 0;
                break;
            }
        }

        System.out.println(ans);
    }
}