import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static Edge[] edgeList;

    static int[] parents;

    // 크기가 1인 서로소 집합 생성
    static void make(){
        parents = new int[N+1];
        for(int i=1; i<=N; i++){
            parents[i] = i;
        }
    }

    // a의 대표자 찾기
    static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        M = Integer.parseInt(input.readLine());

        edgeList = new Edge[M];

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

            edgeList[i] = new Edge(from, to, weight);
        }
            make();
            Arrays.sort(edgeList);

        int result = 0;
        int count = 0;

        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                result += edge.weight;
                if(++count == N-1) break;
            }
        }
        System.out.println(result);

    }


}
