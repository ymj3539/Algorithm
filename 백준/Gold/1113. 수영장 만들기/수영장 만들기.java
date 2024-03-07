import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] map;
    static int N, M;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int answer = 0;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        int maxHeight = Integer.MIN_VALUE;

        for(int r=0; r<N; r++){
            String str = input.readLine();
            for(int c=0; c<M; c++) {
                map[r][c] = str.charAt(c) - '0';
                maxHeight = Math.max(maxHeight, map[r][c]);
            }
        }

        // 낮은 높이 부터 탐색
        for(int h=1; h<maxHeight; h++){
            // map 전체 탐색
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(visited[r][c]) continue;
                    if(map[r][c] == h) {
                        bfs(r, c, h);

                    }
                }
            }
        }

        System.out.println(answer);

    }

    static void bfs(int rr, int cc, int num){
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> waterPoints = new LinkedList<>();
        queue.offer(new Point(rr, cc));
        waterPoints.offer(new Point(rr, cc));
        visited[rr][cc] = true;


        boolean isFlow = false;
        int minHeight = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                // 장외면 흘러넘침
                if(!isIn(dr, dc)) {
                    isFlow = true;
                    continue;
                }

                // 더 낮으면 흘러넘침
                if(num > map[dr][dc]){
                    isFlow = true;
                    continue;
                }
                // 더 높으면 벽
                if(num < map[dr][dc]) {
                    minHeight = Math.min(minHeight, map[dr][dc]);
                    continue;
                }

                if(visited[dr][dc]) continue;
                queue.offer(new Point(dr, dc));
                waterPoints.offer(new Point(dr, dc));
                visited[dr][dc] = true;
            }
        }
        if(!isFlow){
            while(!waterPoints.isEmpty()){
                Point p = waterPoints.poll();
                // 채울수 있는 물의 양
                int water = minHeight - map[p.r][p.c];
                // 체운만큼 물 total 증가
                answer += water;
                // 물 채우기
                map[p.r][p.c] = minHeight;
                // 다시 채워질 수 있으니 방문 취소로 되돌리기
                visited[p.r][p.c] = false;
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