import java.io.*;
import java.util.*;

class Solution {
    static class Node{
        Node pre;
        Node next;
        boolean isDelete;
        
        public Node(Node pre, Node next, boolean isDelete){
            this.pre = pre;
            this.next = next;
            this.isDelete = isDelete;
        }
        
    }
    
    public String solution(int n, int k, String[] cmd) {
        Node[] list = new Node[n];
        
        list[0] = new Node(null, null, false);
        for(int i=1; i<n; i++){
            list[i] = new Node(list[i-1], null, false);
            list[i-1].next = list[i];
        }
        
        Stack<Node> stack = new Stack<>();
        Node cur  = list[k];
        
        int size = cmd.length;
        for(int i=0; i<size; i++){
            
            String[] str = cmd[i].split(" ");
            // D일때
            if(str[0].equals("D")){
                for(int j=0; j<Integer.parseInt(str[1]); j++){
                    cur = cur.next;
                }
            }
            
//             // U 일때
            else if(str[0].equals("U")){
                for(int j=0; j<Integer.parseInt(str[1]); j++){
                    cur = cur.pre;
                }
            }
            
//             // C 일때
            else if(str[0].equals("C")){
                stack.push(cur);
                cur.isDelete = true;
                
                Node pre = cur.pre;
                Node next = cur.next;
                
                if(pre != null){
                    pre.next = next;
                }
                
                if(next != null){
                    next.pre = pre;
                    cur = next;
                }
                else {
                    cur = pre;
                }
                
            }
            
//             // Z 일때
            else if(str[0].equals("Z")){
                Node node = stack.pop();
                node.isDelete = false;
                
                Node pre = node.pre;
                Node next = node.next;
                
                
                if(pre != null){
                    pre.next = node;
                }
                
                if(next != null){
                    next.pre = node;
                }
                
            }
        }
    
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<n; i++){
            if(list[i].isDelete){
                sb.append("X");
            }else {
                sb.append("O");
            }
        }
        
        
        
        
        String answer = "";
        answer = sb.toString();
        return answer;
    }
}