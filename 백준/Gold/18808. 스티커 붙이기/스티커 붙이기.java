import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, K;
    static int R, C;
    static int[][] Notebook;
    static int[][] Sticker;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        Notebook = new int[N][M];

        // 스티커 갯수만큼 반복
        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            Sticker = new int[R][C];


            // 스티커 입력받기
            for(int r=0; r<R; r++){
                tokens = new StringTokenizer(input.readLine());
                for(int c=0; c<C; c++){
                    Sticker[r][c] = Integer.parseInt(tokens.nextToken());
                }
            }


            // 범위 체크
            int spin_cnt = 0;
            while(spin_cnt<=3){
                if(!(R <= N && C <= M)) {
                    spin();
                    spin_cnt++;
                }else break;
            }

            // 스티커 붙이기
            outer : while(spin_cnt <= 3){
                // 스티커 한칸씩 옮기면서 붙일 수 있나 체크
                for(int r=0; r<=N-R; r++){
                    for(int c=0; c<=M-C; c++){
                        // 시작점 r,c
                        // 붙일 수 있나 체크
                            if(canStick(r, c)) {
                                // 붙일 수 있으면 넘어가
                                break outer;
                            }
                            // 붙일 수 없으면 한칸 옮겨 -> continue;

                        }
                    }

                // 붙일 수 없으면 회전시키기
                // 회전
                spin();
                spin_cnt++;
            }
        }
        // 스티커 갯수 카운트
        int sticker_cnt = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(Notebook[r][c] == 1){
                    sticker_cnt++;
                }
            }
        }

        System.out.println(sticker_cnt);
    }

    static void spin(){
        int[][] spin_Sticker = new int[C][R];
        for(int r=0; r<R; r++){

            for(int c=0; c<C; c++){
                spin_Sticker[c][R-r-1] = Sticker[r][c];
            }
        }

        Sticker = spin_Sticker;
        int tmp = R;
        R = C;
        C = tmp;
    }

    static boolean canStick(int r, int c){
        // 노트북 배열 복사
        int[][] copy_notebook = new int[N][M];
        for(int i=0; i<N; i++){
            copy_notebook[i] = Notebook[i].clone();
        }

        // 범위 체크
        if(!(R <= N && C <= M)) return false;


        // 스티커 붙일 수 있는지 확인
        for(int dr =r; dr<r+R; dr++){
            for(int dc = c; dc<c+C; dc++){
                // 스티커가 붙을 자리일때
                if(Sticker[dr-r][dc-c] == 1){
                    // 노트북에 아무것도 없으면
                    if(Notebook[dr][dc] == 0){
                        copy_notebook[dr][dc] = 1;

                    }
                    // 뭐가 붙어있으면 스티커 붙일 수 없음
                    if(Notebook[dr][dc] == 1) {
                        return false;
                    }
                }
            }
        }

        Notebook = copy_notebook;
        return true;
    }
}