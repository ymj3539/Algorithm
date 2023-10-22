import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static int[] dy = {0, 0, 1 , -1};
    static int[] dx = {1, -1, 0, 0};
    static List<Point> melted;
    static Queue<Point> cheese;
    /*
    * bfs로 겉의 빈공간을 3으로 표시
    * 3과 접하는 곳이 3개 이상이면 녹일 치즈
    * 치즈 녹이기 -> 녹은치즈 3으로 바꾸고, list에 넣고 bfs 탐색
    * */
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        int cheeseCnt = 0;
        cheese = new LinkedList<>();

        map = new int[N][M];
        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if(map[r][c] == 1) {
                    cheese.offer(new Point(r,c));
                    cheeseCnt++;
                }
            }
        }

        melted = new ArrayList<>();
        melted.add(new Point(0, 0));

        int time = 0;
        while(cheeseCnt >0){
            // map에서 추가적으로 공기 유입되는 부분 처리
            bfs();

            melted = new ArrayList<>();

            // 녹일 치즈 찾기
            find();

            // 치즈 녹이기
            for(Point p : melted){
                // 3으로 표시
                map[p.r][p.c] = 3;
                // 치즈 갯수 감소
                cheeseCnt--;
            }
            time++;

        }
        System.out.println(time);

    }

    static void find(){
        int size = cheese.size();
        for(int i=0; i<size; i++){
            Point cur = cheese.poll();
            int cnt =0;
            for(int d=0; d<dy.length; d++){
                int dr = cur.r + dy[d];
                int dc = cur.c + dx[d];

                if(map[dr][dc] == 3) cnt++;
            }

            if(cnt >= 2) {
                melted.add(new Point(cur.r, cur.c));
            }else {
                cheese.offer(cur);
            }
        }
    }

    static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for(Point p : melted){
            queue.offer(p);
            visited[p.r][p.c] = true;
        }

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int i=0; i<dy.length; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr, dc)) continue;
                if(map[dr][dc] != 0) continue;
                if(visited[dr][dc]) continue;

                queue.offer(new Point(dr, dc));
                visited[dr][dc] = true;
                map[dr][dc] = 3;
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