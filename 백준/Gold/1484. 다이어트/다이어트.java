import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int G;
    public static void main(String[] args) throws Exception{
        G = Integer.parseInt(input.readLine());


        int start = binarySearch(1, 100000);
        
        StringBuilder sb = new StringBuilder();
        for(int i = start; i<= 100000; i++){
            double before_w = (Math.pow(i, 2) - G);
            if(before_w == 0) continue;
            if(Math.sqrt(before_w) % 1 <= 0) {
                sb.append(i + "\n");
            }
        }

        if(sb.length() == 0) System.out.println(-1);
        else System.out.println(sb.toString());
    }


    static int binarySearch(int left, int right){
        int mid = (left + right)/2;
        while(left <= right){
            mid = (left + right)/2;

            if(Math.pow(mid, 2) < G){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        return left;
    }
}