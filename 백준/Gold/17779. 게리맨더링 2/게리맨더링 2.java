import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        map = new int[N+1][N+1];
        int[] pop;
        int ans = Integer.MAX_VALUE;

        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        for(int x=1; x<=N; x++){
            for(int y=1; y<=N; y++){
                for(int d1 = 1; d1<=N; d1++){
                    outer : for(int d2 = 1; d2<=N; d2++){
                        if(!isIn(x,y,d1,d2)) continue;
                        // 구역 체크 맵
                        int[][] new_map = new int[N+1][N+1];

                        // 인구수 배열 초기화
                        pop = new int[6];

                        // 경계선 체크
                        for(int i=0; i<=d1; i++){
                            // 1번 경계선
                            new_map[x+i][y-i] = 5;

                            // 2번 경계선
                            new_map[x+d2+i][y+d2-i] = 5;
                        }

                        for(int i=0; i<=d2; i++) {
                            // 2번 경계선
                            new_map[x + i][y + i] = 5;

                            // 3번 경계선
                            new_map[x + d1 + i][y - d1 + i] = 5;
                        }


                        // 맵돌면서 정해진 x, y, d1, d2로 구역 나누기
                        for(int r=1; r<=N; r++){
                            boolean flag = false;
                            for(int c=1; c<=N; c++){
                                // 5구역 먼저
                                if((r==x && c==y) || (r==x+d1+d2 && c==y-d1+d2)) {
                                    pop[5] += map[r][c];
                                    flag = false;
                                    continue;
                                }

                                if(new_map[r][c]==5 && !flag) {
                                    pop[5] += map[r][c];
                                    flag = true;
                                    continue;
                                }else if(new_map[r][c] == 5 && flag){
                                    pop[5] += map[r][c];
                                    flag = false;
                                    continue;
                                }

                                if(flag) {
                                    new_map[r][c] = 5;
                                    pop[5] += map[r][c];
                                    continue;
                                }


                                // 1구역
                                if(1<=r && r< x+d1 && 1<=c && c<=y){
                                    pop[1] += map[r][c];
                                    new_map[r][c] = 1;
                                }
                                // 2구역
                                else if(1<=r && r<=x+d2 && y<c && c<=N){
                                    pop[2] += map[r][c];
                                    new_map[r][c] = 2;
                                }
                                // 3구역
                                else if(x+d1<=r && r<=N && 1<=c && c<y-d1+d2){
                                    pop[3] += map[r][c];
                                    new_map[r][c] = 3;
                                }
                                // 4구역
                                else if(x+d2<r && r<=N && y-d1+d2<=c && c<=N){
                                    pop[4] += map[r][c];
                                    new_map[r][c] = 4;
                                }
                                // 5구역
                                else{
                                    pop[5] += map[r][c];
                                    new_map[r][c] = 5;
                                }
                            }
                        }

                        // 최소값 최대값 계산
                        Arrays.sort(pop);

                        int tmp = pop[5] - pop[1];

                        ans = Math.min(ans, tmp);

                    }
                }
            }
        }

        System.out.println(ans);

    }


    static boolean isIn(int x, int y, int d1, int d2){
        return (d1>=1 && d2>=1 &&
                x>=1 && x<(x+d1+d2) && (x+d1+d2)<=N &&
                1<=(y-d1) && (y-d1)<y && y < (y+d2) && (y+d2) <= N);
    }
}