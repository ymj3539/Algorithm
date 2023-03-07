import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int K;
    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(input.readLine());
        Stack<Integer> stack = new Stack<>();

        int sum = 0;

        for(int i=0; i<K; i++){
            int tmp = Integer.parseInt(input.readLine());
            if(tmp == 0){
                sum -= stack.pop();
            }else {
                stack.push(tmp);
                sum += tmp;
            }
        }

        System.out.println(sum);

    }
}
