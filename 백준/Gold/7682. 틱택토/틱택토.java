import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    public static void main(String[] args) throws Exception{

        StringBuilder sb = new StringBuilder();
        
        while(true){
            String str = input.readLine();
            if(str.equals("end")) break;

            map = new char[3][3];

            int idx = 0;

            int x_cnt = 0;
            int o_cnt = 0;
            int dot_cnt= 0;

            for(int r=0; r<3; r++){
                for(int c=0; c<3; c++){
                    if(str.charAt(idx) == 'X') x_cnt++;
                    else if(str.charAt(idx) == 'O') o_cnt++;
                    else dot_cnt++;
                    map[r][c] = str.charAt(idx++);
                }
            }

            // 조건 확인
            // 1. X, O 갯수 체크
            if(!(x_cnt == o_cnt || x_cnt == o_cnt+1)){
                sb.append("invalid");
                sb.append("\n");
                continue;
            }

            // 2. 가로, 세로, 대각선 1줄 만들어졌는지 체크
            int x_line_cnt = 0; // x 빙고 개수
            int o_line_cnt = 0; // o 빙고 개수

            // 가로
            for(int r=0; r<3; r++){
                if(map[r][0] == map[r][1] && map[r][1] == map[r][2]){
                    if(map[r][0] == '.') continue;
                    if(map[r][0] == 'O') o_line_cnt++;
                    else x_line_cnt++;
                }
            }

            // 세로
            for(int c=0; c<3; c++){
                if(map[0][c] == map[1][c] && map[1][c] == map[2][c]){
                    if(map[0][c]=='.') continue;
                    if(map[0][c] == 'O') o_line_cnt++;
                    else x_line_cnt++;
                }
            }


            // 대각선
            if(map[0][0] == map[1][1]  && map[1][1] == map[2][2]) {
                if(map[0][0] != '.'){
                    if(map[0][0] == 'O') o_line_cnt++;
                    else x_line_cnt++;
                }
            }else if(map[0][2] == map[1][1] && map[1][1] == map[2][0]){
                if(map[0][2] != '.'){
                    if(map[0][2] == 'O') o_line_cnt++;
                    else x_line_cnt++;
                }
            }

            // x빙고와 o빙고 둘다 존재 -> invalid
            if(x_line_cnt >= 1 && o_line_cnt>=1){
                sb.append("invalid\n");
                continue;
            }

            // 1줄이 아직 안 나왔는데 끝난 경우 -> invalid
            if((x_line_cnt<=0) && (o_line_cnt<=0) && (dot_cnt > 0)) {
                sb.append("invalid\n");
                continue;
            }

            // o 빙고가 존재할 때, o개수와 x개수는 같아야 함
            if(o_line_cnt>=1){
                if(o_cnt != x_cnt) {
                    sb.append("invalid\n");
                    continue;
                }
            }

            // x 빙고가 존재할 때, x개수가 o개수보다 1개 많아야 함
            if(x_line_cnt>=1){
                if(o_cnt >= x_cnt) {
                    sb.append("invalid\n");
                    continue;
                }
            }

            sb.append("valid\n");
        }

        System.out.println(sb);

    }


}