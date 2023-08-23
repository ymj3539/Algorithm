import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, K;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static Fireball[][] map;
    static Queue<Fireball> queue;
    public static void main(String[] args)throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        queue = new LinkedList<>();
        Queue<Fireball> movedQueue;

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            int m = Integer.parseInt(tokens.nextToken());
            int s = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());
            queue.offer(new Fireball(r, c, m, s, d, 1));
        }

        // 명령
        for(int i=0; i<K; i++){
            // copymap 초기화
            map = new Fireball[N+1][N+1];
            movedQueue = new LinkedList<>();

            // 파이어볼 이동
            while(!queue.isEmpty()){
                Fireball fireball = queue.poll();

                int dr = fireball.r;
                int dc = fireball.c;

                // 속력만큼 이동
                for(int s =0; s<fireball.s; s++){
                    dr += dy[fireball.d];
                    dc += dx[fireball.d];

                    // 만약 범위 밖인 경우
                    if(dr<=0) dr = N;
                    if(dc<=0) dc = N;
                    if(dr>N) dr = 1;
                    if(dc>N) dc = 1;
                }

                // 이동한 fireball map에 표시
                if(map[dr][dc] == null){
                    map[dr][dc] = new Fireball(dr, dc, fireball.m, fireball.s, fireball.d, 1);

                }else {
                    map[dr][dc].m += fireball.m;
                    map[dr][dc].s += fireball.s;
                    map[dr][dc].cnt++;
//                    System.out.println(map[dr][dc].d+", "+fireball.d);
                    if(map[dr][dc].d % 2 != fireball.d % 2){
                        map[dr][dc].d = -2;
                    }
                }
            }


            // 2개 이상인 경우
            for(int r=1; r<=N; r++){
                for(int c=1; c<=N; c++){
                    if(map[r][c] == null) continue;


                    if(map[r][c].cnt >= 2){
                        // 합치기
                        int new_m = map[r][c].m / 5;
                        if(new_m  == 0) continue;
                        int new_s = map[r][c].s / map[r][c].cnt;

                        // 4개로 쪼개진 파이어볼 queue에 넣기
                        if(map[r][c].d == -2){
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 1, 1));
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 3, 1));
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 5, 1));
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 7, 1));
                        }else{
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 0, 1));
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 2, 1));
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 4, 1));
                            movedQueue.offer(new Fireball(r, c, new_m, new_s, 6, 1));
                        }


                    }else{
                        // 그냥 queue에 넣기
                        movedQueue.offer(new Fireball(r, c, map[r][c].m, map[r][c].s, map[r][c].d, map[r][c].cnt));
                    }
                }
            }
            queue = movedQueue;
        }


        int total_m = 0;
        while(!queue.isEmpty()){
            Fireball cur = queue.poll();
            total_m += cur.m;
        }

        System.out.println(total_m);
    }



    static class Fireball{
        int r, c, m, s, d, cnt;
        public Fireball(int r, int c, int m, int s, int d, int cnt){
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
            this.cnt = cnt;
        }

    }
}