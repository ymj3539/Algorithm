import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(input.readLine());
        int[] S = new int[N];
        int[] P = new int[N];

        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            P[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            S[i] = Integer.parseInt(tokens.nextToken());
        }


        // 기본 순서(만들어야 되는 순서)
        int[] standard = new int[N];
        for(int i=0; i<N; i++){
            standard[i] = 0;
            standard[++i] = 1;
            standard[++i] = 2;

        }

        int cnt = 0;
        boolean flag = true;
        // 맨 처음 카드 순서
        int[] start_cards = P.clone();
        int[] new_P = new int[N];

        while(true){

            // 조건 확인
            if(Arrays.equals(standard, P)) break;

            // 카드 섞기
            for(int i=0; i<N; i++){
               new_P[S[i]] = P[i];
            }


            if(Arrays.equals(new_P, start_cards)) {
                flag = false;
                break;
            }

            cnt++;
            P = new_P.clone();
        }

        System.out.println(flag ? cnt : -1);
    }
}