import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] map;
    static int N, K;
    static Queue<Point>[] virusArr;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        map = new int[N+1][N+1];
        virusArr = new Queue[K+1];

        for(int i=1; i<=K; i++){
            virusArr[i] = new LinkedList<>();
        }

        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if(map[r][c] > 0) virusArr[map[r][c]].offer(new Point(r, c));
            }
        }

        tokens = new StringTokenizer(input.readLine());
        int S = Integer.parseInt(tokens.nextToken());
        int X = Integer.parseInt(tokens.nextToken());
        int Y = Integer.parseInt(tokens.nextToken());

        // S번 반복
        for(int i=0; i<S; i++) {
            // 바이러스 이동
            for(int k=1; k<=K; k++){
                Queue<Point> queue = virusArr[k];
                int size = queue.size();

                for(int j=0; j<size; j++){
                    Point cur = queue.poll();
                    // 증식
                    for(int d=0; d<4; d++){
                        int dr = cur.r + dy[d];
                        int dc = cur.c + dx[d];

                        if(!isIn(dr, dc)) continue;
                        if(map[dr][dc] > 0) continue;

                        map[dr][dc] = k;
                        queue.offer(new Point(dr, dc));
                    }
                }
            }
        }

        System.out.println(map[X][Y]);

    }

    static boolean isIn(int r, int c){
        return r>=0 && r<=N && c>=0 && c<=N;
    }


    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}