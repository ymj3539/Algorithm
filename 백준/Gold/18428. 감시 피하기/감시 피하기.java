import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static char[][] map;
    static List<Point> Teachers = new ArrayList<>();
    static List<Point> object_list = new ArrayList<>();
    static int Teachers_size;
    static int N;
    static boolean flag;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        map = new char[N][N];
        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = tokens.nextToken().charAt(0);
                if(map[r][c] == 'T') {
                    Teachers.add(new Point(r,c));
                }
                else if(map[r][c] == 'X') {
                    object_list.add(new Point(r, c));
                }
            }
        }

        Teachers_size = Teachers.size();
        flag = false;
        pick(0, new Point[3], 0);

        System.out.println(flag ? "YES" : "NO");
    }

    static void pick(int cnt, Point[] objects, int idx){
        if(flag) return;
        if(cnt == 3){
            // 감시 피할 수 있는 지 확인 하러 가자
            // 맵에 표시
            for(int i=0; i<3; i++){
                map[objects[i].r][objects[i].c] = 'O';
            }

            if(canPass()) {
                flag = true;
                return;
            }

            for(int i=0; i<3; i++){
                map[objects[i].r][objects[i].c] = 'X';
            }
            return;
        }

        for(int i=idx; i<object_list.size(); i++){
            objects[cnt] = object_list.get(i);
            pick(cnt+1, objects, i+1);
        }
    }

    static boolean canPass(){
        for(int i =0; i<Teachers_size; i++){

            for(int d=0; d<4; d++){
                int dr = Teachers.get(i).r;
                int dc = Teachers.get(i).c;

                while(true){
                    dr += dy[d];
                    dc += dx[d];

                    if(!isIn(dr,dc)) break;
                    if(map[dr][dc] == 'O') break;
                    if(map[dr][dc] == 'S') return false;
                }
            }

        }

        return true;
    }

    static boolean isIn(int r, int c){
        return (r>=0 && c>=0 && r<N && c<N);
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}