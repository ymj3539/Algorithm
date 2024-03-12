import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        map = new int[N][N];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        int[] list = new int[5];
        dfs(0, list);

        System.out.println(max);
    }

    static void dfs(int move_cnt, int[] list){
//        System.out.println(move_cnt+"-------"+Arrays.toString(list));
//        for(int i=0; i<N; i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
        if(move_cnt == 5){
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    max = Math.max(max, map[r][c]);
                }
            }

            return;
        }

        int[][] copy = new int[N][N];
        for(int i=0; i<N; i++){
            copy[i] = map[i].clone();
        }

        int tmp = list[move_cnt];
        for(int i=1; i<=4; i++){
            move(i, move_cnt);
            list[move_cnt] = i;
            dfs(move_cnt+1, list);
            list[move_cnt] = tmp;
            for(int j=0; j<N; j++){
                map[j] = copy[j].clone();
            }
        }

    }

    static void move(int dir, int move_cnt){
        // dir : 1(오른쪽), 2(왼쪽), 3(위), 4(아래)
        if(dir == 1){
            // 오른쪽 이동
            for(int r=0; r<N; r++){
                int idx = N-1;
                int pre_block = 0;

                for(int c=N-1; c>=0; c--){
                    // 블록이 0이면 패스
                    if(map[r][c] == 0) continue;
                    // 블록이 0이 아니면
                    // 앞에 블록과 합칠 수 있는지 확인
                    if(map[r][c] == pre_block){
                        // 합치기
                        map[r][idx+1] *= 2;
                        pre_block = 0;
                        // 비우기
                        map[r][c] =0;
                    }
                    // idx위치에 현재 블록 넣기
                    else {
                        pre_block = map[r][c];
                        // 비우기
                        map[r][c] =0;
                        map[r][idx] = pre_block;
                        idx--;

                    }

//                    if(move_cnt == 0){
//                        System.out.println(r+" "+c+" "+idx+" =============");
//                        System.out.println(Arrays.toString(map[r]));
//                    }
                }
            }

        } else if(dir == 2){
            // 왼쪽 이동
            for(int r=0; r<N; r++){
                int idx = 0;
                int pre_block = 0;

                for(int c=0; c<N; c++){
                    // 블록이 0이면 패스
                    if(map[r][c] == 0) continue;
                    // 블록이 0이 아니면
                    // 앞에 블록과 합칠 수 있는지 확인
                    if(map[r][c] == pre_block){
                        // 합치기
                        map[r][idx-1] *= 2;
                        pre_block = 0;
                        // 비우기
                        map[r][c] =0;
                    }
                    // 그냥 옮기기
                    else {
                        pre_block = map[r][c];
                        // 비우기
                        map[r][c] =0;
                        map[r][idx] = pre_block;
                        idx++;
                    }
                }
            }
        } else if(dir == 3){
            // 위로 이동
            for(int c=0; c<N; c++){
                int idx = 0;
                int pre_block = 0;

                for(int r=0; r<N; r++){
                    // 블록이 0이면 패스
                    if(map[r][c] == 0) continue;
                    // 블록이 0이 아니면
                    // 앞에 블록과 합칠 수 있는지 확인
                    if(map[r][c] == pre_block){
                        // 합치기
                        map[idx-1][c] *= 2;
                        pre_block = 0;
                        // 비우기
                        map[r][c] =0;
                    }
                    // 그냥 옮기기
                    else {
                        pre_block = map[r][c];
                        // 비우기
                        map[r][c] =0;
                        map[idx][c] = pre_block;
                        idx++;
                    }
                }
            }
        } else {
            // 아래로 이동
            for(int c=0; c<N; c++){
                int idx = N-1;
                int pre_block = 0;

                for(int r=N-1; r>=0; r--){
                    // 블록이 0이면 패스
                    if(map[r][c] == 0) continue;
                    // 블록이 0이 아니면
                    // 앞에 블록과 합칠 수 있는지 확인
                    if(map[r][c] == pre_block){
                        // 합치기
                        map[idx+1][c] *= 2;
                        pre_block = 0;
                        // 비우기
                        map[r][c] =0;
                    }
                    // 그냥 옮기기
                    else {
                        pre_block = map[r][c];
                        // 비우기
                        map[r][c] =0;
                        map[idx][c] = pre_block;
                        idx--;
                    }
                }
            }
        }


    }

}