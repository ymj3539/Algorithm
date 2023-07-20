import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int X;
    static char[] input_arr;    // 처음 입력받은 문자열(싸이클 확인 위함)
    static char[] cur_arr;  // 현재 문자열
    static char[] new_arr;  // 이동 후 문자열
    static List<char[]> list = new ArrayList<>();   // 싸이클 내의 문자열들
    static String str;
    static int arr_size;    // 문자열 크기
    static String ans;  // 정답 문자열
    public static void main(String[] args) throws Exception {
        X = Integer.parseInt(input.readLine());

        str = input.readLine();

        input_arr = str.toCharArray();
        cur_arr = str.toCharArray();

        arr_size = cur_arr.length;
        new_arr = new char[arr_size];

        int turn = 0;   // 한 싸이클이 몇 턴인지 체크

        // 한 턴의 횟수 구하기
        while(true){
            // 문자열 이동
            move();

            // turn 증가
            turn++;

            // 이동 후 문자열 list에 추가
            list.add(new_arr.clone());

            // 현재 문자열 갱신
            cur_arr = new_arr.clone();

            // 이동 후 문자열이 처음 입력받은 문자열과 같으면 싸이클 완성 -> 종료
            if(Arrays.equals(new_arr, input_arr)) break;
        }

        // 정답 문자열 구하기
        X %= turn;  // X값 줄이기

        char[] ans_arr = list.get(list.size() -1 - X);  // 뒤에서 X번째 값

        // 배열 -> 문자열 변환
        ans = Arrays.toString(ans_arr)
                .replace("[","")
                .replace("]","")
                .replace(" ","")
                .replace(",","");

        System.out.println(ans);

    }

    static void move(){
        // 문자 이동 메소드
        for(int i=0, j=0; i<arr_size/2; i++){
            new_arr[j++] = cur_arr[i];    //  앞에서 i번째
            new_arr[j++] = cur_arr[arr_size-1-i];     // 뒤에서 i번째
        }

        // 문자열 길이가 홀수라면 -> 가운데 값은 맨 끝으로 감
        if(arr_size%2 != 0){
            new_arr[arr_size-1] = cur_arr[arr_size/2];
        }


    }
}