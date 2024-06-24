import java.util.Scanner;

public class Main_10830_행렬제곱_백준 {

	//1000단위가 넘어가면 나머지만 남겨도 문제 없다
	static int N;
	static long B;
	static int [][] A;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N= scann.nextInt();
		B= scann.nextLong();
		A=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {//!!!!만약 1이 들어오고 원소가 1000이면 나눗셈 연산이 없이 리턴되어 곤란해질 수 있다 따라서 1000이면 0으로 바꿔주자 //이것땜에 계속 틀림
				int temp=scann.nextInt();
				if(temp==1000)A[i][j]=0;
				else A[i][j]=temp;
			}
		}
		//B가 홀수이면 마지막에 A한번 더 곱해야한다
		int[][] ans=dfs(B);
		
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	
	
	private static int[][] dfs(long b) {
		if(b==1) {
			return A;
		}
		int [][] temp=dfs(b/2);
		if(b%2==1) {
			return squareMulti(squareMulti(temp, temp), A );//전요소 두번 곱한 뒤 마지막으로 A한번 더 곱해야한다
		}
		else {
			return squareMulti(temp, temp);
		}
		
	}



	public static int[][] squareMulti(int a[][],int b[][]){//행렬 제곱하기
		int [][] newA=new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {
				
				for (int k = 0; k < N; k++) {
					newA[i][j]+=a[i][k]*b[k][j];
				}
				if(newA[i][j]>1000) newA[i][j] =newA[i][j]%1000;
			}
		}
		return newA;
	}
}
