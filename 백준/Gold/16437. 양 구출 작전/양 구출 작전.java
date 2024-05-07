import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static Animal[] animals;
    static List<Integer>[] list;
    static long total;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        animals = new Animal[N+1];
        list = new List[N+1];

        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        animals[1] = new Animal(null, 0, 0);

        for(int i=2; i<N+1; i++){
            tokens = new StringTokenizer(input.readLine());
            String type = tokens.nextToken();
            int cnt = Integer.parseInt(tokens.nextToken());
            int parents = Integer.parseInt(tokens.nextToken());

            animals[i] = new Animal(type, cnt, parents);
            list[parents].add(i);
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        total = dfs(1);

        System.out.println(total);
    }

    static long dfs(int cur){
        long sum = 0;
        for(int next : list[cur]){
            sum += dfs(next);
        }

        if(cur == 1) return sum;
        if(animals[cur].type.equals("S")) return sum + animals[cur].cnt;
        else return sum < animals[cur].cnt ? 0 : sum - animals[cur].cnt;

    }

    static class Animal{
        String type;
        int cnt;
        int parents;
        public Animal(String type, int cnt, int parents){
            this.type = type;
            this.cnt = cnt;
            this.parents = parents;
        }

    }

}