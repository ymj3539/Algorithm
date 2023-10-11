import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N;
	static int M;
	static List<Point> home = new LinkedList<Point>();
	static List<Point> store = new LinkedList<Point>();
	static int cityMin = Integer.MAX_VALUE;;
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		int tmp;
		
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<N; c++) {
				tmp = Integer.parseInt(tokens.nextToken());
				if(tmp == 1) {
					home.add(new Point(r,c));
				}else if(tmp == 2) store.add(new Point(r,c));
			}
		}
		////  입력끝
		makeCombination(0, new Point[M], 0);
		System.out.println(cityMin);
		
	}
	
	static void makeCombination(int nth, Point[] choosed, int startIdx) {
		if(nth == choosed.length) {
			cityDistance(choosed);
			return;
		}
		
		for(int i=startIdx; i<store.size(); i++) {
			choosed[nth] = store.get(i);
			makeCombination(nth+1, choosed, i+1);
		}
	}
	
	static void cityDistance(Point[] choosed) {
		int sum=0;
		for(Point h : home) {
			int min = Integer.MAX_VALUE;
			for(Point s : choosed) {
				min = Math.min(min, h.getDistance(s));
			}
			sum += min;
		}
		cityMin = Math.min(cityMin, sum);
	}
	
	// 배열의 사이즈를 미리 알수 없기 때문에 r과 c의 값을 저장하기 위해 class 구현
	public static class Point {
		int r;
		int c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		int getDistance(Point other) {
			return Math.abs(this.r-other.r)+Math.abs(this.c-other.c);
			
		}
	}
	
}
