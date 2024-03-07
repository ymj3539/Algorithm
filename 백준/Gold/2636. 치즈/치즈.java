import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static Queue<Point> airQueue = new LinkedList<>();
    static Queue<Point> cheeseQueue = new LinkedList<>();
    static boolean[][] visited;
    static int cheeseCnt = 0;

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if(map[r][c] == 1) cheeseCnt++;
            }
        }

        airQueue.offer(new Point(0, 0));
        visited[0][0] = true;

        int sec = 0;
        int cnt = 0;
        while(cheeseCnt > 0){
            // 바깥공기 체크
            bfs();
            cnt = cheeseQueue.size();
            // 치즈 녹이기
            while(!cheeseQueue.isEmpty()){
                Point cur = cheeseQueue.poll();
                map[cur.r][cur.c] = 0;
                cheeseCnt--;
                airQueue.offer(new Point(cur.r, cur.c));
            }

            sec++;
        }

        System.out.println(sec);
        System.out.println(cnt);

    }

    static void bfs(){
        while(!airQueue.isEmpty()) {
            Point cur = airQueue.poll();

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc]) continue;
                if(map[dr][dc] == 1) {
                    cheeseQueue.offer(new Point(dr, dc));
                    visited[dr][dc] = true;
                    continue;
                }

                airQueue.offer(new Point(dr, dc));
                visited[dr][dc] = true;
            }

        }
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