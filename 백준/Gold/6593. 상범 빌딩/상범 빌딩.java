import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int L, R, C;
    static char[][][] map;
    static Point start;
    static int[] dz = {-1, 0, 0, 1, 0, 0};
    static int[] dy = {0, -1, 0, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, 0, -1};
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();

        while(true){
            tokens = new StringTokenizer(input.readLine());

            L = Integer.parseInt(tokens.nextToken());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            if(L ==0 && R == 0 && C ==0) break;

            map = new char[L][R][C];

            for(int l=0; l<L; l++){
                for(int r=0; r<R; r++){
                    String str = input.readLine();
                    for(int c=0; c<C; c++){
                        map[l][r][c] = str.charAt(c);
                        if(map[l][r][c] == 'S') {
                            start = new Point(l, r, c, 0);
                        }
                    }
                }
                input.readLine();
            }
            //// 입력 끝
            sb.append(bfs()+"\n");


        }

        System.out.println(sb);

    }

    static String bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start.l, start.r, start.c, 0));
        boolean[][][] visited = new boolean[L][R][C];
        visited[start.l][start.r][start.c] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            if(map[cur.l][cur.r][cur.c] == 'E') return "Escaped in "+cur.moveCnt+" minute(s).";

            for(int i=0; i<6; i++){
                int dl = cur.l + dz[i];
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dl, dr, dc)) continue;
                if(visited[dl][dr][dc]) continue;
                if(map[dl][dr][dc] == '#') continue;

                queue.offer(new Point(dl, dr, dc, cur.moveCnt+1));
                visited[dl][dr][dc] = true;
            }

        }

        return "Trapped!";
    }

    static boolean isIn(int l, int r, int c){
        return l>=0 && l<L && r>=0 && r<R && c>=0 && c<C;
    }

    static class Point{
        int l, r, c, moveCnt;
        public Point(int l, int r, int c, int moveCnt){
            this.l = l;
            this.r = r;
            this.c = c;
            this.moveCnt = moveCnt;
        }
    }
}