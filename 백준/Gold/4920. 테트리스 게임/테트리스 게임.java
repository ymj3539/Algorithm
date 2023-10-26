import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static int[][][] blocks = {
            {{0,0}, {0,1}, {0,2}, {0,3}}, // 0
            {{0,0}, {0,1}, {0,2}, {1,2}}, // 1
            {{0,0}, {0,1}, {1,1}, {1,2}}, // 2
            {{0,0}, {0,1}, {1,0}, {1,1}}, // 3
            {{0,0}, {0,1}, {0,2}, {1,1}}, // 4
            {{0,0}, {1,0}, {2,0}, {3,0}}, // 5
            {{0,1}, {1,1}, {2,0}, {2,1}}, // 6
            {{0,0}, {1,0}, {1,1}, {1,2}}, // 7
            {{0,0}, {0,1}, {1,0}, {2,0}}, // 8
            {{0,1}, {1,0}, {1,1}, {2,0}}, // 9
            {{0,1}, {1,0}, {1,1}, {2,1}}, // 10
            {{0,1}, {1,0}, {1,1}, {1,2}}, // 11
            {{0,0}, {1,0}, {1,1}, {2,0}} // 12
    };
    static int N;
    static long[][] map;
    public static void main(String[] args) throws Exception {
        int t = 0;
        while(true){
            t++;
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            if(N == 0) break;

            map = new long[N][N];
            for(int r=0; r<N; r++){
                tokens = new StringTokenizer(input.readLine());
                for(int c=0; c<N; c++){
                    map[r][c] = Long.parseLong(tokens.nextToken(" "));
                }
            }


            long max = Long.MIN_VALUE;
            // 시작점 지정 -> 전체 map 돌기
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){

                    // 블록 돌기
                    for(int i=0; i<blocks.length; i++){
                        long sum = 0;
                        boolean flag = true;
                        for(int d=0; d<4; d++){
                            int dr = r + blocks[i][d][0];
                            int dc = c + blocks[i][d][1];

                            if(!isIn(dr, dc)) {
                                flag = false;
                                break;
                            }
                            sum += map[dr][dc];
                        }
                        if(flag){
                            max = Math.max(max, sum);
                        }
                    }
                }
            }

            sb.append(t+". "+max+"\n");
        }

        System.out.println(sb);

    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<N);
    }

}