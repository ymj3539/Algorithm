import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, L;
    static int[] places;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());

        places = new int[N];
        if(N!=0){
            tokens = new StringTokenizer(input.readLine());
            for(int i=0; i<N; i++){
                places[i] = Integer.parseInt(tokens.nextToken());
            }
        }

        Arrays.sort(places);

        // 간격 계산
        // 간격 배열에 담기
        int[] arr = new int[N+1];
        if(N!=0){
            for(int i=0; i<=N; i++){
                if(i == 0){
                    arr[i] = places[i];
                }
                else if(i==N){
                    arr[i] = (L - places[i-1]);
                }
                else {
                    arr[i] = (places[i] - places[i-1]);
                }
            }
        }else{
            arr[0] = L;
        }

        // 이분탐색으로 조건 만족하는 최소값 찾기
        int left = 1;
        int right = L;
        while(left<=right){
            int mid = (left+right)/2;

            int cnt = 0;
            for(int num : arr){
                cnt += ((num -1)/mid);
            }
            if (cnt > M) {
                left = mid +1;

            }else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }
}