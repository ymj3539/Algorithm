import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parents = new int[N+1];

        for(int i=1; i<=N; i++){
            parents[i] = i;
        }

        int cnt = 0;

        for(int i=1; i<=M; i++){
            tokens = new StringTokenizer(input.readLine());

            int num1 = Integer.parseInt(tokens.nextToken());
            int num2 = Integer.parseInt(tokens.nextToken());

            if(find(num1) == find(num2)) {
                cnt = i;
                break;
            }else {
                union(num1, num2);
            }
        }

        System.out.println(cnt);
    }

    static int find(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);

        if(x < y){
            parents[Y] = X;
        }else {
            parents[X] = Y;
        }
    }

}