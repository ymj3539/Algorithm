import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Character> str_deque;
    static Deque<Long> num_deque;
    public static void main(String[] args)throws Exception{
        String str = input.readLine();

        str_deque = new ArrayDeque<>();
        num_deque = new ArrayDeque<>();

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < str.length()){
            char ch = str.charAt(i);
            // 맨 앞에 음수 왔을 때 처리
            if(i==0 && ch =='-'){
                sb.append('-');
                i++;
                continue;
            }

            if(ch=='-' || ch=='*' || ch=='/' || ch=='+') {
                str_deque.addLast(ch);
                // 숫자 덱에 넣기
                num_deque.addLast(Long.parseLong(sb.toString()));
                // sb 초기화
                sb = new StringBuilder();
            }
            // 숫자 일때
            else {
                sb.append(ch);
            }
            i++;
        }
        num_deque.addLast(Long.parseLong(sb.toString()));

        // 수식 계산
        while(str_deque.size() > 1){
            // 1. 수식 맨 앞 맨 뒤 비교
            char str1 = str_deque.removeFirst();
            char str2 = str_deque.removeLast();

            // 앞 - 곱셈/나눗셈, 뒤-덧셈,뺄셈
            if((str1 == '*'|| str1 == '/') && (str2 == '+'|| str2 == '-')) {
                // 앞 부분만 연산
                long num1 = num_deque.removeFirst();
                long num2 = num_deque.removeFirst();

                long res1 = calculate(num1, str1, num2);

                // 계산된 숫자 deque에 넣기
                num_deque.addFirst(res1);
                // 뒷쪽 연산자 다시 덱에 넣기
                str_deque.addLast(str2);
            }

            // 앞 - 덧셈, 뺄셈, 뒤- 곱셈, 나눗셈
            else if((str1 == '-'|| str1 == '+') && (str2 == '*'|| str2 == '/')){
                long num3 = num_deque.removeLast();
                long num4 = num_deque.removeLast();

                long res2 = calculate(num4, str2, num3);

                // 계산된 숫자 deque에 넣기
                num_deque.addLast(res2);

                // 연산 처리 안된거 deque에 다시 넣기
                str_deque.addFirst(str1);
            }

            // 둘다 곱셈 또는 나눗셈일때
            else {
                // 연산자 계산 후 비교
                long num1 = num_deque.removeFirst();
                long num2 = num_deque.peekFirst();
                long num3 = num_deque.removeLast();
                long num4 = num_deque.peekLast();

                long res1 = calculate(num1, str1, num2);
                long res2 = calculate(num4, str2, num3);

                if(res1 >= res2){
                    // 계산된 숫자 deque에 넣기
                    num_deque.removeFirst();
                    num_deque.addFirst(res1);

                    // 연산 처리 안된거 deque에 다시 넣기
                    num_deque.addLast(num3);
                    str_deque.addLast(str2);
                }else {
                    // 계산된 숫자 deque에 넣기
                    num_deque.removeLast();
                    num_deque.addLast(res2);

                    // 연산 처리 안된거 deque에 다시 넣기
                    num_deque.addFirst(num1);
                    str_deque.addFirst(str1);
                }
            }

        }

        // 마지막 연산
        long answer = 0;
        if(str_deque.isEmpty()){
            answer = num_deque.removeFirst();
        }else {
            answer = calculate(num_deque.removeFirst(), str_deque.removeFirst(), num_deque.removeFirst());
        }

        System.out.println(answer);

    }

    static long calculate(long num1, char str1, long num2){
        if(str1 == '+'){
            return num1 + num2;
        }else if(str1 == '-'){
            return num1 - num2;
        }else if(str1 == '*'){
            return num1 * num2;
        }else {
            return num1/num2;
        }
    }
}