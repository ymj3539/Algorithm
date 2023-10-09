import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, n, Q;
    static int[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());
        N = (int)Math.pow(2, n); // map 한변의 길이

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }


        // Q번의 파이어스톰 시전
        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<Q; i++){
            int l = Integer.parseInt(tokens.nextToken());
            int L = (int)Math.pow(2, l);

            // 1. 부분 배열 회전
            int[][] new_map = new int[N][N];
            for(int r=0; r<N; r+=L){
                for(int c=0; c<N; c+=L){
                    // 90도 회전
                    rotate(r, c, L, new_map);
                }
            }

            map = new_map;


            // 2. 얼음 녹이기
            melt();

        }

        // 3. 남은 얼음 합, 가장 큰 얼음 덩어리 찾기
        int total = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(map[r][c] > 0) {
                    total += map[r][c];
                    if(!visited[r][c]) {
                        MAX = Math.max(MAX, bfs(r,c));
                    }
                }
            }
        }

        System.out.println(total+"\n"+(MAX == Integer.MIN_VALUE ? 0 : MAX));
    }

    static int bfs(int r, int c){
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r,c});
        visited[r][c] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i=0; i<4; i++){
                int dr = cur[0] + dy[i];
                int dc = cur[1] + dx[i];

                if(!isIn(dr, dc)) continue;
                if(map[dr][dc] <= 0) continue;
                if(visited[dr][dc]) continue;

                queue.offer(new int[] {dr, dc});
                visited[dr][dc] = true;
                cnt++;
            }
        }

        return cnt;
    }

    static void rotate(int r, int c, int L, int[][] new_map){
        for(int i=0; i<L; i++){
            for(int j=0; j<L; j++){
                new_map[r+j][c+i] = map[r+L-1-i][c+j];
            }
        }

    }

    static void melt() {
        int[][] new_map = new int[N][N];
        for(int i=0; i<N; i++){
            new_map[i] = map[i].clone();
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                int cnt = 0;
                for(int d=0; d<4; d++){
                    int dr = r + dy[d];
                    int dc = c + dx[d];

                    if(!isIn(dr, dc)) continue;
                    if(map[dr][dc] <= 0) continue;
                    cnt++;
                }

                if(cnt < 3){
                    new_map[r][c] = map[r][c] - 1;
                }
            }
        }
        map = new_map;
    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<N);
    }
}