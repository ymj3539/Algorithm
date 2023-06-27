import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, S;
	static int[] arr;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N];
		tokens = new StringTokenizer(input.readLine());
		
		int size = arr.length;
		for(int i=0; i<size; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
//		System.out.println(Arrays.toString(arr));
		
		int sum = 0;	// 총합
		int cnt = 0;	// 길이
		int end = 0;	// 맨 끝값 
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=0; i<size; i++) {
			// 총합보다 작니?
			if(sum < S) {
				// 총합, 길이 더하기
				sum += arr[i];
				cnt++;
				queue.offer(arr[i]);
				
			}
			
			// 총합 이상이니?
			while(sum >= S) {
				// 최소 길이 비
				min = Math.min(min, cnt);
				
				// 뒤에꺼 빼기
				end = queue.poll();
				sum -= end;
				cnt--;
			}
			
		}
		
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}

}