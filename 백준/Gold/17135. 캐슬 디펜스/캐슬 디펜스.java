import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, D; // N: 행, M: 열, D: 궁수의 공격 거리 제한
    static int[][] map;
    static int ENEMY;
    static int[] dy = {0, -1, 0};
    static int[] dx = {-1, 0, 1};
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args)throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        D = Integer.parseInt(tokens.nextToken());

        map = new int[N+1][M];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if(map[r][c] == 1) ENEMY++;
            }
        }

        // 궁수 배치
        comb(0, new Point[3], 0);

        System.out.println(MAX);
    }

    static void comb(int nth, Point[] selected, int idx){
        if(nth == 3){
            // 배치된 궁수로 시뮬돌리기
            play(selected);
            return;
        }

        for(int i=idx; i<M; i++){
            selected[nth] = new Point(N, i);
            comb(nth+1, selected, i+1);
        }
    }

    static void play(Point[] selected){
        // 궁수 배치
        int[][] copymap = new int[N+1][M];
        for(int r=0; r<N; r++){
            copymap[r] = map[r].clone();
        }

        int total_enemy = ENEMY;
        int remove_enemy_cnt = 0;


        while(total_enemy>0){
            // 궁수 공격
            for(int i=0; i<3; i++){
                copymap = attack(copymap, selected[i]);
            }

            for(int r=N; r>=0; r--) {
                for (int c = 0; c < M; c++) {
                    if(r == N) {
                        if(copymap[r][c] == 1) {
                            total_enemy--;
                            copymap[r][c] = 0;
                        }
                    }

                    else {
                        // 적 삭제
                        if (copymap[r][c] < 0) {
                            copymap[r][c] = 0;
                            remove_enemy_cnt++;
                            total_enemy--;
                        }

                        // 적 이동
                        else if (copymap[r][c] == 1) {
                            copymap[r][c] = 0;
                            copymap[r+1][c] = 1;
                        }

                    }

                }
            }
        }

        MAX = Math.max(MAX, remove_enemy_cnt);


    }

    static int[][] attack(int[][] copymap, Point archer){
        // 가장 가까운 적 구하기
        Queue<Point> queue = new LinkedList<>();
        queue.offer(archer);
        boolean[][] visited  = new boolean[N+1][M];
        visited[archer.r][archer.c] = true;

        int depth = 1;

        outer : while(!queue.isEmpty()){
            if(depth > D) break outer;
            int size = queue.size();
            while(size-->0){
                Point cur = queue.poll();

                for(int i=0; i<3; i++){
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];

                    if(!isIn(dr, dc)) continue;
                    if(visited[dr][dc]) continue;
                    if(copymap[dr][dc] != 0) {
                        copymap[dr][dc] -= 2;
                        break outer;
                    }else {
                        queue.offer(new Point(dr, dc));
                        visited[dr][dc] = true;
                    }
                }

            }
            depth++;
        }



        return copymap;
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}