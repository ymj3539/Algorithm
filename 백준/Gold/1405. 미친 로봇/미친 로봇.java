import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] dy = {0, 0, 1, -1}; // 동 서 남 북
    static int[] dx = {1, -1, 0, 0};
    static int[] percents;
    static int N;
    static double total_percent;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        percents = new int[4];
        N = Integer.parseInt(tokens.nextToken());
        for(int i=0; i<4; i++){
            percents[i] = Integer.parseInt(tokens.nextToken());
        }

        boolean[][] visited = new boolean[30][30];
        visited[15][15] = true;

        dfs(0, 15, 15, 1, visited);

        System.out.println(total_percent);
    }

    static void dfs(int nth, int r, int c, double percent, boolean[][] visited){
        if(nth == N){
            total_percent += percent;
            return;
        }

        for(int i=0; i<4; i++){
            int dr = r + dy[i];
            int dc = c + dx[i];

            if(percents[i] == 0) continue;
            if(visited[dr][dc]) continue;
            visited[dr][dc] = true;
            dfs(nth+1, dr, dc, percent * percents[i] * (0.01), visited);
            visited[dr][dc] = false;
        }
    }
}