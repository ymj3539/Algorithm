import java.util.*;
import java.io.*;
class Solution {
    static List<String> totalPath;
    static String[][] Tickets;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        totalPath = new ArrayList<>();
        Tickets = new String[tickets.length][tickets[0].length];
        for(int i=0; i<tickets.length; i++){
            Tickets[i] = tickets[i].clone();
        }
        dfs(0, "ICN", "ICN", new boolean[tickets.length]);
        Collections.sort(totalPath);
        answer = totalPath.get(0).split(" ");
        return answer;
    }
    
    static void dfs(int nth, String cur, String path, boolean[] visited){
        if(nth == Tickets.length){
            totalPath.add(path);
            return;
        }
        
        for(int i=0; i<Tickets.length; i++){
            if(visited[i]) continue;
            if(Tickets[i][0].equals(cur)) {
                visited[i] = true;
                dfs(nth+1, Tickets[i][1], path+" "+Tickets[i][1], visited);
                visited[i] = false;
            }
        }
        
    }
}