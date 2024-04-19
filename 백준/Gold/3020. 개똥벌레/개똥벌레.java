import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, H;

    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        int[] bottom = new int[N/2];
        int[] top = new int[N/2];

        for(int i=0; i<N/2; i++){
            bottom[i] = Integer.parseInt(input.readLine());
            top[i] = Integer.parseInt(input.readLine());
        }

        // 이진 탐색을 위한 정렬
        Arrays.sort(bottom);
        Arrays.sort(top);

        int min = Integer.MAX_VALUE;    // 장애물의 최솟값
        int ans_cnt = 0;    // 구간의 수

        // 각 높이마다 탐색
        for(int r=1; r<=H; r++){
            int tmp_cnt = 0; // 해당 높이에서 파괴할 수 있는 장애물 갯수
            // 이진 탐색
            tmp_cnt += count(top, r);
            tmp_cnt += count(bottom, H+1-r);

            // 최솟값 비교
            // 최솟값보다 작으면 -> 최솟값 갱신, 구간의 수 갱신
            if(min > tmp_cnt){
                min = tmp_cnt;
                ans_cnt = 1;
            }
            // 최솟값과 같으면 구간의 수 증가
            else if(min == tmp_cnt){
                ans_cnt++;
            }
        }

        System.out.println(min+" "+ans_cnt);
    }

    static int count(int[] arr, int h){
        // right : 파괴되는 첫번째 장애물의 index
        // 이 뒤의 index에 해당되는 종유석(석순)은 다 파괴 가능
        int left = 0;
        int right = N/2;
        int mid;

        while(left < right){
            mid = (left + right)/2;
            if(arr[mid] >= h){
                right = mid;
            }else if(arr[mid] < h){
                left = mid+1;
            }
        }


        // 우리는 갯수를 알고 싶으니까 전체 갯수(N/2) - right로 구함
        return N/2 - right;
    }
}