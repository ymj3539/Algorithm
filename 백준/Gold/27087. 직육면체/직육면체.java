import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int A, B, C, p;

    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t=1; t<=T; t++){
            tokens = new StringTokenizer(input.readLine());

            A = Integer.parseInt(tokens.nextToken());
            B = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());
            p = Integer.parseInt(tokens.nextToken());

            sb.append((canFill() ? 1 : 0) + "\n");

        }

        System.out.println(sb);

    }
    static boolean canFill(){
        int cnt =0;
        if(A%p == 0) cnt++;
        if(B%p == 0) cnt++;
        if(C%p == 0) cnt++;

        if(cnt >= 2) return true;
        else return false;
    }
}