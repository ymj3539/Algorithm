import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static List<Point> virus;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1 , 0, 0};
    static int answer = Integer.MAX_VALUE;
    static int BLANK_CNT;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        virus = new ArrayList<>();
        map = new int[N][N];
        BLANK_CNT = 0;

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if(map[r][c] == 2) virus.add(new Point(r, c));
                else if(map[r][c] == 0) BLANK_CNT++;
            }
        }

        // M개의 바이러스 고르기
        comb(0, new Point[M], 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    static void comb(int nth, Point[] choosed, int idx){
        // 선택한 바이러스로 bfs
        if(nth == M){
            int result = bfs(choosed);
            answer = Math.min(result, answer);

            return;
        }

        int size = virus.size();
        for(int i=idx; i<size; i++){
            choosed[nth] = virus.get(i);
            comb(nth+1, choosed, i+1);
        }
    }

    static int bfs(Point[] choosed){
        int[][] newMap = new int[N][N];

        for(int r=0; r<N; r++){
            newMap[r] = map[r].clone();
        }

        int blank_cnt = BLANK_CNT;

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        for(Point p : choosed){
            queue.offer(new Point(p.r, p.c));
            visited[p.r][p.c] = true;
        }

        int time = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            if(blank_cnt == 0) return time;
            while(size-->0){
                Point cur = queue.poll();

                for(int i=0; i<4; i++){
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];

                    if(!isIn(dr, dc)) continue;
                    if(visited[dr][dc]) continue;
                    if(newMap[dr][dc] == 1) continue;

                    if(newMap[dr][dc] == 0) blank_cnt--;
                    visited[dr][dc] = true;
                    queue.offer(new Point(dr, dc));
                    newMap[dr][dc] = 2;

                }
            }

            time++;
        }
        
        // 다 퍼졌는지 확인
        boolean flag = true;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(newMap[r][c] == 0) flag = false;
            }
        }

        return flag ? time : Integer.MAX_VALUE;
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

    }


}