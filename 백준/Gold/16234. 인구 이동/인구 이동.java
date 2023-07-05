import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        map = new int[N][N];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        int day = 0;
        boolean flag;

        while(true){
            visited = new boolean[N][N];
            flag = false;

            // map 전체 탐색
             for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(!visited[r][c]) {
                        if(move(r, c)) {
                            flag = true;
                        }
                    }
                }
            }

            if(!flag) break;
            else day++;
        }
        System.out.println(day);
    }

    static boolean move(int r, int c){
        Queue<Country> queue = new LinkedList<>();
        queue.offer(new Country(r, c));
        int sum = map[r][c];
        List<Country> list = new ArrayList<>();
        list.add(new Country(r,c));
        visited[r][c] = true;
        while(!queue.isEmpty()){
            Country cur = queue.poll();

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc]) continue;
                // 인구차 체크
                if(!check(cur.r,cur.c,dr,dc)) continue;

                queue.offer(new Country(dr, dc));
                visited[dr][dc] = true;
                sum += map[dr][dc];
                list.add(new Country(dr,dc));
            }
        }
        int cnt = list.size();
        if(cnt >1){

            int population = sum/cnt;

            for(Country country : list) {
                map[country.r][country.c] = population;
            }
            return true;

        }else return false;
    }

    static boolean check(int r, int c, int dr, int dc){
        int tmp = Math.abs(map[r][c] - map[dr][dc]);
        return (L<=tmp && tmp<=R);
    }

    static boolean isIn(int r, int c){
        return (r>=0 && c>=0 && r<N && c<N);
    }

    static class Country{
        int r, c;


        public Country(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString(){
            return "("+this.r+", "+this.c+")";
        }
    }
}