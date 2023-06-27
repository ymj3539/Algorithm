import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        S = Integer.parseInt(tokens.nextToken());

        arr = new int[N+1];
        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        Queue<Integer> queue = new LinkedList<>();

        while(start <= N){
            // sum이 S보다 작으면 sum에 arr 원소 하나 추가
            if(sum < S) {
                // sum에 추가 후 start 값 하나 증가
                queue.offer(arr[start]);
                sum += arr[start++];
            }

            // sum이 S보다 크면 sum에서 제일 앞의 원소 빼줌
            else {
                minLength = Math.min(minLength, queue.size());
                int tmp = queue.poll();
                sum -= tmp;
            }
        }


        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);

    }
}
