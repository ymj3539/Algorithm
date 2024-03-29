import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int M, N, K;
    static int[][] Jmap;
    static int[][] Omap;
    static int[][] Imap;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(input.readLine());

        Jmap = new int[M+1][N+1];
        Omap = new int[M+1][N+1];
        Imap = new int[M+1][N+1];

        for(int r=1; r<=M; r++){
            String str = input.readLine();
            for(int c=1; c<=N; c++){
                char ch = str.charAt(c-1);
                if(ch=='J') Jmap[r][c] = 1;
                else if(ch =='O') Omap[r][c] = 1;
                else Imap[r][c] = 1;
            }
        }

        // 누적합 구하기 (세로 방향)
        for(int r=1; r<=M; r++){
            for(int c=1; c<N; c++){
                Jmap[r][c+1] += Jmap[r][c];
                Omap[r][c+1] += Omap[r][c];
                Imap[r][c+1] += Imap[r][c];
            }
        }

        // 누적합 구하기 (가로 방향)
        for(int c=1; c<=N; c++){
            for(int r=1; r<M; r++){
                Jmap[r+1][c] += Jmap[r][c];
                Omap[r+1][c] += Omap[r][c];
                Imap[r+1][c] += Imap[r][c];
            }
        }

        for(int k=0; k<K; k++){
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());
            func(a, b, c, d);
        }

        System.out.println(sb);

    }
    static void func(int a, int b, int c, int d){
        // 정글 계산
        int j = Jmap[c][d] - Jmap[a-1][d] - Jmap[c][b-1] + Jmap[a-1][b-1];
        int o = Omap[c][d] - Omap[a-1][d] - Omap[c][b-1] + Omap[a-1][b-1];
        int i = Imap[c][d] - Imap[a-1][d] - Imap[c][b-1] + Imap[a-1][b-1];

        sb.append(j+" "+o+" "+i+"\n");
    }
}