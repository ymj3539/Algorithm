class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        
        //알파벳 배열 만들기
        int[] alphabat = new int [26];
        
        // 문자열 반복
        for(int i=0; i< s.length(); i++){
            int c = s.charAt(i);
            
            // 앞에 값이 없으면
            if(alphabat[c - 'a'] == 0){
                answer[i] = -1;
                // 최신 index 갱신
                alphabat[c - 'a'] = i + 1;
            }else {
                int tmp = (i+1 - alphabat[c - 'a']);
                answer[i] = tmp;
                alphabat[c - 'a'] = i + 1;
            }
        }
        return answer;
    }
}