import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] belt;
    static boolean[] robot;
    static int N, K;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(input.readLine());

        belt = new int[N*2];
        robot = new boolean[N*2];
        for(int i=0; i<N*2; i++){
            belt[i] = Integer.parseInt(tokens.nextToken());
        }

        int round = 0;    // 단계

        while (true){
            round++;
            // 회전
            int tmp = belt[N*2-1];
            boolean robot_tmp = robot[N*2-1];
            for(int i=N*2-1; i>0; i--){
                // 내구도 변경(한칸씩 밀기)
                belt[i] = belt[i-1];
                // 로봇 위치 변경(벨트와 함께 이동)
                robot[i] = robot[i-1];
                if(i == N) robot[i] = false;
            }
            belt[0] = tmp;
            robot[0] = robot_tmp;

            // 로봇 이동
            robot[N-1] = false;
            for(int i = N-2; i>=0; i--){
                if(robot[i]) {
                    // 이동하려는 칸에 로봇 있나 체크
                    if(robot[i+1]) continue;
                    // 내구도 1이상 남아있나 확인
                    if(belt[i+1] < 1) continue;
                    // 이동
                    robot[i+1] = true;
                    robot[i] = false;
                    belt[i+1]--;
                }

            }

            // 로봇 올려
            if(!robot[0] && (belt[0] != 0)){
                robot[0] = true;
                belt[0]--;
            }
            int cnt = 0;

            // 내구도 개수 체크
            for(int i=0; i<N*2; i++){
                if(belt[i] == 0) cnt++;
            }

            if(cnt >= K) break;
        }

        System.out.println(round);

    }
}