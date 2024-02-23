import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static String[][] map;
    public static void main(String[] args) throws Exception{
        N =Integer.parseInt(input.readLine());

        map = new String[N][N];

        for(int r=0; r<N; r++){
            String str = input.readLine();
            for(int c=0; c<N; c++){
                map[r][c] = String.valueOf(str.charAt(c));
            }
        }

        // 지뢰 체크
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(map[r][c].equals("#")) {
                    check(r, c);
                }
            }
        }

        int cnt = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(map[r][c].equals("#")) cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void check(int r, int c){
        for(int i=0; i<8; i++){
            int dr = r + dy[i];
            int dc = c + dx[i];

            if(!isIn(dr, dc)) continue;
            if(map[dr][dc].equals("x") || map[dr][dc].equals("#")) continue;

            if(Integer.parseInt(map[dr][dc]) == 0) {
                map[r][c] = "x";
                return;
            }
        }

        for(int i=0; i<8; i++){
            int dr = r + dy[i];
            int dc = c + dx[i];

            if(!isIn(dr, dc)) continue;
            if(map[dr][dc].equals("x") || map[dr][dc].equals("#")) continue;

            if(Integer.parseInt(map[dr][dc]) != 0) {
                int tmp = Integer.parseInt(map[dr][dc]) -1;
                map[dr][dc] = String.valueOf(tmp);
            }
        }
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }
}