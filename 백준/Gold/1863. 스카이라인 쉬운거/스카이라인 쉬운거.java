import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int n;
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(input.readLine());
        Stack<Integer> stack = new Stack<>();

        int min = 0;
        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(input.readLine());
            int c = Integer.parseInt(tokens.nextToken());
            int r = Integer.parseInt(tokens.nextToken());

            if(stack.isEmpty()){
                stack.push(r);
            }else{
                while(!stack.isEmpty() && stack.peek() > r){
                    stack.pop();
                    min++;
                }

                if(!stack.isEmpty() && stack.peek() == r) continue;

                stack.push(r);
            }
        }
        while(!stack.isEmpty()){
            if(stack.peek() > 0){
                min++;
            }
            stack.pop();
        }
        System.out.println(min);
    }
}