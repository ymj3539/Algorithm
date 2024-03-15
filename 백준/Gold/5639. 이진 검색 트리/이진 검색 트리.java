import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        int root_value = Integer.parseInt(input.readLine());

        Node root = new Node(null, root_value, null);
        min = Math.min(min, root_value);

        // 전위순회로 트리 만들기
        while(true){
            String str = input.readLine();
            if(str == null || str.equals("")) break;
            int cur = Integer.parseInt(str);
            min = Math.min(min, cur);

            insert(root, cur);
        }

        dfs(root);

    }

    static void insert(Node root, int cur){
        if(cur <  root.value){
            if(root.left == null){
                root.left = new Node(null, cur, null);

            }else {
                insert(root.left, cur);
            }
        }else {
            if(root.right == null){
                root.right = new Node(null, cur, null);
            }else {
                insert(root.right, cur);
            }
        }

    }

    static void dfs(Node cur){
//        System.out.println(cur+": "+tree.get(cur).toString());
        if(cur == null) return;
        dfs(cur.left);
        dfs(cur.right);

        System.out.println(cur.value);

    }

    static class Node{
        int value;
        Node left, right;
        public Node(Node left, int value, Node right){
            this.left = left;
            this.value = value;
            this.right = right;
        }


    }
}