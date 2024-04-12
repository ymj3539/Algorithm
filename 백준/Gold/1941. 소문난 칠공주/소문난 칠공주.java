import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int ans;
    static Point[] points = new Point[25];
    public static void main(String[] args) throws Exception{
        int idx = 0;
        for(int r=0; r<5; r++){
            String str = input.readLine();
            for(int c=0; c<5; c++){
                map[r][c] = str.charAt(c);
                points[idx++] = new Point(r, c);
            }
        }

        // 조합
        Point[] choosed = new Point[7];
        boolean[][] visited = new boolean[5][5];
        comb(0, choosed, visited, 0, 0);


        System.out.println(ans);

    }

    static void comb(int nth, Point[] choosed, boolean[][] visited, int idx, int y_cnt){
        if(y_cnt > 3) return;
        if(nth == 7){
            // 다 연결되어있는지 확인
            if(checked(choosed, visited)) ans++;
            return;
        }

        for(int i=idx; i<25; i++){
            choosed[nth] = points[i];
            visited[points[i].r][points[i].c] = true;
            comb(nth+1, choosed, visited,i+1,
                    map[points[i].r][points[i].c] == 'Y' ? y_cnt+1 : y_cnt);

            visited[points[i].r][points[i].c] = false;
        }
    }

    static boolean checked(Point[] choosed, boolean[][] visited){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited2 = new boolean[5][5];
        int cnt = 1;

        queue.offer(new Point(choosed[0].r, choosed[0].c));
        visited2[choosed[0].r][choosed[0].c] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr, dc)) continue;
                if(!visited[dr][dc]) continue;
                if(visited2[dr][dc]) continue;

                queue.offer(new Point(dr, dc));
                visited2[dr][dc] = true;
                cnt++;

            }
        }

        if(cnt == 7) return true;
        else return false;

    }

    static boolean isIn(int r, int c){
        return r>=0 && r<5 && c>=0 && c<5;
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}