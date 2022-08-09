package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_25239 {
	static int hh;
	static int mm;
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		String str = input.readLine();
		String[] strArr = str.split(":");
		hh = Integer.parseInt(strArr[0]);
		mm = Integer.parseInt(strArr[1]);
		int currentArea = AreaNum(hh);	// ���� ��ħ�� ��ġ�� ������ ��ȣ
		
		int[] area = new int[7];
		tokens = new StringTokenizer(input.readLine());
		for(int i=1; i<7; i++) {
			area[i] = Integer.parseInt(tokens.nextToken());
		}
		
		int event = Integer.parseInt(input.readLine());
		
		double timeSum=0;
		
		// �̺�Ʈ ����
		for(int i=0; i<event; i++) {
			tokens = new StringTokenizer(input.readLine());
			double time = Double.parseDouble(tokens.nextToken());
			String eventDetail = tokens.nextToken();
			
			timeSum += time;
			
			if(timeSum > 60) {
				break;
			}
			if(eventDetail.equals("^")) {
				area[currentArea] = 0;
				int answer =0;
				for(int j : area) {
					answer += j;
				}
				if(answer == 0) {
					break;
				}
			}else {
				int hour = strToTime(eventDetail);
				currentArea = AreaNum(hour);
			}
		}
		
		// ���� �ۼ�Ʈ ���ؼ� ���
		int answer =0;
		for(int i : area) {
			answer += i;
		}
//		System.out.println(Arrays.toString(area));
		if(answer >100) {
			answer = 100;
		}
		System.out.print(answer);
	}
	
	// �־��� �̺�Ʈ �ð� ���ںκа� ���ںκ� �и� �� ����ð��� ���ϱ�
	static int strToTime(String str) {
		char c;
		int tmp =0 ;
		for(int i=0; i<str.length(); i++) {
			c = str.charAt(i);
			if(c>='0' && c<='9') {
				continue;
			}else {
				tmp = i;
				break;
			}
		}
		int time = Integer.parseInt(str.substring(0, tmp));
		String timeType = str.substring(tmp);
		if(timeType.equals("HOUR")) {
			hh += time;
		}else {
			mm+=time;
			if(mm >=60) {
				mm %= 60;
				hh++;
			}
			
		}
		hh %= 12;
		return hh;
		
	}
	
	// �ð��� �ش��ϴ� ���� ��ȣ ��ȯ
	static int AreaNum (int hour) {
		int area = 0;
		switch(hour) {
		case 0:
		case 1:
			area = 1;
			break;
		case 2:
		case 3:
			area =2;
			break;
		case 4:
		case 5: 
			area = 3;
			break;
		case 6:
		case 7: 
			area = 4;
			break;
		case 8:
		case 9: 
			area = 5;
			break;
		case 10:
		case 11: 
			area = 6;
			break;
		}
		return area;
	}
}
