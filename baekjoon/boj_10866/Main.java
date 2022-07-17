package boj_10866;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Deque<String> deque = new ArrayDeque<String>();
		
		int N = Integer.parseInt(br.readLine());
		String arr[] = new String[2];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			arr = str.split(" ");
			
			switch (arr[0]) {
			
			case "push_front" :
				deque.addFirst(arr[1]);
				break;
			case "push_back" :
				deque.addLast(arr[1]);
				break;
			case "pop_front" :
				if(deque.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else {
					bw.write(String.valueOf(deque.pollFirst())+'\n');
				}
				break;
			case "pop_back" :
				if(deque.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else {
					bw.write(String.valueOf(deque.pollLast())+'\n');
				}
				break;
			case "size" :
				bw.write(String.valueOf(deque.size())+'\n');
				break;
			case "empty" :
				if(deque.isEmpty()) bw.write(String.valueOf(1)+'\n');
				else bw.write(String.valueOf(0)+'\n');
				break;
			case "front" :
				if(deque.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else {
					bw.write(String.valueOf(deque.peekFirst())+'\n');
				}
				break;
			case "back" :
				if(deque.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else {
					bw.write(String.valueOf(deque.peekLast())+'\n');
				}
				break;
			}
			
		}
		bw.flush();	
	}

}
