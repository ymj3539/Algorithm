import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static List<Integer>[] gears;
    static int T;
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(input.readLine());

        gears = new List[T+1];

        for(int i=1; i<=T; i++){
            String str = input.readLine();
            gears[i] = new ArrayList<>();
            for(int j=0; j<8; j++){
                gears[i].add(str.charAt(j) - '0');
            }
        }

        int k = Integer.parseInt(input.readLine());

        for(int i=0; i<k; i++){
            tokens = new StringTokenizer(input.readLine());

            int num = Integer.parseInt(tokens.nextToken());
            int dir = Integer.parseInt(tokens.nextToken());

            dfs(num, dir, new boolean[T+1]);
        }

        // 0번 index가 1인 것의 갯수
        int cnt = 0;
        for(int i=1; i<=T; i++){
            if(gears[i].get(0) == 1) cnt++;
        }

        System.out.println(cnt);
    }

    static void dfs(int num, int dir, boolean[] visited){
        if(visited[num]) return;

        // 현재 톱니 회전
        int idx2 = gears[num].get(2);
        int idx6 = gears[num].get(6);
        turn(num, dir);
        visited[num] = true;

        // 왼쪽(6번) 체크 -> 2번) (6번
        if(num > 1) {
            // 다르면 회전
            if(gears[num-1].get(2) != idx6){
                dfs(num-1, dir*(-1), visited);
            }
        }

        // 오른쪽(2번) 체크 -> 2번) (6번
        if(num < T){
            // 다르면 회전
            if(gears[num+1].get(6) != idx2){
                dfs(num+1, dir*(-1), visited);
            }
        }
    }

    static void turn(int num, int dir) {
        // 시계 방향
        if(dir == 1){
            int tmp = gears[num].remove(7);
            gears[num].add(0, tmp);
        }

        else {
            int tmp = gears[num].remove(0);
            gears[num].add(7, tmp);
        }
    }
}