import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        while(true){
            tokens = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());

            if(A == 0 && B == 0) break;

            // 상근이의 사용량 찾는 이분탐색
            int totalUse = priceToUse(A);
            int left = 0;
            int right = totalUse/2;
            int mid;
            while(left <= right){
                // mid : 상근이의 사용량
                mid = (left + right)/2;
                // 상근이의 요금
                int sUse = UseToPrice(mid);
                // 이웃의 요금
                int nUse = UseToPrice(totalUse - mid);

                // tmp : 상근요금 - 이웃요금의 절대값
                int tmp = Math.abs(sUse - nUse);

                if(tmp > B){
                    // 상근 사용량 증가
                    left = mid + 1;
                }else {
                    // 상근 사용량 감소
                    right = mid - 1;
                }
            }

            sb.append(UseToPrice(left)+"\n");

        }
        System.out.println(sb);
    }

    // 요금 -> 사용량 변환 메소드
    static int priceToUse(int price){
        if(price <= 200){
            return price/2;
        }
        else {
            price -= 200;
            if(price <= 29700){
                return price/3 + 100;
            }
            else {
                price -= 29700;
                if(price <= 4950000){
                    return price/5 + 10000;
                }
                else {
                    price -= 4950000;
                    return price/7 + 1000000;
                }
            }
        }
    }

    static int UseToPrice(int use){
        if(use <= 100){
            return use * 2;
        }
        else if(use <= 10000){
            return 100 * 2 + (use - 100) * 3;
        }
        else if(use <= 1000000){
            return 100 * 2 + (10000 - 100) * 3 + (use -10000) * 5;
        }
        else return 100 * 2 + (10000-100) * 3 + (1000000 - 10000) * 5 + (use - 1000000) * 7;
    }
}