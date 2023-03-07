import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        outer : while(true){
            String str = input.readLine();
            if(str.equals(".")) {
                break outer;
            }

            String answer = solve(str);
            System.out.println(answer);
        }
    }

    static String solve(String str){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '(' || str.charAt(i) == '['){
                stack.push(str.charAt(i));
            }else if(str.charAt(i) == ')' ){
                // stack 제일 위에 ( 가 있어야 돼
                if(stack.isEmpty() || stack.peek() != '('){
                    return "no";

                }else stack.pop();
            }else if(str.charAt(i) == ']'){
                // stack 제일 위에 [ 가 있어야 돼
                if(stack.isEmpty() || stack.peek() != '['){
                    return "no";

                }else stack.pop();
            }
        }

        if(!stack.isEmpty()) {
            return "no";
        }else{
            return "yes";
        }
    }
}
