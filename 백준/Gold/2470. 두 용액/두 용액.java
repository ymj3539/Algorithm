import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static long[] arr;
    static String answer;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.readLine());
        arr = new long[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(tokens.nextToken());
        }

        Arrays.sort(arr);

        binarySearch();

        System.out.println(answer);
    }

    static void binarySearch(){
        int leftIdx = 0;
        int rightIdx = arr.length-1;
        long min = Long.MAX_VALUE;

        while(leftIdx < rightIdx){
            long liquid = arr[leftIdx] + arr[rightIdx];
            if(Math.abs(liquid) < min){
                min = Math.abs(liquid);
                answer = arr[leftIdx]+" "+arr[rightIdx];
            }
            if(liquid == 0) return;
            else if(liquid > 0){
                rightIdx--;
            }else {
                leftIdx++;
            }
        }
    }
}