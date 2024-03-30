import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        String str = input.readLine();
        int N = Integer.parseInt(input.readLine());
        Set<String> set = new HashSet<>();
        for(int i=0; i<N; i++){
            String s = input.readLine();
            set.add(s);
        }

        int[] dp = new int[str.length()+1];
        for(int i=str.length()-1; i>=0; i--){
            for(int j=i+1; j<str.length(); j++){
                if(dp[j] == 1) {
                    String tmp = str.substring(i, j);
                    if(set.contains(tmp)) {
                        dp[i] = 1;
                    }
                }
            }

            if(set.contains(str.substring(i,str.length()))) {
              dp[i] = 1;
            }
        }

        System.out.println(dp[0] == 1 ? 1 : 0);

    }
}