
import java.util.Arrays;

class Solution {
		static boolean[][][] wall;
		static int N;
		static int cnt;
		

	    public int[][] solution(int n, int[][] build_frame) {
	        int[][] answer;
	        
	        cnt = 0;
	        N = n;
	        
	        // 벽면을 나타내는 3차원 배열
	        wall = new boolean[n+1][n+1][2];
	        
	        // 순서대로 작업
	        for(int i=0; i<build_frame.length; i++) {
	        	int x = build_frame[i][0];
	        	int y = build_frame[i][1];
	        	int a = build_frame[i][2];
	        	int b = build_frame[i][3];
	        	
	        	// 기둥 설치 시
	        	if(a == 0 && b == 1) {
	        		// 조건에 맞으면 설치
	        		if(checkPillar(x,y)) {
	        			wall[x][y][0] = true;
	        			cnt++;
	        		}
	        	}
	        	
	        	// 보 설치 시
	        	else if(a == 1 && b==1) {
	        		// 조건에 맞으면 설치
	        		if(checkBo(x,y)) {
	        			wall[x][y][1] = true;
	        			cnt++;
	        		}
	        	}
	        	
	        	// 기둥 삭제 시
	        	else if(a==0 && b==0) {
	        		// 삭제
	        		wall[x][y][0] = false;
	        		cnt--;
	        		// 전체 벽면 체크 후 조건에 어긋나면 되돌리기
	        		if(!checkWall()) {
	        			wall[x][y][0] = true;
	        			cnt++;
	        		}
	        	}
	        	
	        	// 보 삭제 시
	        	else if(a==1 && b==0) {
	        		// 삭제
	        		wall[x][y][1] = false;
	        		cnt--;
	        		// 전체 벽면 체크 후 조건에 어긋나면 되돌리기
	        		if(!checkWall()) {
	        			wall[x][y][1] = true;
	        			cnt++;
	        		}
	        	}
	        	
	        	
	        }
	        

	        
	        // result 배열 만들기
	        answer = new int[cnt][3];
	        int index = 0;
	        // wall 배열 돌면서 존재하는 보와 기둥 체크
	        for(int x=0; x<N+1; x++) {
	        	for(int y=0; y<N+1; y++) {
	        		// 기둥이 존재할 때
	        		if(wall[x][y][0]) {
	        			answer[index++] = new int[]{x, y, 0};
	        		}
	        		// 보가 존재할 때
	        		if(wall[x][y][1]) {
	        			answer[index++] = new int[] {x,y,1};
	        		}
	        	}
	        }
	        
	        
	        
	        return answer;
	    }
	    
	    static boolean checkWall() {
	    	for(int x=0; x<N+1; x++) {
	    		for(int y=0; y<N+1; y++) {
	    			// 기둥 존재 시 조건 체크
	    			if(wall[x][y][0]) {
	    				if(!checkPillar(x,y)) return false;
	    			}
	    			// 보 존재 시 조건 체크
	    			if(wall[x][y][1]) {
	    				if(!checkBo(x,y)) return false;
	    			}
	    		}
	    	}
	    	return true;
	    }
	    
	    // 기둥 조건 체크
	    static boolean checkPillar(int x, int y) {
	    	// 1. 바닥에 있음
	    	if(y == 0) return true;
	    	
	    	// 2. 보의 한쪽 끝부분 위에 있음
	    	else if((x>0 && wall[x-1][y][1]) || wall[x][y][1]) return true;
	    	
	    	// 3. 기둥 위에 존재함
	    	if(y>0 && wall[x][y-1][0]) return true;
	    	
	    	return false;
	    }
	    
	    
	 // 보 조건 체크
	    static boolean checkBo(int x, int y) {
	    	// 1. 한 쪽 끝 부분이 기둥 위에 있음
	    	
	    	if((isIn(x, y-1) && wall[x][y-1][0]) || (isIn(x+1, y-1) && wall[x+1][y-1][0])) return true;
	    	
	    	
	    	// 2. 양쪽 끝이 다른 보와 동시에 연결
	    	
	    	else if(isIn(x-1,y) && wall[x-1][y][1] && isIn(x+1, y) && wall[x+1][y][1]) return true;
	    	
	    	return false;
	    }
	    
	    static boolean isIn(int x, int y) {
	    	return (x>=0 && y>=0 && x<N+1 && y<N+1);
	    }
}
