import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int G, R;
    static int[][] Map;
    static List<Point> list = new ArrayList<>();
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int Max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        G = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());



        Map = new int[N][M];
        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                Map[r][c] = Integer.parseInt(tokens.nextToken());
                if(Map[r][c] == 2) list.add(new Point(r, c));
            }
        }

        // 초록색 뿌릴 곳 선택(조합)
        comb_G(0, new int[G], new boolean[list.size()], 0);
        System.out.println(Max);
    }

    static int spread(int[] green, int[] red, int[][] map){
        Queue<Point> queue = new LinkedList<>();
        int[][] visited_time = new int[N][M];
        int flower_cnt = 0;

        // 초록색 - 큐에 넣기
        for(int i=0; i<G; i++){
            int idx = green[i];
            Point tmp = list.get(idx);
            map[tmp.r][tmp.c] = 3;
            queue.offer(new Point(tmp.r, tmp.c, 3));
            visited_time[tmp.r][tmp.c] = 3;
        }

        // 빨간색 - 큐에 넣기
        for(int i=0; i<R; i++){
            int idx = red[i];
            Point tmp = list.get(idx);
            map[tmp.r][tmp.c] = 4;
            queue.offer(new Point(tmp.r, tmp.c, 4));
            visited_time[tmp.r][tmp.c] = 4;
        }

        int time = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            Set<Point> flowers = new HashSet<>();

            while(size-->0){
                Point cur = queue.poll();
                if(map[cur.r][cur.c] == 5) continue;


                for(int d=0; d<4; d++){
                    int dr = cur.r + dy[d];
                    int dc = cur.c + dx[d];

                    if(!isIn(dr, dc)) continue;
                    if(map[dr][dc] == 0) continue;


                    // 아직 배양액 안 퍼짐
                    if(map[dr][dc] != 3 && map[dr][dc] != 4 && map[dr][dc] != 5){
                        queue.offer(new Point(dr, dc, cur.color));
                        visited_time[dr][dc] = time+1;
                        map[dr][dc] = cur.color;
                    }

                    // 가려는 곳 : 빨강, 진행하는 배양액 : 초록
                    else if(map[dr][dc] == 3 && cur.color == 4 && visited_time[dr][dc] == time+1){
                        map[dr][dc] = 5;
                        flowers.add(new Point(dr, dc));
                    }
                    // 가려는 곳 : 초록, 진행하는 배양액 : 빨강
                    else if(map[dr][dc] == 4 && cur.color == 3 && visited_time[dr][dc] == time+1){
                        map[dr][dc] = 5;
                        flowers.add(new Point(dr, dc));
                    }
                }
            }
            time++;
            flower_cnt += flowers.size();
        }

        return flower_cnt;
    }

    static void comb_R(int nth, int[] red, boolean[] visited, int idx, int[] green){
        if(nth == R){
            // 뿌리기
            // map 복사
            int[][] copymap = new int[N][M];
            for(int r=0; r<N; r++){
                copymap[r] = Map[r].clone();
            }

            // 배양액 퍼짐
            int flower_cnt = spread(green, red, copymap);
            Max = Math.max(Max, flower_cnt);

            return;
        }

        for(int i=idx; i<list.size(); i++){
            if(visited[i]) continue;
            red[nth] = i;
            visited[i] = true;
            comb_R(nth+1, red, visited, i+1, green);
            visited[i] = false;
        }
    }

    static void comb_G(int nth, int[] green, boolean[] visited, int idx){
        if(nth == G){
            // 빨간색 뿌릴 곳 선택(조합)
            comb_R(0, new int[R], visited, 0, green);
            return;
        }

        for(int i=idx; i<list.size(); i++){
            if(visited[i]) continue;
            green[nth] = i;
            visited[i] = true;
            comb_G(nth+1, green, visited, i+1);
            visited[i] = false;
        }
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }

    static class Point{
        int r, c;
        int color;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int color){
            this.r =r;
            this.c =c;
            this.color = color;
        }
    }
}