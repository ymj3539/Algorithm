import java.util.*;
import java.io.*;


public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(input.readLine());
		
		int num = N;
		
		calculate(num, 0);
		
		System.out.println(min+" "+max);
	}
	
	static void calculate(int num, int total) {
		// 각 자리 숫자 중 홀수 개수 구하기
		int odd_cnt = count_odd(num);
		total += odd_cnt;
		
		
		// 수가 한자리일때
		if(num < 10) {
			// 최소값 최대값 비교
			max = Math.max(max, total);
			min = Math.min(min, total);
			return;
		} 
		
		// 수가 두자리일때
		else if(num <100) {
			int n1 = num / 10;
			int n2 = num % 10;
			
			num = n1 + n2;
			calculate(num, total);
		} 
		
		// 수가 세자리 이상일때 -> 3개의 수로 분할
		else {
			// 분할
			comb(0, new int[2], 1, total, num);
		}
		
	}
	
	static void comb(int nth, int[] choosed, int start_idx, int total, int num) {
		if(nth == choosed.length) {
			// 분할된 수 더하기
			int cut1 = choosed[0];
			int cut2 = choosed[1];
			
			int n1 =0;
			int n2 =0; 
			int n3 = 0;
			
			String num_str = String.valueOf(num);
			n1 = Integer.parseInt(num_str.substring(0, cut1));
			n2 = Integer.parseInt(num_str.substring(cut1, cut2));
			n3 = Integer.parseInt(num_str.substring(cut2, num_str.length()));
			
			int tmp = n1+n2+n3;
			
			calculate(tmp, total);
			return;
		}
		
		int size = String.valueOf(num).length();
		for(int i=start_idx; i<size; i++) {
			choosed[nth] = i;
			comb(nth+1, choosed, i+1, total, num);
		}
	}
	
	
	static int count_odd(int num){
		int cnt = 0;
		
		while(num > 0) {
			int n = num % 10;
			num /= 10;
			if(n%2 != 0) cnt++;
		}
		
		return cnt;
	}
}