import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(input.readLine());
        int[] arr  = new int[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(input.readLine());
        }

        long sum = 0;
        Arrays.sort(arr);
        for(int i=1; i<=N; i++){
            if(arr[i] > arr[i-1]){
                sum += arr[i] - (arr[i-1] + 1);
                arr[i] = arr[i-1] + 1;
            }

        }

        System.out.println(sum);
    }
}