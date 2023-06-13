import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int n;
	static List<Room> roomlist;
	public static void main(String[] args) throws Exception {
		while(true) {
			n = Integer.parseInt(input.readLine());
			if(n == 0) break;
			
			roomlist = new ArrayList<>();
			roomlist.add(null);
			
			// n개의 방정보 입력 받기
			for(int i=0; i<n; i++) {
				tokens = new StringTokenizer(input.readLine());
				
				String roomType = tokens.nextToken();
				int cost = Integer.parseInt(tokens.nextToken());
				List<Integer> doors = new ArrayList<>();
				
				// 갈 수 있는 문 번호 입력받기
				while(true) {
					int doorNum = Integer.parseInt(tokens.nextToken());
					if(doorNum == 0) break;
					
					doors.add(doorNum);
				}
				
				// 문 번호 입력 다 받았으면 Roomlist에 추가
				roomlist.add(new Room(roomType, cost, doors, i+1, 0));
			}
			
			// 미로 탐색
			sb.append(bfs() + "\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	static String bfs() {
		Queue<Room> queue = new LinkedList<>();
		queue.offer(roomlist.get(1));
		
		boolean[][] visited = new boolean[n+1][n+1];
		
		
		while(!queue.isEmpty()) {
			Room cur = queue.poll();
			if(cur.roomNum == n) return "Yes";
			
			int size = cur.doors.size();
			
			for(int i=0; i<size; i++) {
				// 갈 수 있는 문 번호
				int index = cur.doors.get(i);
				Room nextRoom = roomlist.get(index);
				
				if(visited[cur.roomNum][nextRoom.roomNum]) continue;
				
				if(cur.roomNum == index) continue;
				
				// 레프리콘 방일 때
				if(nextRoom.roomType.equals("L")) {
					// 현재 금액 체크
					if(nextRoom.cost > cur.curCost) {
						queue.offer(new Room(nextRoom.roomType, nextRoom.cost, nextRoom.doors, nextRoom.roomNum, nextRoom.cost));
						visited[cur.roomNum][nextRoom.roomNum] = true;
					}else {
						queue.offer(new Room(nextRoom.roomType, nextRoom.cost, nextRoom.doors, nextRoom.roomNum, cur.curCost));
						visited[cur.roomNum][nextRoom.roomNum] = true;
					}
				}
				else if(nextRoom.roomType.equals("T")) {
					// 방에 들어갈 수 있는지 체크
					if(nextRoom.cost > cur.curCost) continue;
					else {
						queue.offer(new Room(nextRoom.roomType, nextRoom.cost, nextRoom.doors, nextRoom.roomNum, cur.curCost - nextRoom.cost));
						visited[cur.roomNum][nextRoom.roomNum] = true;
				
					}
					
				}
				
			}
		}
		
		return "No";
	}
	
	static class Room {
		String roomType;
		int cost;
		List<Integer> doors;
		int roomNum;
		int curCost;
		
		public Room(String roomType, int cost, List<Integer> doors, int roomNum, int curCost) {
			super();
			this.roomType = roomType;
			this.cost = cost;
			this.doors = doors;
			this.roomNum = roomNum;
			this.curCost = curCost;
		}

		@Override
		public String toString() {
			return "Room [roomType=" + roomType + ", cost=" + cost + ", doors=" + doors + ", roomNum=" + roomNum
					+ ", curCost=" + curCost + "]";
		}

	}
}