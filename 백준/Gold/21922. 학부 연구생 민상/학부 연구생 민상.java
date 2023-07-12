import javax.annotation.processing.SupportedSourceVersion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static List<int[]> air_con = new ArrayList<>();
    static int[] dy = {0, 1, 0, -1};    // 우 하 좌 상
    static int[] dx = {1, 0, -1, 0};
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][4];
        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if(map[r][c] == 9) {
                    air_con.add(new int[] {r, c});
                    visited[r][c][0] = true;
                    visited[r][c][1] = true;
                    visited[r][c][2] = true;
                    visited[r][c][3] = true;
                }
            }
        }

        for (int[] arr : air_con){
            int ar = arr[0];
            int ac = arr[1];
            for(int i=0; i<4; i++){
                int dr = ar + dy[i];
                int dc = ac + dx[i];
                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc][i]) continue;
                visited[dr][dc][i] = true;
                dfs(dr, dc, i);
            }
        }

        int seat = 0;

        for(int r=0; r<N; r++){

            for(int c=0; c<M; c++){
                boolean flag = false;
                for(int k=0; k<4; k++){
                    if(visited[r][c][k]) flag = true;
                }
                if(flag) seat++;
            }
        }

        System.out.println(seat);
    }

    static void dfs(int r, int c, int dir){
//        System.out.println(r+", "+c+", "+dir);
//        for(int[] i : visited){
//            System.out.println(Arrays.toString(i));
//        }
        // 방향 전환
        // 1번 물건
        if(map[r][c] == 1){
            if(dir == 0) dir = 2;
            else if(dir == 2) dir = 0;
        }

        // 2번 물건
        else if(map[r][c] == 2){
            if(dir == 1) dir =3;
            else if(dir == 3) dir =1;
        }

        // 3번 물건
        else if(map[r][c] == 3){
            if(dir == 1) dir =2;
            else if(dir == 0) dir = 3;
            else if(dir == 3) dir = 0;
            else if(dir == 2) dir = 1;
        }

        // 4번 물건
        else if(map[r][c] == 4){
            if(dir == 1) dir = 0;
            else if(dir == 3) dir = 2;
            else if(dir == 0) dir = 1;
            else if(dir == 2) dir = 3;
        }


        // 다음 칸 isIn 체크
        int dr = r + dy[dir];
        int dc = c + dx[dir];

        if(isIn(dr, dc)) {
            if(!visited[dr][dc][dir]){
                visited[dr][dc][dir] = true;
                dfs(dr, dc, dir);

            }
        }
    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<M);
    }
}