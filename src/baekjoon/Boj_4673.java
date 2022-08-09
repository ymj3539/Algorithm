package baekjoon;

public class Boj_4673 {
	
	public static void main(String[] args) {
		
		boolean answer[] = new boolean[10001];
		
		for(int i=1; i< 10001; i++) {
			if(isSelf(i)<10001) answer[isSelf(i)] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<10001; i++) {
			if(answer[i] == false) sb.append(i+"\n");
		}
		
		System.out.print(sb);
	}
	
	static int isSelf(int n) {
		int sum = n;
		while(n > 0) {
			sum += n%10;
			n /= 10;
		}
		return sum;
	}
}
