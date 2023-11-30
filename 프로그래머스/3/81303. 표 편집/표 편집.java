import java.io.*;
import java.util.*;

class Solution {
    static class Node{
        Node pre; // 앞 노드
        Node next; //뒷 노드
        boolean isDelete; // 삭제 되었니?
        
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
                // 주어진 수만큼 next쪽으로 이동
                for(int j=0; j<Integer.parseInt(str[1]); j++){
                    cur = cur.next;
                }
            }
            
//             // U 일때
            else if(str[0].equals("U")){
                // 주어진 수만큼 pre쪽으로 이동
                for(int j=0; j<Integer.parseInt(str[1]); j++){
                    cur = cur.pre;
                }
            }
            
//             // C 일때
            else if(str[0].equals("C")){
                // 스택에 넣고 삭제되었다고 표시
                stack.push(cur);
                cur.isDelete = true;
                
                // 현재 노드의 앞노드와 뒷노드
                Node pre = cur.pre;
                Node next = cur.next;
                
                // 맨 앞이 아니라면
                if(pre != null){
                    // 현재 노드의 앞노드와 뒷노드를 연결
                    pre.next = next;
                }
                
                // 맨 뒤가 아니라면
                if(next != null){
                    // 현재 노드의 뒷노드와 앞노드를 연결
                    next.pre = pre;
                    // 가리키는 행은 그 다음 행
                    cur = next;
                }
                else {
                    // 맨 뒤라면
                    // 가리키는 행은 그 이전 행
                    cur = pre;
                }
                
            }
            
//             // Z 일때 (삭제 취소)
            else if(str[0].equals("Z")){
                // 스택에서 빼고 삭제 표시 바꿔줌
                Node node = stack.pop();
                node.isDelete = false;
                
                Node pre = node.pre;
                Node next = node.next;
                
                // 맨 앞이 아니라면
                if(pre != null){
                    // 앞 노드의 next와 삭제 취소 노드 연결
                    pre.next = node;
                }
                
                // 맨뒤가 아니라면
                if(next != null){
                    // 뒷 노드의 pre와 삭제 취소 노드를 연결
                    next.pre = node;
                }
                /// 삭제 취소 작업에서 현재 가리키는 행은 수정하지 않음
            }
        }
    
        StringBuilder sb = new StringBuilder();
        
        // list 배열 돌면서 삭제된 노드는 X 표시
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