import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, T;
    static Camp[] camps;
    static int[] parents;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(input.readLine());

        for(int t =0; t<T; t++){
            N = Integer.parseInt(input.readLine());

            camps = new Camp[N];

            parents = new int[N];

            for(int i=0; i<N; i++){
                tokens = new StringTokenizer(input.readLine());
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                int r = Integer.parseInt(tokens.nextToken());

                camps[i] = new Camp(x, y, r);
                parents[i] = i;
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(i==j) continue;
                    
                    if(find(i) == find(j)) continue;

                    // 다르면 진영 범위 체크
                    if(isConnect(camps[i], camps[j])){
                        // 범위 안에 들어오면 합치기
                        union(i, j);
                    }


                }
            }

            Set<Integer> set = new HashSet<>();
            
            for(int i=0; i< N; i++){
                set.add(find(parents[i]));
            }

            sb.append(set.size()+"\n");


        }
        System.out.println(sb);

    }

    static int find(int i){
        if(parents[i] == i){
            return i;
        }
        else return parents[i] = find(parents[i]);
    }

    static void union(int i, int j){
        int I = find(i);
        int J = find(j);
        // 대표자의 부모를 바꿔줘야돼
        parents[J] = I;
    }

    static boolean isConnect(Camp camp1, Camp camp2){
        // 두 진영 사이의 거리
        int dist = (int)(Math.pow(camp1.x - camp2.x, 2) + Math.pow(camp1.y - camp2.y, 2));
        int r = (int)Math.pow(camp1.r + camp2.r,2);
        if(dist <= r){
            return true;
        }else return false;
    }

    static class Camp{
        int x, y, r;
        public Camp(int x, int y, int r){
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

}