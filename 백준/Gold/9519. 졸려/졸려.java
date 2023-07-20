import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int X;
    static char[] input_arr;
    static char[] cur_arr;
    static char[] new_arr;
    static List<char[]> list = new ArrayList<>();
    static String str;
    static int arr_size;
    static String ans;
    public static void main(String[] args) throws Exception {


        X = Integer.parseInt(input.readLine());

        str = input.readLine();

        input_arr = str.toCharArray();
        cur_arr = str.toCharArray();

        arr_size = cur_arr.length;
        new_arr = new char[arr_size];


        int turn = 0;
        list.add(null);

        // 한 턴의 횟수 구하기
        while(true){
            // 문자열 이동
            move();

            // turn 카운트
            turn++;
            list.add(new_arr.clone());
            cur_arr = new_arr.clone();

            // 문자열 비교
            if(Arrays.equals(new_arr, input_arr)) break;
        }

        // 정답 문자열 구하기
        if(turn == 0) ans = str;
        else {
            X %= turn;

            int list_size = list.size();
            char[] ans_arr = list.get(turn - X);
            ans = Arrays.toString(ans_arr)
                    .replace("[","")
                    .replace("]","")
                    .replace(" ","")
                    .replace(",","");

        }

        System.out.println(ans);

    }

    static void move(){
        // 문자 이동 메소드
        for(int i=0, j=0; i<arr_size/2; i++){
            new_arr[j++] = cur_arr[i];    //  앞에서 i번째
            new_arr[j++] = cur_arr[arr_size-1-i];     // 뒤에서 i번째
        }

        // 문자열 길이가 홀수라면
        if(arr_size%2 != 0){
            new_arr[arr_size-1] = cur_arr[arr_size/2];
        }


    }
}