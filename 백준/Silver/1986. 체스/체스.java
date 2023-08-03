import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int n, m;
    static boolean[][] map;
    static boolean[][] QKP;
    static Point[] Queen;
    static Point[] Knight;
    static Point[] Pawn;
    static int[] qdy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] qdx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] kdy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] kdx = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        map = new boolean[n+1][m+1];
        QKP = new boolean[n+1][m+1];

        tokens = new StringTokenizer(input.readLine());
        Queen = new Point[Integer.parseInt(tokens.nextToken())];
        for(int i=0; i<Queen.length; i++){
            int r = Integer.parseInt(tokens.nextToken());
            int c =  Integer.parseInt(tokens.nextToken());

            Queen[i] = new Point(r,c);
            QKP[r][c] = true;
            map[r][c] = true;
        }

        tokens = new StringTokenizer(input.readLine());
        Knight = new Point[Integer.parseInt(tokens.nextToken())];
        for(int i=0; i<Knight.length; i++){
            int r = Integer.parseInt(tokens.nextToken());
            int c =  Integer.parseInt(tokens.nextToken());

            Knight[i] = new Point(r,c);
            QKP[r][c] = true;
            map[r][c] = true;
        }

        tokens = new StringTokenizer(input.readLine());
        Pawn = new Point[Integer.parseInt(tokens.nextToken())];
        for(int i=0; i<Pawn.length; i++){
            int r = Integer.parseInt(tokens.nextToken());
            int c =  Integer.parseInt(tokens.nextToken());

            Pawn[i] = new Point(r,c);
            QKP[r][c] = true;
            map[r][c] = true;
        }

        // 퀸이 갈 수 있는곳
        for(int i=0; i<Queen.length; i++){
            int r = Queen[i].r;
            int c = Queen[i].c;

            for(int d=0; d<8; d++){
                int dr = r;
                int dc = c;

                while(true){
                    dr += qdy[d];
                    dc += qdx[d];
                    if(!isIn(dr, dc)) break;
                    if(QKP[dr][dc]) break;
                    map[dr][dc] = true;
                }
            }
        }

        // 나이트가 갈 수 있는곳
        for(int i=0; i<Knight.length; i++){
            int r = Knight[i].r;
            int c = Knight[i].c;

            for(int d=0; d<8; d++){
                int dr = r + kdy[d];
                int dc = c + kdx[d];

                if(!isIn(dr, dc)) continue;
                if(QKP[dr][dc]) continue;

                if(!map[dr][dc]) {
                    map[dr][dc] = true;
                }

            }
        }
        int ans =0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(!map[i][j]) ans++;
            }
        }



        System.out.println(ans);
    }

    static boolean isIn(int r, int c){
        return (r>=1 && r<=n && c>=1 && c<=m);
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}