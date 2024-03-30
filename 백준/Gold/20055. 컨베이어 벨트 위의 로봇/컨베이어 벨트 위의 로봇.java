import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    static int[] belt;
    static boolean[] robots;
    static int zero_cnt = 0;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        belt = new int[2*N];
        robots = new boolean[N];

        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<2*N; i++){
            belt[i] = Integer.parseInt(tokens.nextToken());
        }

        // 시뮬레이션
        int step = 1;
        while(true){
            // 1. 벨트와 로봇 한칸씩 회전
            move();
            // 2. 로봇 이동
            moveRobot();
            // 3. 로봇 올리기
            if(belt[0] >= 1) {
                robots[0] = true;
                belt[0]--;
                if(belt[0] == 0) zero_cnt++;
            }
            // 4. 내구도 0인 칸 찾기
            if(zero_cnt >= K) break;
            step++;
        }
        System.out.println(step);

    }

    static void moveRobot(){
        // 2N 위치에 로봇있으면 한칸 이동 -> 내리기
        if(robots[N-1]) robots[N-1] = false;
        // 나머지 로봇 이동
        for(int i=N-2; i>=0; i--){
            if(robots[i] && !robots[i+1] && belt[i+1] >=1) {
                robots[i+1] = true;
                robots[i] = false;
                belt[i+1]--;
                if(belt[i+1] == 0) zero_cnt++;
            }
        }
    }

    static void move(){
        // 벨트 이동
        int last = belt[2*N-1];
        for(int i=2*N-1; i>0; i--){
            belt[i] = belt[i-1];
        }
        belt[0] = last;

        // 로봇 이동
        for(int i=N-1; i>0; i--){
            robots[i] = robots[i-1];
        }
        robots[0] = false;
    }

}