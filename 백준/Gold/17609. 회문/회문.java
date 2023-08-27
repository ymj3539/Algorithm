import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int T;
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(input.readLine());

        for(int t=0; t<T; t++){
            String str = input.readLine();
            int left_i = 0;
            int right_i = str.length() - 1;
            boolean flag = false; // 유사회문 체크용 flag
            boolean is_palin = true;

            while(left_i <= right_i){
                char left = str.charAt(left_i);
                char right = str.charAt(right_i);

                // 앞문자 뒷문자 비교
                if(left == right) {
                    left_i++;
                    right_i--;
                }
                else {
                    // 한문자 삭제 가능
                    // 앞문자 삭제
                    if(can_palindrome(str, left_i+1, right_i)){
                        is_palin = false;
                        sb.append(1+"\n");
                        break;
                    }

                    else{
                        // 뒷문자 삭제
                        if(can_palindrome(str, left_i, right_i-1)){
                            is_palin = false;
                            sb.append(1+"\n");
                            break;
                        }
                        // 둘다 삭제해도 다름
                        else{
                            is_palin = false;
                            sb.append(2+"\n");
                            break;
                        }
                    }

                }
            }

            if(is_palin){
                sb.append(0+"\n");
            }
        }

        System.out.println(sb);
    }

    static boolean can_palindrome(String str, int left_i, int right_i){
        while(left_i <= right_i) {
            char left = str.charAt(left_i);
            char right = str.charAt(right_i);

            // 앞문자 뒷문자 비교
            if (left != right) {
                return false;
            }else{
                left_i++;
                right_i--;
            }

        }
        return true;
    }
}