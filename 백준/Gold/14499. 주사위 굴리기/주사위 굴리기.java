import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, K;
    static int[][] map;

    static int[] dy = {0, 0, 0, -1, 1}; // 우 좌 위 아래
    static int[] dx = {0, 1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        int x = Integer.parseInt(tokens.nextToken());
        int y = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        Dice dice = new Dice(0, 0, 0, 0, 0, 0, x, y);

        map = new int[N][M];
        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<K; i++){
            int dir = Integer.parseInt(tokens.nextToken());
            int dr = dice.r + dy[dir];
            int dc = dice.c + dx[dir];
            if(!isIn(dr, dc)) continue;

            if(dir == 1) dice.goEast();
            else if(dir == 2) dice.goWest();
            else if(dir == 3) dice.goNorth();
            else dice.goSouth();

            // 상단 숫자 출력
            sb.append(dice.up+"\n");

            // 바닥면 복사
            if(map[dice.r][dice.c] == 0) {
                // 칸 <- 바닥면
                map[dice.r][dice.c] = dice.down;
            }else{
                // 바닥면 <- 칸
                dice.down = map[dice.r][dice.c];
                // 칸은 0으로 초기화
                map[dice.r][dice.c] = 0;
            }
        }
        System.out.println(sb);
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }

    static class Dice{
        int up, down, front, back, left, right;
        int r, c;

        public Dice(int up, int down, int front, int back, int left, int right, int r, int c){
            this.up = up;
            this.down = down;
            this.front = front;
            this.back = back;
            this.left = left;
            this.right = right;
            this.r = r;
            this.c = c;
        }

        public void goEast(){
            int up_tmp = this.up;
            this.up = this.left;
            this.left = this.down;
            this.down = this.right;
            this.right = up_tmp;
            this.r = this.r + dy[1];
            this.c = this.c + dx[1];
        }

        public void goSouth(){
            int up_tmp = this.up;
            this.up = this.back;
            this.back = this.down;
            this.down = this.front;
            this.front = up_tmp;
            this.r = this.r + dy[4];
            this.c = this.c + dx[4];
        }

        public void goWest(){
            int up_tmp = this.up;
            this.up = this.right;
            this.right = this.down;
            this.down = this.left;
            this.left = up_tmp;
            this.r = this.r + dy[2];
            this.c = this.c + dx[2];
        }

        public void goNorth(){
            int up_tmp = this.up;
            this.up = this.front;
            this.front = this.down;
            this.down = this.back;
            this.back = up_tmp;
            this.r = this.r + dy[3];
            this.c = this.c + dx[3];
        }

    }
}