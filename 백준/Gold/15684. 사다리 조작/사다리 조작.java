import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, H;
    static int[][] ladder;
    static boolean Flag;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        ladder = new int[H+1][N+1];

        // 사다리 놓기
        // 현재 위치 오른쪽에 가로선이 있으면 -> 1, 왼쪽에 가로선이 있으면 -> 2
        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            ladder[a][b] = 1;
            ladder[a][b+1] = 2;
        }

        int answer = -1;
        // 추가해야되는 가로선의 최솟값 구하기
        for(int i=0; i<=3; i++){
            comb(1, 0, i);
            if(Flag) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    static void comb(int nr, int cnt, int size){
        if(Flag) return;
        if(cnt == size){
            // 사다리 조건에 맞는지 체크
            if(check()) {
                Flag = true;
            }
            return;
        }

        for(int r=nr; r<=H; r++){
            for(int c=1; c<N; c++){
                if(ladder[r][c] == 1) continue;
                if(ladder[r][c] == 2) continue;
                if(ladder[r][c+1] == 1) continue;

                ladder[r][c] = 1;
                ladder[r][c+1] = 2;
                comb(r, cnt+1, size);
                ladder[r][c] = 0;
                ladder[r][c+1] = 0;
            }
        }
    }

    static boolean check(){
        for(int start_idx=1; start_idx<=N; start_idx++){
            int cur_c = start_idx;
            for(int r=1; r<=H; r++){
                if(ladder[r][cur_c] == 1){
                    cur_c++;
                }else if(ladder[r][cur_c] == 2) {
                    cur_c--;
                }
            }

            if(cur_c != start_idx) return false;

        }

        return true;
    }
}