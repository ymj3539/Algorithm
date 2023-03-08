
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public String[] solution(String[] record) {
        String[] answer = {};
        int l = record.length;
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for(int i=0; i<l; i++) {
        	String[] arr = record[i].split(" ");
        	if(arr.length == 3) {
        		map.put(arr[1], arr[2]);
            	if(arr[0].equals("Enter")) {
            		list.add(arr[1]+" 님이 들어왔습니다.");
		    	}else if(arr[0].equals("Change")) {
		    		map.replace(arr[1], arr[2]);
		    	}
        	}else {
        		if(arr[0].equals("Leave")) {
            		list.add(arr[1]+" 님이 나갔습니다.");
        		}
        	}
        	
        }
        
        List<String> list2 = new ArrayList<>();
        
        for(int i=0; i<list.size(); i++) {
        	String str = list.get(i);
        	String[] tmp = str.split(" ");
        	String nickname = map.get(tmp[0]);
        	String text = nickname+tmp[1]+" "+tmp[2];
        	list2.add(text);
        			
        }
        
        answer = list2.toArray(new String[list2.size()]);
        
        return answer;
    }
}
