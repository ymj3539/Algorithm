import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int M, N;
    static int[][] deltas = {
            {0, -1},
            {-1, -1},
            {-1, 0}
    };
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[M][M];

        for(int[] i : map){
            Arrays.fill(i, 1);
        }

        int[] inc = new int[2*M-1];
        int[] inc_cnt = new int[3];

        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            inc_cnt[0] = Integer.parseInt(tokens.nextToken());
            inc_cnt[1] = Integer.parseInt(tokens.nextToken());
            inc_cnt[2] = Integer.parseInt(tokens.nextToken());

            int index =0;
            int start = 0;
            for(int j=start; j<inc_cnt[0]; j++){
                inc[index++] = 0;
            }

            start = index;
            for(int j=start; j<inc_cnt[0]+inc_cnt[1]; j++){
                inc[index++] = 1;
            }

            start = index;
            for(int j=start; j<2*M-1; j++){
                inc[index++] = 2;
            }

            // 애벌레 증가 시키기
            // 맵 복제
            int[][] new_map = new int[M+1][M+1];
            for(int r=0; r<M; r++){
                new_map[r] = map[r].clone();
            }

            index = 0;
            // 왼쪽 아래 -> 위
            for(int r=M-1; r>=0; r--){
                new_map[r][0] += inc[index++];
            }

            // 최상단 왼쪽 -> 오른쪽
            for(int c=1; c<M; c++){
                new_map[0][c] += inc[index++];
            }

            // 나머지 애벌레 증가시키기 (1,1) ~ (M-1, M-1)
            for(int r=1; r<M; r++){
                for(int c=1; c<M; c++){
                    // L, D, U 중 최대값 찾기
                    int max = Integer.MIN_VALUE;
                    for(int d=0; d<3; d++){
                        int dr = r + deltas[d][0];
                        int dc = c + deltas[d][1];
                        max = Math.max(max, new_map[dr][dc] - map[dr][dc]);
                    }

                    // 최댓값을 현재 좌표에 더하기
                    new_map[r][c] += max;
                }
            }

            map = new_map;
        }

        StringBuilder sb = new StringBuilder();
        for(int r=0; r<M; r++){
            for(int c=0; c<M; c++){
                sb.append(map[r][c]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}