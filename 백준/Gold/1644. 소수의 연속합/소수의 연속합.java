import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        // 소수 거르기
        boolean[] not_prime = new boolean[N+1];
        not_prime[0] = not_prime[1] = true;
        for(int i=2; i*i<=N; i++){
            if(!not_prime[i]) {
                for(int j=i*i; j<=N; j+=i){
                    not_prime[j] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<N+1; i++){
            if(!not_prime[i]) {
                list.add(i);
                if(N==i) ans++;
            }
        }

        int size = list.size();

        // 소수합 으로 N 나타내기
        if(N > 2){

            int left = 0;
            int right = 1;
            int sum = list.get(left) + list.get(right);
            while(left < size && right < size-1){

                if(sum == N){
                    ans++;
                    right++;
                    sum += list.get(right);
                }else if(sum > N){
                    sum -= list.get(left);
                    left++;
                }else{
                    right++;
                    sum += list.get(right);
                }
            }
        }

        System.out.println(ans);

    }
}