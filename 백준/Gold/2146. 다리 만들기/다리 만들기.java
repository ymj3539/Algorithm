import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int N;
    static int island_num;
    static int Min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        island_num = 1;

        for(int r=0; r<N; r++){
            for (int c=0; c<N; c++){
                if(map[r][c] == 1 && !visited[r][c]){
                    divide(new Point(r, c));

                }
            }
        }

//        for(int[] i : map) {
//            System.out.println(Arrays.toString(i));
//        }

        visited = new boolean[N][N];

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(!visited[r][c] && map[r][c] != 0){
                    bfs(r, c);
                }
            }
        }

        System.out.println(Min);

    }

    // 다리 최소값 구하기
    static void bfs(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        boolean[][] visited2 = new boolean[N][N];
        visited2[r][c] = true;

        int depth = 0;
        int num = map[r][c]; // 섬 번호

        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                Point cur = queue.poll();

                if(depth > Min) return;


                for(int d=0; d<4; d++){
                    int dr = cur.r + dy[d];
                    int dc = cur.c + dx[d];

                    if(!isIn(dr,dc)) continue;
                    if(visited2[dr][dc]) continue;
                    if(map[dr][dc] == num) continue;

                    // 0이 아니고, 현재 섬 번호와 다른 번호
                    if(map[dr][dc] != 0){

                        Min = Math.min(Min, depth);
                    }else {
                        queue.offer(new Point(dr, dc));
                        visited2[dr][dc] = true;
                    }


                }
            }

            depth++;

        }

    }


    // 섬 구분
    static void divide(Point point){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        visited[point.r][point.c] = true;
        map[point.r][point.c] = island_num;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int d=0; d<4; d++){
                int dr = cur.r + dy[d];
                int dc = cur.c + dx[d];

                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc]) continue;
                if(map[dr][dc] == 0) continue;


                map[dr][dc] = island_num;
                visited[dr][dc] = true;
                queue.offer(new Point(dr, dc));
            }
        }

        island_num++;
    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<N);
    }

    static class Point{
        int r, c, length;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        public Point (int r, int c, int length){
            this.r = r;
            this.c = c;
            this.length = length;

        }
    }
}