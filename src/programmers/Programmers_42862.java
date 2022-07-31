package programmers;

public class Programmers_42862 {
	
    public static int solution(int n, int[] lost, int[] reserve) {
    	
        int[] students = new int[n];
        for(int l : lost){
            students[l-1]--;
        }
        
        for(int r : reserve) {
            students[r-1]++;
        }
        
        for(int i =0; i<n; i++){
            if(students[i] == -1 ){
                    if(i>0 && students[i-1] == 1 ){
                        students[i-1]--;
                        students[i]++;
                    }else if(i<n-1 && students[i+1] == 1){
                        students[i+1]--;
                        students[i]++;
                    }   
            }
        }
        
        int answer = 0;
        for(int s : students){
            if(s == 0 || s == 1){
                answer++;
            }
        }
        
        
        return answer;
    }
//    public static void main(String[] args) {
//    	int[] lost = {2,4};
//    	int[] reserve = {1,3,5};
//		System.out.println(solution(5, lost,reserve));
//	}
}
