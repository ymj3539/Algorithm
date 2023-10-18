import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));
    static StringTokenizer tokens;
    static int[][] map;
    static int N, M;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];

        tokens = new StringTokenizer(input.readLine());
        int R = Integer.parseInt(tokens.nextToken());
        int C = Integer.parseInt(tokens.nextToken());
        int D = Integer.parseInt(tokens.nextToken());
        Robot robot = new Robot(R, C, D);

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }




        int cnt = 0;

        // 작동
        while(true){
            // 1. 현재 칸 청소
            if(map[robot.r][robot.c] == 0){
                map[robot.r][robot.c] = 2;
                cnt++;
            }

            // 2. 주변에 빈칸 체크
            boolean flag = true;
            for(int i=0; i<4; i++){
                int dr = robot.r + dy[i];
                int dc = robot.c + dx[i];

                if(!isIn(dr, dc)) continue;
                // 빈칸 있음
                if(map[dr][dc] == 0) flag = false;
            }

            // 빈칸 없는 경우
            if(flag){
                // 후진 방향
                int back_dir = (robot.d + 2) % 4;
                int dr = robot.r + dy[back_dir];
                int dc = robot.c + dx[back_dir];
                // 후진O
                if(isIn(dr, dc) && map[dr][dc] != 1){
                    // 한칸 후진
                    robot.moveBack(back_dir);

                    // 1번으로 돌아가
                    continue;
                }
                // 후진X
                else{
                    // 작동 멈춤
                    break;
                }
            }


            // 빈칸 있는 경우
            else{
                // 반시계방향 90도 회전
                robot.turn();

                int dr = robot.r + dy[robot.d];
                int dc = robot.c + dx[robot.d];

                if(isIn(dr, dc) && map[dr][dc] == 0) {
                    robot.moveFront();
                }
            }
        }

        System.out.println(cnt);

    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<M);
    }


    static class Robot{
        int r, c, d;
        public Robot(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void moveBack(int dir){
            this.r = this.r + dy[dir];
            this.c = this.c + dx[dir];
        }

        public void turn(){
            this.d = ((d - 1) == -1 ? 3 : d-1);
        }

        public void moveFront(){
            this.r = this.r + dy[d];
            this.c = this.c + dx[d];
        }

        @Override
        public String toString(){
            return this.r +" "+this.c +" "+this.d;
        }
    }
}