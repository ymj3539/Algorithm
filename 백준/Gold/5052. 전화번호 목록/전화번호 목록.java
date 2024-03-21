import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();

        outer : for(int t=1; t<=T; t++){
            int n = Integer.parseInt(input.readLine());

            String[] nums = new String[n];
            for(int i=0; i<n; i++) {
                 nums[i] = input.readLine();
            }

            Arrays.sort(nums);

            for(int i=0; i<n-1; i++){
                if(nums[i+1].startsWith(nums[i])){
                    sb.append("NO"+"\n");
                    continue outer;
                }
            }
            sb.append("YES"+"\n");
        }

        System.out.println(sb);

    }
}