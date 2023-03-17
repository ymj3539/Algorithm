import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[3];
		int money = 0;
		int max = 0;
		for(int i = 0; i<3; i++) {
			num[i] = sc.nextInt();
			if(num[i] > max) max = num[i];
		}
		sc.close();
		
		if(num[0]==num[1] && num[1]==num[2]) {
			money = 10000 + num[0]*1000;
		}else if(num[0]==num[1] || num[1]==num[2]) {
			money = 1000 + num[1]*100;
		}else if(num[0]==num[2]) {
			money = 1000 + num[0]*100;
		}else money = max*100;
		
		System.out.println(money);
	}
}
