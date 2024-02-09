import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t=1; t<=T; t++){
            String W = input.readLine();
            int K = Integer.parseInt(input.readLine());

//            if(K==1) {
//                sb.append(1+" "+1);
//                continue;
//            }

            int MAX = Integer.MIN_VALUE;
            int MIN = Integer.MAX_VALUE;

            int[] alphabets = new int[26];

            // 주어진 문자열에서 각 문자가 몇개 존재하는지 카운트
            for(int i=0; i<W.length(); i++){
                alphabets[W.charAt(i) - 'a']++;
            }

            for(int i=0; i<W.length(); i++){
                int cnt = 0;
                char start_l = W.charAt(i);
                if(alphabets[start_l - 'a'] < K) continue;

                for(int j=i; j<W.length(); j++){
                    if(W.charAt(j) == start_l){
                        cnt++;

                        if(cnt == K){
                            MAX = Math.max(MAX, j-i+1);
                            MIN = Math.min(MIN, j-i+1);
                            break;
                        }
                    }
                }
            }
            if(MAX == Integer.MIN_VALUE || MIN == Integer.MAX_VALUE){
                sb.append(-1+"\n");
            } else{
                sb.append(MIN+" "+MAX+"\n");
            }

        }

        System.out.println(sb);
    }

}