import java.io.*;
import java.util.*;
public class Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static int[] dy = {1, 1, -1, -1};
    static int[] dx = {1, -1, -1, 1};
    static int MAX;
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++)
        {
            N = Integer.parseInt(input.readLine());
            map = new int[N][N];
            MAX = Integer.MIN_VALUE;
            boolean[] dessert = new boolean[101];

            for(int r=0; r<N; r++){
                tokens = new StringTokenizer(input.readLine());
                for(int c=0; c<N; c++){
                    map[r][c] = Integer.parseInt(tokens.nextToken());
                    if(!dessert[map[r][c]]) dessert[map[r][c]] = true;
                }
            }


            // 시작점 돌기
            for(int r=0; r<N-2; r++){
                for(int c=1; c<N-1; c++){
                    boolean[] new_dessert = dessert.clone();
                    dfs(r, c, r, c, 0, new_dessert, 0);
                }
            }

            // 출력
            sb.append("#"+t+" "+(MAX == Integer.MIN_VALUE ? -1 : MAX)+"\n");

        }

        System.out.println(sb);
    }

    /*
    * 시작점, 현재좌표, 진행방향, 디저트방문체크배열, 디저트 수
    * */
    static void dfs(int sr, int sc, int r, int c, int dir, boolean[] dessert, int cnt){
        if(sr == r && sc == c && cnt != 0) {
            MAX = Math.max(MAX, cnt);
            return;
        }

        // 1. 진행 방향으로 한 칸 더 감
        int dr1 = r + dy[dir];
        int dc1 = c + dx[dir];

        if(isIn(dr1, dc1) && dessert[map[dr1][dc1]]){
            dessert[map[dr1][dc1]] = false;
            dfs(sr, sc, dr1, dc1, dir, dessert, cnt+1);
            dessert[map[dr1][dc1]] = true;
        }

        // 2. 다음 진행방향으로 한 칸 가
        if(dir < 3) {
            int dr2 = r + dy[dir+1];
            int dc2 = c + dx[dir+1];

            if(isIn(dr2, dc2) && dessert[map[dr2][dc2]]){
                dessert[map[dr2][dc2]] = false;
                dfs(sr, sc, dr2, dc2, dir+1, dessert, cnt+1);
                dessert[map[dr2][dc2]] = true;
            }

        }

    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }

}