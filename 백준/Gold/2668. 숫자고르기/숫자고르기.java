import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] choosed;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        choosed = new int[N+1];
        for(int i=1; i<N+1; i++){
            choosed[i] = Integer.parseInt(input.readLine());
        }


        for(int i=1; i<=N; i++){
            dfs(i, i, new boolean[N+1]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i : list){
            sb.append(i+"\n");
        }

        System.out.println(list.size()+"\n"+ sb);
    }

    static void dfs(int start, int index, boolean[] visited){
        if(choosed[index] == start)  {
            list.add(start);
            return;
        }

        if(!visited[choosed[index]]){
            visited[choosed[index]] = true;
            dfs(start, choosed[index], visited);
            visited[choosed[index]] = false;
        }

    }

}