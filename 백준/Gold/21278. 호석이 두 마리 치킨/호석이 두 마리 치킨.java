import java.util.*;
import java.io.*;
public class Main {
    /*
    * N개의 건물 (1~N번)
    * M개의 도로 - i번째 도로 : 서로 다른 두 건물 사이를 1시간에 양방향 이동할 수 있는 도로
    * 키친 도시 ~ 2개 골라서 치킨집 열어
    * */
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] cost;
    static int total_min = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        cost = new int[N+1][N+1];

        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                cost[r][c] = 100000;
                if(r==c) cost[r][c] = 0;
            }
        }


        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());

            cost[A][B] = 1;
            cost[B][A] = 1;
        }

        // 플로이드 워셜 알고리즘
        // 도시를 1개부터 N개까지 거쳐가는 경우를 모두 고려
        for(int i=1; i<=N; i++){
            // 노드 a에서 b로 가는 경우
            for(int a=1; a<=N; a++){
                for(int b=1; b<=N; b++){
                    // i번째 도시를 거쳐가는 경로와 기존 경로를 비교
                    cost[a][b] = Math.min(cost[a][b], cost[a][i] + cost[i][b]);
                }
            }
        }

        // 조합
        comb(0, new int[2], 1);
        System.out.println(sb);

    }

    static void comb(int nth, int[] choosed, int idx){
        if(nth == 2){
            int sum = 0;
            for(int i=1; i<=N; i++){
                int min = Integer.MAX_VALUE;
                for(int j=0; j<2; j++){
                    min = Math.min(cost[i][choosed[j]], min);
                }

                sum += (min * 2);
            }

            if(sum < total_min){
                total_min = sum;
                sb = new StringBuilder();
                sb.append(choosed[0]+" "+choosed[1]+" "+sum);
            }
            return;
        }

        for(int i = idx; i<=N; i++){
            choosed[nth] = i;
            comb(nth+1, choosed, i);
        }
    }
}