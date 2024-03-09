import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static StringTokenizer tokens;
    static Node[] Tree;
    static int[] parents;
    static int c;
    static int[] MinArr;
    static int[] MaxArr;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        Tree = new Node[N+1];
        parents = new int[N+1];
        MinArr = new int[N+1];
        MaxArr = new int[N+1];

        Arrays.fill(MinArr, Integer.MAX_VALUE);
        Arrays.fill(MaxArr, Integer.MIN_VALUE);

        for(int i=1; i<=N; i++){
            tokens = new StringTokenizer(input.readLine());

            int node = Integer.parseInt(tokens.nextToken());
            int left = Integer.parseInt(tokens.nextToken());
            int right = Integer.parseInt(tokens.nextToken());

            if(left != -1) parents[left] = node;
            if(right != -1) parents[right] = node;

            Tree[node] = new Node(left, right);
        }

        // r : level, c : depth
        c = 0;

        // 루트 노드 찾기
        int root = 0;
        for(int i=1; i<=N; i++){
            if(parents[i] == 0) root = i;
        }

        dfs(root, 1);

        // 너비 구하기
        int maxWidth = Integer.MIN_VALUE;
        int ansLevel = 0;
        for(int i=1; i<=N; i++){
            int width = MaxArr[i] - MinArr[i] + 1;
            if(width > maxWidth){
                ansLevel = i;
                maxWidth = width;
            }
        }

        System.out.println(ansLevel+" "+maxWidth);

    }

    static void dfs(int cur, int level) {
        // 왼쪽
        if(Tree[cur].left != -1) {
            dfs(Tree[cur].left, level+1);
        }

        // 루트
        c++;
        MinArr[level] = Math.min(MinArr[level], c);
        MaxArr[level] = Math.max(MaxArr[level], c);

        // 오른쪽
        if(Tree[cur].right != -1) {
            dfs(Tree[cur].right, level+1);
        }

    }

    static class Node{
        int left, right;
        public Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
}