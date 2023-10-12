import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int R, C, T;
    static int[][] room;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static List<Point> cleaner = new ArrayList();
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        room = new int[R][C];

        for(int r=0; r<R; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<C; c++){
                room[r][c] = Integer.parseInt(tokens.nextToken());
                if(room[r][c] == -1) cleaner.add(new Point(r, c));
            }
        }

        for(int t=0; t<T; t++){
            // 1. 미세먼지 확산
            int[][] new_room = new int[R][C];
            for(int r=0; r<R; r++){
                new_room[r] = room[r].clone();
            }

            for(int r=0; r<R; r++){
                for(int c=0; c<C; c++){
                    // 미세먼지가 있으면 확산
                    if(room[r][c] >= 0){
                        int dust = (int)room[r][c] / 5;
                        // 인접한 4방향으로 확산
                        for(int i=0; i<4; i++){
                            int dr = r + dy[i];
                            int dc = c + dx[i];

                            if(!isIn(dr, dc)) continue;
                            if(room[dr][dc] == -1) continue;

                            new_room[dr][dc] += dust;
                            new_room[r][c] -= dust;
                        }
                    }
                }
            }

            room = new_room;

            // 2. 공기청정기 작동
            // 위쪽 공기 확산
            int nr = cleaner.get(0).r -1;
            int nc = cleaner.get(0).c;
            for(int r = nr; r>=1; r--){
                room[nr][nc] = room[--nr][nc];
            }
            for(int c=nc; c<C-1; c++){
                room[nr][nc] = room[nr][++nc];
            }
            for(int r = nr; r<cleaner.get(0).r; r++){
                room[nr][nc] = room[++nr][nc];
            }
            for(int c=nc; c>1; c--){
                room[nr][nc] = room[nr][--nc];
            }
            room[nr][nc] = 0;


            // 아래쪽 공기 확산
            nr = cleaner.get(1).r + 1;
            nc = cleaner.get(1).c;
            for(int r = nr; r<R-1; r++){
                room[nr][nc] = room[++nr][nc];
            }
            for(int c = nc; c<C-1; c++){
                room[nr][nc] = room[nr][++nc];
            }
            for(int r=nr; r>=cleaner.get(1).r + 1; r--){
                room[nr][nc] = room[--nr][nc];
            }
            for(int c=nc; c>1; c--){
                room[nr][nc] = room[nr][--nc];
            }

            room[nr][nc] =0;

        }

        // 남은 미세먼지의 양 구하기
        int total = 0;
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(room[r][c] > 0) total += room[r][c];
            }
        }

        System.out.println(total);

    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<R && c>=0 && c<C);
    }
}