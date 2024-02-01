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
		dfs(num, 0);
		
		System.out.println(min+" "+max);
	}
	
	static void dfs(int num, int total_odd_cnt) {
		// 각 자리 숫자 중 홀수 개수 구하기
		int odd_cnt = count_odd(num);
		total_odd_cnt += odd_cnt;
		
		
		// 수가 한자리일때
		if(num < 10) {
			// 최소값 최대값 비교
			max = Math.max(max, total_odd_cnt);
			min = Math.min(min, total_odd_cnt);
			
			return;
		} 
		
		// 수가 두자리일때
		else if(num <100) {
			int n1 = num / 10;
			int n2 = num % 10;
			
			int tmp = n1 + n2;
			dfs(tmp, total_odd_cnt);
			
		} 
		
		// 수가 세자리 이상일때 -> 3개의 수로 분할
		else {
			int size = String.valueOf(num).length();
			// 분할
			for(int i=1; i<=size-2; i++) {
				for(int j=i+1; j<=size-1; j++) {
					int cut1 = i;
					int cut2 = j;
					
					int n1 =0;
					int n2 =0; 
					int n3 = 0;
					
					String num_str = String.valueOf(num);
					n1 = Integer.parseInt(num_str.substring(0, cut1));
					n2 = Integer.parseInt(num_str.substring(cut1, cut2));
					n3 = Integer.parseInt(num_str.substring(cut2, num_str.length()));
					
					int tmp = n1+n2+n3;
					
					dfs(tmp, total_odd_cnt);
				}
			}
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