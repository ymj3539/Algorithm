import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[] parents;
    static int ans;
    public static void main(String[] args)throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parents = new int[N+1];

        for(int i=1; i<=N; i++){
            parents[i] = i;
        }

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());

            if(find(u) == find(v)){
                // 사이클 발생 하므로 사이클 끊기 -> 연산 횟수 증가
                ans++;
            }else {
                // union 연결
                union(u, v);
            }
        }

        // union 연결 후 총 집단의 갯수 세기 -> 각 집단을 연결해야하니까
        Set<Integer> set = new HashSet<>();
        for(int i=1; i<=N; i++){
            set.add(find(parents[i]));
        }

        ans += (set.size()-1);


        System.out.println(ans);

    }

    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);

        if(X>Y){
            parents[X] = Y;
        }else{
            parents[Y] = X;
        }


    }
}