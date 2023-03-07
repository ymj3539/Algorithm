import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        map = new char[12][6];
        for(int r=0; r<12; r++){
            String inputStr = input.readLine();
            for(int c=0; c<6; c++){
                map[r][c] = inputStr.charAt(c);
            }
        }
        
        
        while(true) {
        	// 한 턴
        	visited = new boolean[12][6];
        	
        	boolean flag = false;
        	
        	// map 전체 탐색
        	for(int r=11; r>=0; r--) {
        		for(int c=5; c>=0; c--) {
        			// 이번 턴에 방문을 안 했고, 뿌요이면
        			if(!visited[r][c] && map[r][c] != '.') {
        				// 주변에 같은 것 있는 지 bfs
        				List<Point> list = bfs(r, c, map[r][c]);
        				
        				// 4개 이상 연속이면 list 터트리기
        				if(list.size() >=4 ) {
        					flag = true;
        					bomb(list);
        				}
        				
        			}
        		}
        	}
        	if(flag) {
        		clean();
        		cnt++; 
        	}else break;
        	
        }
        
        System.out.println(cnt);
    }
    
    // map 정리
    static void clean() {
    	while(true) {
    		boolean flag = false;
    		for(int r=11; r>=1; r--) {
        		for(int c=5; c>=0; c--) {
        			// 만약 .이고 위에 뿌요가 있으면 밑으로 땡겨
        			if(map[r][c] == '.' && map[r-1][c] != '.') {
        				map[r][c] = map[r-1][c];
        				map[r-1][c] = '.';
        				flag = true;
        			}
        		}
        	}
    		
    		if(!flag) break;
    	}
    	
    }
    
    static void bomb(List<Point> list) {
    	for(int i=0; i<list.size(); i++) {
    		Point cur = list.get(i);
    		
    		int r = cur.r;
    		int c = cur.c;
    		map[r][c] = '.';
    	}
    }
    
    static List<Point> bfs(int r, int c, char color){
    	Queue<Point> queue = new LinkedList<>();
    	List<Point> list = new ArrayList<>();
    	
    	queue.offer(new Point(r,c,color));
    	
    	while(!queue.isEmpty()) {
    		Point cur = queue.poll();
    		
    		int nr = cur.r;
    		int nc = cur.c;
    		
    		for(int i=0; i<4; i++) {
    			int dr = nr + dy[i];
    			int dc = nc + dx[i];
    			
    			if(!isIn(dr, dc)) continue;
    			
    			if(visited[dr][dc]) continue;
    			
    			if(map[dr][dc] != cur.color) continue;
    			
    			queue.offer(new Point(dr, dc, cur.color));
    			visited[dr][dc] = true;
    			list.add(new Point(dr, dc, cur.color));
    		}
    	}
    	return list;
    }



    static class Point{
        int r, c;
        char color;
		public Point(int r, int c, char color) {
			super();
			this.r = r;
			this.c = c;
			this.color = color;
		}

        
    }

    static boolean isIn(int r, int c){
        return (r>=0 && c>=0 && r<12 && c<6);
    }
}