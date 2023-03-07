import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];

        int[] arr = new int[8001];

        int rangeMax = Integer.MIN_VALUE;
        int rangeMin = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(input.readLine());
            sum += nums[i];

            arr[nums[i]+4000]++;
            rangeMax = Math.max(rangeMax, nums[i]);
            rangeMin = Math.min(rangeMin, nums[i]);
        }


        // 산술평균
        int s = (int)Math.round((double)sum / N);
        sb.append(s).append("\n");

        // 중앙값
        Arrays.sort(nums);
        int index = N/2;
        int m = nums[index];
        sb.append(m).append("\n");

        // 최빈값
        int max = Integer.MIN_VALUE;
        int maxCnt = 0;
        boolean flag = false;
        for(int i=rangeMin + 4000; i<=rangeMax+4000; i++){
            if(max < arr[i]){
                max = arr[i];
                maxCnt = i-4000;
                flag = true;
            }else if(max == arr[i] && flag == true){
                // i가 이미 있던 maxCnt보다 작으면 i로 바꿔줘
                maxCnt = i-4000;
                flag = false;

            }
        }
        sb.append(maxCnt).append("\n");


        // 최대 - 최소
        int maxMin = nums[N-1] - nums[0];
        sb.append(maxMin);

        System.out.println(sb);
    }


}
