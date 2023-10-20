import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int M, N, H; // M:가로(x), N:세로(y), H:높이(z)
    static int[][][] map;

    static int[] dz = {-1, 0, 0, 1, 0, 0};
    static int[] dy = {0, -1, 0, 0, 1, 0};
    static int[] dx = {0,  0, 1, 0, 0, -1};
    static List<Point> list;
    static int raw_cnt = 0;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        map = new int[H][N][M];
        list = new ArrayList<>();

        for(int h=H-1; h>=0; h--){
            for(int r=0; r<N; r++){
                tokens = new StringTokenizer(input.readLine());
                for(int c=0; c<M; c++){
                    map[h][r][c] = Integer.parseInt(tokens.nextToken());
                    if(map[h][r][c] == 1) {
                        list.add(new Point(h, r, c));
                    }else if(map[h][r][c] == 0){
                        raw_cnt++;
                    }
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    static int bfs(){
        Queue<Point> queue = new LinkedList();
        boolean[][][] visited = new boolean[H][N][M];
        for(Point p : list){
            queue.offer(p);
            visited[p.h][p.r][p.c] = true;
        }

        int day = 0;
        while(!queue.isEmpty()){
            if(raw_cnt == 0) return day;
            int size= queue.size();

            while(size-->0){
                Point cur = queue.poll();

                for(int i=0; i<6; i++){
                    int dh = cur.h + dz[i];
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];

                    if(!isIn(dh, dr, dc)) continue;
                    if(map[dh][dr][dc] == -1 || map[dh][dr][dc] == 1) continue;
                    if(visited[dh][dr][dc]) continue;

                    queue.offer(new Point(dh, dr, dc));
                    visited[dh][dr][dc] = true;
                    map[dh][dr][dc] = 1;
                    raw_cnt--;
                }
            }
            day++;
        }

        return -1;
    }

    static boolean isIn(int h, int r, int c){
        return (h>=0 && h<H && r>=0 && r<N && c>=0 && c<M);
    }

    static class Point{
        int h, r, c;
        public Point(int h, int r, int c){
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }
}