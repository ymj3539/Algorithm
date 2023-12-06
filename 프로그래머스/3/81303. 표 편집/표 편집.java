import java.util.*;
import java.io.*;

class Solution {
    static class Node {
        Node pre;
        int ele;
        Node next;
        boolean isExist;
        
        public Node(Node p, int e, Node n, boolean i){
            this.pre = p;
            this.ele = e;
            this.next = n;
            this.isExist = i;
        }
    }
    
    Node[] list;
    Node cur;
    Stack<Node> stack;
    
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        list = new Node[n];
        stack = new Stack<>();
        
        
        list[0] = new Node(null, 0, null, true);
        for(int i=1; i<n; i++){
           list[i] = new Node(list[i-1], i, null, true);
            list[i-1].next = list[i];
        }
        
        cur = list[k];
        
        for(int i=0; i<cmd.length; i++){
            StringTokenizer tokens = new StringTokenizer(cmd[i]);
            String cmd_l = tokens.nextToken();
            
            if(cmd_l.equals("U")){
                int X = Integer.parseInt(tokens.nextToken());
                
                for(int j=0; j<X; j++){
                        cur = cur.pre;
                }
                
            }
            
            else if(cmd_l.equals("D")){
                int X = Integer.parseInt(tokens.nextToken());
                
                for(int j=0; j<X; j++){
                        cur = cur.next;    
                }
            }
            
            else if(cmd_l.equals("C")){
                cur.isExist = false;
                stack.push(cur);
                
                if(cur.pre == null){
                    cur = cur.next;
                    cur.pre = null;
                }
                
                else{
                    // 삭제 행이 가장 마지막 행일 때
                    if(cur.next == null){
                        cur = cur.pre;
                        cur.next = null;
                    }else{
                        cur.pre.next = cur.next;
                        cur.next.pre = cur.pre;
                        cur = cur.next;
                    }
                }
                
            }
            
            // Z 명령어 실행(실행 취소)
            else {
                Node rollback = stack.pop();
                list[rollback.ele].isExist = true;
                
                // 맨 앞일때
                if(rollback.pre == null){
                    rollback.next.pre = rollback;
                }
                else {
                    // 맨 끝일때
                    if(rollback.next == null){
                        rollback.pre.next = rollback;
                    }else {
                        rollback.pre.next = rollback;
                        rollback.next.pre = rollback;
                    }    
                }
                
                
                
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(list[i].isExist){
                sb.append("O");
                
            }else sb.append("X");
        }
        answer = sb.toString();
        
        return answer;
        
        
    }
}