import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static int[][] favorite;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        map = new int[N+1][N+1];

        favorite = new int[N*N+1][5];
        for(int i=1; i<=N*N; i++){
            tokens = new StringTokenizer(input.readLine());
            int stu_num = Integer.parseInt(tokens.nextToken());
            for(int j=0; j<4; j++){
                favorite[stu_num][j] = Integer.parseInt(tokens.nextToken());
            }
            sit(stu_num);
        }

        // 만족도 계산
        System.out.println(cal());


    }
    static int cal(){
        int sum = 0;
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                int cnt = 0;
                int stu_num = map[r][c];
                for(int i=0; i<4; i++){
                    int dr = r + dy[i];
                    int dc = c + dx[i];

                    if(!isIn(dr, dc)) continue;
                    for(int s=0; s<4; s++){
                        if(favorite[stu_num][s] == map[dr][dc]) {
                            cnt++;
                            break;
                        }
                    }
                }

                if(cnt!=0) sum += Math.pow(10, (cnt-1));
            }
        }

        return sum;
    }

    static void sit(int stu){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        // 빈자리 탐색
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                // 빈자리라면
                if(map[r][c] == 0){
                    // 주변에 선호하는 학생 몇명인지 탐색
                    int fav_stu_cnt = 0;
                    int empty = 0;
                    for(int i=0; i<4; i++){
                        int dr = r + dy[i];
                        int dc = c + dx[i];

                        if(!isIn(dr, dc)) continue;
                        if(map[dr][dc] == 0) {
                            empty++;
                            continue;
                        }
                        for(int s=0; s<4; s++){
                            if(favorite[stu][s] == map[dr][dc]) {
                                fav_stu_cnt++;
                                break;
                            }
                        }

                    }
                    // pq에 담기
                    pq.offer(new Point(r, c, fav_stu_cnt, empty));
                }
            }
        }

        // pq에서 하나 꺼내서 맵에 체크
        if(!pq.isEmpty()){
            Point cur = pq.poll();
            map[cur.r][cur.c] = stu;
        }

    }

    static boolean isIn(int r, int c) {
        return r>=1 && r<=N && c>=1 && c<=N;
    }

    static class Point implements Comparable<Point>{
        int r, c;
        int fav_cnt;
        int empty_cnt;
        public Point(int r, int c, int f, int e){
            this.r = r;
            this.c = c;
            this.fav_cnt = f;
            this.empty_cnt = e;
        }

        @Override
        public int compareTo(Point p){
            if(this.fav_cnt == p.fav_cnt){
                if(this.empty_cnt == p.empty_cnt){
                    if(this.r == p.r) {
                        return this.c - p.c;
                    }
                    return this.r - p.r;
                }
                return p.empty_cnt - this.empty_cnt;
            }
            return p.fav_cnt - this.fav_cnt;
        }
    }
}