package sw_1959;

import java.util.Scanner;

public class Solution {
	public static void main(String args[]) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			int N = sc.nextInt();
            int M = sc.nextInt();
            int[] A = new int[N];
            int[] B = new int[M];
            
            for(int i = 0; i< N; i++){
            	A[i] = sc.nextInt();
            }
            for(int i = 0; i< M; i++){
            	B[i] = sc.nextInt();
            }
            
            int max = 0;
            
            if(M>N) {
            	for(int i=0; i<M-N+1; i++) {
            		int sum =0;
            		for(int j =0; j<N; j++) {
            			sum += A[j] * B[i+j];
            		}
            		max = Math.max(max, sum);
            		
            	}
            }
            
            if(M<N) {
            	for(int i=0; i<N-M+1; i++) {
            		int sum =0;
            		for(int j =0; j<M; j++) {
            			sum += B[j] * A[i+j];
            		}
            		max = Math.max(max, sum);
            		
            	}
            }
            // N == M ÀÏ ¶§ 
            else {
            	int sum =0;
            	
            	for(int k =0; k<N; k++) {
            		sum += A[k] *B[k];
            	}
            	max = Math.max(max, sum);
            }
            
            System.out.println("#"+test_case+" "+max);

		}
		sc.close();
	}
}




