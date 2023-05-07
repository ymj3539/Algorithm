import java.util.Scanner;

public class Main {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		makePrime(0, 0);
		
		
	}
	// 소수 만들기
	 static void makePrime(int nth, int result) {
		 if(nth == N) {
			 System.out.println(result);
			 return;
		 }
		 
		 for(int i=1; i<=9; i++) {
			 int tmp = result*10+i;
			 if(isPrime(tmp)) {
				 makePrime(nth+1,tmp);
			 }
		 }
	 }
	
	// 소수 판별
	static boolean isPrime(int n) {
		if(n == 1) {
			return false;
		}
		for(int i=2; i<=(int)Math.sqrt(n); i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}
}
