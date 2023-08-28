import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][][] maze = new int[5][5][5];
    static int Min = Integer.MAX_VALUE;
    static int[][] deltas = {
            {0,0,-1},
            {-1,0,0},
            {0,1,0},
            {0,0,1},
            {1,0,0},
            {0,-1,0}
    };
    public static void main(String[] args) throws Exception{
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                tokens = new StringTokenizer(input.readLine());
                for(int k=0; k<5; k++){
                    maze[i][j][k] = Integer.parseInt(tokens.nextToken());
                }
            }
        }

//        for(int i=0; i<5; i++){
//            for(int[] j : maze[i]){
//                System.out.println(Arrays.toString(j));
//            }
//        }


        // 판 쌓기
        perm(0, new int[5], new boolean[5]);

        System.out.println(Min == Integer.MAX_VALUE ? -1 : Min);


    }

    static void perm(int nth, int[] choosed, boolean[] visited) {
        if(nth == 5){
            // 순서대로 판 쌓기
            int[][][] new_maze = new int[5][5][5];
            for(int i=0; i<5; i++){
                new_maze[i] = maze[choosed[i]];
            }

            // 회전 시키기
            turn(0, new_maze);

            return;
        }

        for(int i=0; i<5; i++){
            if(!visited[i]){
                choosed[nth] = i;
                visited[i] = true;
                perm(nth+1, choosed, visited);
                visited[i] = false;
            }
        }
    }

    static void turn(int nth, int[][][] maze){
        if(nth == 5){
            // bfs 탐색
            if(maze[0][0][0] == 0) return;
            int depth = bfs(maze);
            Min = Math.min(depth, Min);
            return;
        }

        // nth번째 판의 방향 정하기
        for(int i=0; i<4; i++){
            int[][] new_arr = new int[5][5];
            for(int r=0; r<5; r++){
                for(int c=0; c<5; c++){
                    new_arr[c][5-1-r] = maze[nth][r][c];
                }
            }

            maze[nth] = new_arr;
            turn(nth+1, maze);
        }


    }

    static int bfs(int[][][] maze){
        Queue<Point> queue = new LinkedList();
        boolean[][][] visited = new boolean[5][5][5];
        queue.offer(new Point(0,0,0));
        visited[0][0][0] = true;

        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                Point cur = queue.poll();
                if(cur.r == 4 && cur.c == 4 && cur.z == 4){
                    return depth;
                }

                for(int i=0; i<6; i++){
                    int dr = cur.r + deltas[i][0];
                    int dc = cur.c + deltas[i][1];
                    int dz = cur.z + deltas[i][2];

                    if(!isIn(dr, dc, dz)) continue;
                    if(visited[dr][dc][dz]) continue;
                    if(maze[dr][dc][dz] == 0) continue;

                    queue.offer(new Point(dr, dc, dz));
                    visited[dr][dc][dz] = true;
                }
            }

            depth++;
        }

        return Integer.MAX_VALUE;
    }

    static boolean isIn(int r, int c, int z){
        return (r>=0 && c>=0 && z>=0 && r<5 && c<5 && z<5);
    }

    static class Point{
        int r, c, z;
        public Point(int r, int c, int z){
            this.r = r;
            this.c = c;
            this.z = z;
        }
    }

}