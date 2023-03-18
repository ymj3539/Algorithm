import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        int[] d = new int[N+1];

        d[1] = 1;
        for(int i=2; i<=N; i++){
            d[i] = 10001;
            for(int j=1; j<=i/2; j++){
                if(j*j==i) {
                    d[i] = 1;
                    break;
                }
                d[i] = Math.min(d[i], d[j]+d[i-j]);
            }
        }

        System.out.println(d[N]);
    }
}
