import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, L;
    static int[] cutpoints;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());   // 자르는 횟수
        M = Integer.parseInt(tokens.nextToken());   // 자를 수 있는 지점의 갯수
        L = Integer.parseInt(tokens.nextToken());   // 롤케이크 길이

        cutpoints = new int[M+1];
        for(int i=0; i<M; i++){
            cutpoints[i] = Integer.parseInt(input.readLine());
        }
        cutpoints[M] = L;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            int cnt = Integer.parseInt(input.readLine());
            sb.append(binarySearch(cnt)+"\n");
        }
        System.out.println(sb);

    }

    static int binarySearch(int totalCnt){
        int left = 0;
        int right = L;
        int mid = 0;
        int max = 0;

        while (left<= right){
            mid = (left + right)/2;
            int cnt = 0;
            int preCutPoint = 0;
            // mid가 가장 작은 조각일 때, 몇개로 자를 수 있는지
            for(int i=0; i<=M; i++){
                if(cutpoints[i] - preCutPoint >= mid){
                    // 해당 지점에서 자를 수 있다는 뜻!
                    cnt++;
                    preCutPoint = cutpoints[i];
                }
            }

            // 주어진 횟수만큼 잘랐는지 확인 후 mid 값 조정
            if(cnt > totalCnt){
                left = mid + 1;
                max = Math.max(max, mid);
            }else {
                right = mid - 1;
            }
        }
        return max;
    }
}