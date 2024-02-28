import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static List<Integer>[] Gears;
    static int K;
    public static void main(String[] args) throws Exception {
        Gears = new List[5];

        for(int i=1; i<=4; i++){
            Gears[i] = new ArrayList<Integer>();
            String str = input.readLine();
            for(int j=0; j<8; j++){
                Gears[i].add(str.charAt(j)-'0');
            }
//            System.out.println(Gears[i].toString());
        }

        K = Integer.parseInt(input.readLine());

        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            int gearNum = Integer.parseInt(tokens.nextToken());
            int dir = Integer.parseInt(tokens.nextToken());

            // 돌리기
            turn(gearNum, dir, new boolean[5]);
        }

        int sum = 0;
        for(int i =1; i<=4; i++){
            if(Gears[i].get(0) == 1){
                sum += Math.pow(2, i-1);
            }
        }

        System.out.println(sum);
    }

    static void turn(int cur, int dir, boolean[] visited){
        if(visited[cur]) return;
        visited[cur] = true;
        // 현재 톱니의 왼쪽
        int left  = cur - 1;
        // 현재 톱니의 오른쪽
        int right = cur + 1;

        // 왼쪽 확인
        if(left >= 1){
            // 왼쪽 톱니의 2번과 현재톱니의 6번 확인
            // 다르면 회전
            if(Gears[left].get(2) != Gears[cur].get(6)){
                // 왼쪽 톱니 회전시키러 가기
                turn(left, dir*(-1), visited);
            }
        }
        // 오른쪽 확인
        if(right <=4){
            // 오른쪽 톱니의 6번과 현재톱니의 2번 확인
            // 다르면 회전
            if(Gears[cur].get(2) != Gears[right].get(6)){
                // 왼쪽 톱니 회전시키러 가기
                turn(right, dir*(-1),visited);
            }
        }
        // 현재 톱니 돌리기
        // 시계 방향 회전
        if(dir == 1) {
            int tmp = (int)Gears[cur].remove(7);
            Gears[cur].add(0, tmp);
        }else if(dir == -1){
            int tmp = (int)Gears[cur].remove(0);
            Gears[cur].add(tmp);
        }
    }
}