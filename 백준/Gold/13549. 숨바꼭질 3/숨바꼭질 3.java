import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    static int[] v_time = new int[100001]; // 해당 위치에 방문하는 최단시간
    public static void main(String[] args)throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        Arrays.fill(v_time, Integer.MAX_VALUE);
        bfs(N);
        System.out.println(v_time[K]);
    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        v_time[start] = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(cur == K) return;

            // X-1 이동
            int dx1 = cur - 1;
            if(dx1 >=0 && dx1<=100000) {
                if(v_time[dx1] > v_time[cur] + 1){
                    v_time[dx1] = v_time[cur] + 1;
                    queue.offer(dx1);
                }
            }

            // X+1 이동
            int dx2 = cur + 1;
            if(dx2 >=0 && dx2<=100000) {
                if(v_time[dx2] > v_time[cur] + 1){
                    v_time[dx2] = v_time[cur] + 1;
                    queue.offer(dx2);
                }
            }

            // 2*X 이동
            int dx3 = cur *2;
            if(dx3 >=0 && dx3<=100000) {
                if(v_time[dx3] > v_time[cur]){
                    v_time[dx3] = v_time[cur];
                    queue.offer(dx3);
                }
            }

        }
    }

    static class Point{
        int p, time;
        public Point(int p, int time){
            this.p = p;
            this.time = time;
        }
    }
 }