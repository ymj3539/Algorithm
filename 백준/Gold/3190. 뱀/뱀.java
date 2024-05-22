import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K, L; // N : 보드 크기, K : 사과 개수, L : 방향 전환 횟수
    static int[][] map;
    static Dir[] dir_arr;
    static Deque<Point> Snake = new ArrayDeque<>();
    static int[] S_dir = {0, 1};
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        K = Integer.parseInt(input.readLine());
        map = new int[N+1][N+1];
        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());

            map[r][c] = 2; // 사과는 2로 표시
        }

        L = Integer.parseInt(input.readLine());
        dir_arr = new Dir[L];
        for(int i=0; i<L; i++){
            tokens = new StringTokenizer(input.readLine());
            int sec = Integer.parseInt(tokens.nextToken());
            char dir = tokens.nextToken().charAt(0);
            dir_arr[i] = new Dir(sec, dir);
        }

        Snake.offerFirst(new Point(1,1));
        map[1][1] = 1;

        int t = 1;
        int dir_i = 0;
        while(true){
            // 머리 이동 가능한지 확인
            Point Head = Snake.peekFirst();
            int dr = Head.r + S_dir[0];
            int dc = Head.c + S_dir[1];

            // 벽이나 자기자신 몸과 부딪히면 끝
            if(!isIn(dr, dc) || map[dr][dc] == 1){
                break;
            }

            // 이동한 칸에 사과가 있다면
            if(map[dr][dc] == 2){
                map[dr][dc] = 1;
            }

            // 사과가 없다면
            else {
                // 꼬리칸 비우기
                Point removed = Snake.removeLast();
                map[removed.r][removed.c] = 0;
            }

            // 머리 진짜 이동
            Snake.offerFirst(new Point(dr, dc));
            map[dr][dc] = 1;


            // 방향 전환
            if(dir_i<L && t == dir_arr[dir_i].sec){
                char dir = dir_arr[dir_i++].dir;
                int y = S_dir[0];
                int x = S_dir[1];
                if(dir == 'L'){
                    S_dir[0] = x*(-1);
                    S_dir[1] = y;
                }else{
                    S_dir[0] = x;
                    S_dir[1] = y*(-1);
                }
            }


            t++;

        }

        System.out.println(t);
    }

    static boolean isIn(int r, int c){
        return (r>=1 && r<=N && c>=1 && c<=N);
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    static class Dir{
        int sec;
        char dir;
        public Dir(int sec, char dir){
            this.sec = sec;
            this.dir = dir;
        }

    }
}