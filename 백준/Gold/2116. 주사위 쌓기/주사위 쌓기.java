import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] dices;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        dices = new int[N][6];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<6; c++){
                dices[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }


        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 5);
        map.put(1,3);
        map.put(2,4);
        map.put(3,1);
        map.put(4,2);
        map.put(5,0);

        int max = Integer.MIN_VALUE;
        // 맨 밑 숫자 고르기
        for(int i=0; i<6; i++){
            int sum =0;
            int bottom = i;

            // 주사위 수만큼 반복
            for(int n=0; n<N; n++){
                int top = map.get(bottom);
                
                int side_max = Integer.MIN_VALUE;
                // 옆면 중 가장 큰 수 고르기
                for(int j=0; j<6; j++){
                    if(j == bottom || j == top) continue;

                    side_max = Math.max(side_max, dices[n][j]);
                }

                // sum에 더하기
                sum += side_max;

                // 그 다음 주사위의 바닥 값 = 현재 주사위의 맨 위 값
                // 바닥값의 인덱스 찾기
                if(n < N-1){
                    for(int index =0; index<6; index++){
                        if(dices[n][top] == dices[n+1][index]){
                            bottom = index;
                            break;
                        }
                    }

                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}