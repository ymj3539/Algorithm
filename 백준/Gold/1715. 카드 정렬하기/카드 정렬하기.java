import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        for(int i=0; i<N; i++){
            pq.offer(Long.parseLong(input.readLine()));
        }

        long sum = 0;

        while(pq.size() > 1){
            long card_A = pq.poll();
            long card_B = pq.poll();

            long bundle = card_A + card_B;
            pq.offer(bundle);
            sum += bundle;
        }


        System.out.println(sum);
    }



}