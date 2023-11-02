import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, D;
    public static void main(String[] args)throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        D = Integer.parseInt(tokens.nextToken());

        int[] dp = new int[D+1];
        List<Path>[] paths = new List[10000+1];
        for(int i=0; i<10001; i++){
            paths[i] = new ArrayList<>();
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int len = Integer.parseInt(tokens.nextToken());

            paths[end].add(new Path(start, end, len));


        }

        dp[0] = 0;
        for(int i=1; i<=D; i++){
            if(paths[i].size()>0){
                for(Path p : paths[i]){
                    // 한번도 갱신된 적 없으면
                    if(dp[i] == Integer.MAX_VALUE){
                        dp[i] = Math.min(dp[i-1]+1, dp[p.start] +  p.len);
                    }
                    // 갱신된 적 있으면
                    else dp[i] = Math.min(dp[i], dp[p.start] +  p.len);
                }
            }
            else {
                dp[i] = dp[i-1] + 1;
            }
        }

        System.out.println(dp[D]);
    }

    static class Path{
        int start, end, len;

        public Path(int s, int e, int l){
            this.start = s;
            this.end = e;
            this.len = l;
        }
    }
}