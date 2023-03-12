import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	

    public int[] solution(String today, String[] terms, String[] privacies) {
       
        List<Integer> list = new ArrayList<>();
        
        // 오늘 날짜 배열로 바꾸기
        String[] todayArr = today.split("\\.");
        
        // terms 배열 hashmap으로 바꾸기
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++) {
        	String str = terms[i];
        	String[] tmp = str.split(" ");
        	map.put(tmp[0], Integer.parseInt(tmp[1]));
        	
        }
        
        // 개인정보 수집 일자 하나씩 확인
        for(int i=0; i<privacies.length; i++) {
        	String str = privacies[i];
        	// 날짜랑 달 분리
        	String[] tmp1 = str.split(" ");
        	String date = tmp1[0];
        	String type = tmp1[1];	// 약관 타입
        	
        	// 가입날짜 배열 처리
        	String[] tmp2 = date.split("\\.");
        	int[] dateArr = new int[3];
        	for(int j=0; j<3; j++) {
        		dateArr[j] = Integer.parseInt(tmp2[j]);
        	}
        	
        	// 가입 날짜 + 약관 달
        	dateArr[1] += map.get(type);
        	
        	// 날짜 정리
        	if(dateArr[1] > 12) {
        		if(dateArr[1]%12 ==0) {
        			dateArr[0] += (dateArr[1]/12 -1);
        			dateArr[1] = 12;
        		}else {
        			dateArr[0] += (dateArr[1]/12);
            		dateArr[1] %= 12;
        		}
        	}
        	
        	// 오늘 날짜와 비교
        	// 년도 비교
        	if(dateArr[0] < Integer.parseInt(todayArr[0])) {
        		list.add(i+1);
        		continue;
        	}else if(dateArr[0] > Integer.parseInt(todayArr[0])) {
        		continue;
        	}
        	// 월 비교
        	else {
        		if(dateArr[1] < Integer.parseInt(todayArr[1])) {
        			list.add(i+1);
        			continue;
        		}else if(dateArr[1] > Integer.parseInt(todayArr[1])) {
            		continue;
        		}
        		// 일 비교
        		else {
        			if(dateArr[2] <= Integer.parseInt(todayArr[2])) {
            			list.add(i+1);
            			continue;
            		}else if(dateArr[2] > Integer.parseInt(todayArr[2])) {
                		continue;
            		}
        		}
        	}
        }
        

        int[] answer = new int[list.size()];
        for(int i=0; i < list.size(); i++) {
        	answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    
}
