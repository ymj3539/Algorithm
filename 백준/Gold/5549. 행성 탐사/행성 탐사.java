import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static char[][] map;
    static int K;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        map = new char[M][N];

        int[][] jungle = new int[M+1][N+1];
        int[][] ocean = new int[M+1][N+1];
        int[][] ice = new int[M+1][N+1];

        K = Integer.parseInt(input.readLine());

        for(int r =1; r<=M; r++){
            map[r-1] = input.readLine().toCharArray();
            for(int c=1; c<=N; c++){
                if(map[r-1][c-1] == 'J') jungle[r][c] = jungle[r][c-1] + 1;
                else jungle[r][c] = jungle[r][c-1];

                if(map[r-1][c-1] == 'O') ocean[r][c] = ocean[r][c-1] + 1;
                else ocean[r][c] = ocean[r][c-1];

                if(map[r-1][c-1] == 'I') ice[r][c] = ice[r][c-1] + 1;
                else ice[r][c] = ice[r][c-1];
            }


        }

        for(int c =1; c<=N; c++){
            for(int r=1; r<=M; r++){
                jungle[r][c] += jungle[r-1][c];
                ocean[r][c] += ocean[r-1][c];
                ice[r][c] += ice[r-1][c];

            }

        }



        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());
            int j_sum = jungle[c][d] - jungle[a-1][d] - jungle[c][b-1] + jungle[a-1][b-1];
            int o_sum = ocean[c][d] - ocean[a-1][d] - ocean[c][b-1] + ocean[a-1][b-1];
            int i_sum = ice[c][d] - ice[a-1][d] - ice[c][b-1] + ice[a-1][b-1];

            sb.append(j_sum+" "+o_sum+" "+i_sum+"\n");

        }

        System.out.println(sb);

    }

}