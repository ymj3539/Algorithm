import java.awt.font.ShapeGraphicAttribute;
import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static int ans_sand; // 구해야되는 값 : 밖으로 떨어진 모래들
    static int[] dy = {0, 1, 0, -1};    // 좌 하 우 상
    static int[] dx = {-1, 0, 1, 0};
    static int[][] sand_dxy = {
            {0, -2, 5}, // y, x, 비율
            {1, -1, 10},
            {-1, -1, 10},
            {2, 0, 2},
            {1, 0, 7},
            {-1, 0, 7},
            {-2, 0, 2},
            {1, 1, 1,},
            {-1, 1, 1},
            {0, -1, 1} // 알파(9)
    };
    static Tornado tornado;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());

            }
        }

        // 토네이도 좌표
        tornado = new Tornado(N/2, N/2, 0);

        // 토네이도가 몇 칸 움직이고 방향전환 하는지 체크하는 cnt
        int moveCnt = 1;

        outer : while(true){

            for(int k=0; k<2; k++){
                // moveCnt 만큼 한칸씩 이동
                for(int i=0; i<moveCnt; i++){
                    // 토네이도 소멸 지점
                    if(tornado.r == 0 && tornado.c == 0) break outer;

                    // 이동
                    tornado.r += dy[tornado.dir];
                    tornado.c += dx[tornado.dir];


                    // 모래 퍼짐
                    if(isIn(tornado.r,tornado.c)){
                        sandMove(tornado.r, tornado.c);
                    }
                }

                // 방향 전환
                tornado.dir += 1;
                if(tornado.dir > 3) tornado.dir = 0;

            }

            // moveCnt 증가
            moveCnt++;
        }

        System.out.println(ans_sand);

    }

    // y좌표(r,c)
    static void sandMove(int r, int c){
        int dir = tornado.dir;
        int dr = 0;
        int dc = 0;
        int total_sand = 0;
        int y_sand = map[r][c];

        // sand dir 배열 돌면서 모래 이동
        for(int i=0; i<10; i++){

            // 비율 좌표
            // 왼쪽으로 이동할 때
            if(dir == 0){
                dr = r + sand_dxy[i][0];
                dc = c + sand_dxy[i][1];
            }
            // 아래 방향일때
            else if(dir == 1){
                dr = r + sand_dxy[i][1] * (-1);
                dc = c + sand_dxy[i][0];
            }
            // 오른쪽 방향일때
            else if(dir == 2){
                dr = r + sand_dxy[i][0];
                dc = c + sand_dxy[i][1]*(-1);
            }
            // 위쪽 방향일때
            else if(dir == 3){
                dr = r + sand_dxy[i][1];
                dc = c + sand_dxy[i][0] * (-1);
            }

            // y모래에 대한 비율 계산
            int movedSand = (int)(y_sand * (0.01) * sand_dxy[i][2]);


            // 알파 일때
            if(i == 9){
                if(isIn(dr, dc)){
                    // 알파(남은 모래) 계산
                    map[dr][dc] += (map[r][c] - total_sand);
                }else {
                    ans_sand += (map[r][c] - total_sand); // 밖으로 떨어진 모래
                }
            }
            // 알파가 아닐 때
            else{
                if(isIn(dr, dc)){
                    // 비율 적용
                    map[dr][dc] += movedSand;
                    total_sand += movedSand; // y모래에서 얼마나 감소됐는지 합치는 변수
                }else{
                    ans_sand += movedSand; // 밖으로 떨어진 모래
                    total_sand += movedSand; // y모래에서 얼마나 감소됐는지 합치는 변수
                }
            }
        }
        // y모래 감소
        map[r][c] = 0;
    }

    static boolean isIn(int r, int c){
        return (r>=0 && c>=0 && r<N && c<N);
    }

    static class Tornado{
        int r, c, dir;
        public Tornado(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

    }
}