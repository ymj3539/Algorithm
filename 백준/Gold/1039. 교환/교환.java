import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static String N;
    static int M, K;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = tokens.nextToken();
        K = Integer.parseInt(tokens.nextToken());

        M = N.length();

        bfs();
        System.out.println(max);
    }

    static void bfs(){
        Queue<Num> queue = new LinkedList();
        queue.offer(new Num(0, Integer.parseInt(N)));
        boolean[][] visited = new boolean[K+1][1000001];
        visited[0][Integer.parseInt(N)] =true;

        max = -1;

        while(!queue.isEmpty()){
            Num cur = queue.poll();

            if(cur.depth == K){
                max = Math.max(max, cur.num);
                continue;
            }

            for(int i=0; i<M; i++){
                for(int j=0; j<M; j++){
                    if(i==j) continue;

                    int swap_result = swap(cur.num, i, j);
                    if(swap_result == - 1) continue;
                    if(visited[cur.depth+1][swap_result]) continue;
                    visited[cur.depth+1][swap_result] = true;
                    queue.offer(new Num(cur.depth+1, swap_result));
                }
            }
        }

    }

    static int swap(int num, int i, int j){
        char[] arr = String.valueOf(num).toCharArray();
//        System.out.println(Arrays.toString(arr));
        char tmp = arr[i];
        arr[i] = arr[j] ;
        arr[j] = tmp;
        if(arr[0] == '0') return -1;
        return Integer.parseInt(String.valueOf(arr));
    }

    static class Num{
        int depth, num;
        public Num (int depth, int num){
            this.depth = depth;
            this.num = num;
        }
    }
}