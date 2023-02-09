import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String he = input.readLine();
		String she = input.readLine();
		
		int N = he.length(); // 문자열의 길이
		
		int[] strokeArr = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
		
		
		Integer[] arr = new Integer[N*2];
		
		// 문자열 합치기 + 획수로 변환
		for(int i=0; i<N; i++) {
			arr[2*i] = strokeArr[he.charAt(i)-'A'];
			arr[(2*i)+1] = strokeArr[she.charAt(i)-'A'];
			
		}
		
		List<Integer> list = Arrays.asList(arr);
		
		// 두자리의 숫자가 될때까지 반복
		while(list.size() > 2) {
			List<Integer> tmp = new ArrayList<>();
			for(int i=0; i<list.size()-1; i++) {
				int num1 = list.get(i);
				int num2 = list.get(i+1);
				
				// 두 수 계산
				tmp.add((num1 + num2)%10);
			}
			list = tmp;
		}
		
		
		System.out.println(list.get(0)+""+list.get(1));
	}
}