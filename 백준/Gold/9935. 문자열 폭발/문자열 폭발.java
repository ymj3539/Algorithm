import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String[] args) throws Exception{
        String str = input.readLine();
        String exp_str = input.readLine();

        Stack<Character>  stack = new Stack<>();

        int str_size = str.length();
        int exp_str_size = exp_str.length();
        char last_char = exp_str.charAt(exp_str_size-1);
        StringBuilder ans = new StringBuilder();

        for(int i=0; i<str_size; i++){
            stack.push(str.charAt(i));
            int stack_size = stack.size();
            // i번째 문자가 last_char과 같은가?
            // 같고, 스택 size가 exp_size 이상이면 스택에서 하나씩 빼서 확인
//            System.out.println(i+", "+str.charAt(i)+", "+stack.size()+", "+exp_str_size);
            if(stack_size >= exp_str_size){

                // 스택에서 뺀 문자와 폭발 문자열(문자) 비교
                boolean flag = true;
                for(int k =exp_str_size-1, j = stack_size-1;  k>=0; k--, j--){
//                    System.out.println(stack.get(j));
                    if(stack.get(j) != exp_str.charAt(k)){
                        flag = false;
                        break;
                    }

                }

                // 스택에서 빼주기
                if(flag){
                    for(int j =0; j<exp_str_size; j++){
                        stack.pop();
                    }

                }
            }

        }


//        while(!stack.isEmpty()){
//            ans.insert(0, stack.pop());
//        }

        if(!stack.isEmpty()){
            for(char c : stack){
                ans.append(c);
            }
        }



        System.out.println(ans.length() == 0 ? "FRULA" : ans.toString());

    }
}