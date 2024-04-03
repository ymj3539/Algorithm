import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[] trues;
    static int[] parents;
    static boolean[] knowTrue;
    static List<Integer>[] parties;
    public static void main (String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        trues = new int[n];
        knowTrue = new boolean[N+1];
        for(int i=0; i<n; i++){
            trues[i] = Integer.parseInt(tokens.nextToken());
            knowTrue[trues[i]] = true;
        }

        parents = new int[N+1];
        parties = new List[M+1];
        for(int i=1; i<=N; i++){
            parents[i] = i;
        }

        for(int i=0; i<M; i++){
            parties[i] = new ArrayList<>();
            tokens = new StringTokenizer(input.readLine());
            int num = Integer.parseInt(tokens.nextToken());
            int a = Integer.parseInt(tokens.nextToken());
            parties[i].add(a);
            for(int j=1; j<num; j++){
                int b = Integer.parseInt(tokens.nextToken());
                parties[i].add(b);
                if(find(a) != find(b)){
                    union(a, b);
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<M; i++){
            int size = parties[i].size();
            boolean canLie = true;
            for(int j=0; j<size; j++){
                if(knowTrue[find(parties[i].get(j))]) {
                    canLie = false;
                    break;
                }
            }
            if(canLie) cnt++;
        }
        System.out.println(cnt);

    }

    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);
        if(knowTrue[X]) knowTrue[Y] = true;
        else if(knowTrue[Y]) knowTrue[X] = true;

        if(X > Y){
            parents[Y] = X;
        }else parents[X] = Y;
    }
}