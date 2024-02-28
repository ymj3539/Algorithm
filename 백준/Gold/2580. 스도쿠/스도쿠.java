import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] MAP;
    static boolean Flag = false;
    static List<Point> list;
    public static void main(String[] args) throws Exception{
        MAP = new int[9][9];

        list = new ArrayList<>();
        for(int i=0; i<9; i++){
            tokens = new StringTokenizer(input.readLine());
            for(int j=0; j<9; j++) {
                MAP[i][j] = Integer.parseInt(tokens.nextToken());
                if(MAP[i][j] == 0) {
                    list.add(new Point(i, j));
                }
            }
        }

        dfs(0);


    }

    static void dfs(int nth){
        if(Flag) return;
        if(nth == list.size()){
            Flag = true;
            // 정답 배열에 카피

            for(int r=0; r<9; r++){
                for(int c=0; c<9; c++){
                    System.out.print(MAP[r][c]+" ");
                }
                System.out.println();
            }


            return;
        }


        int r = list.get(nth).r;
        int c = list.get(nth).c;

        for(int i=1; i<=9; i++){
            if(check(r, c, i)){
                MAP[r][c] = i;
                dfs(nth+1);
                MAP[r][c] = 0;
            }
        }
    }

    // 스도쿠 조건 체크
    static boolean check(int r, int c, int num){
        // 가로줄 체크
        for(int i=0; i<9; i++){
            if(MAP[r][i] == num) return false;
        }
        // 세로줄 체크
        for(int i=0; i<9; i++){
            if(MAP[i][c] == num) return false;
        }

        // 사각형 체크
        int sr = (r/3) * 3;
        int sc = (c/3) * 3;
        for(int i=sr; i<sr+3; i++){
            for(int j=sc; j<sc+3; j++){
                if(MAP[i][j] == num) return false;
            }
        }

        return true;
    }



    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}