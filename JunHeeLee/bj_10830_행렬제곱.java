package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class bj_10830_행렬제곱 {
	static int N;
	static long B; //B의 범위가 int 범위를 초과하므로 long으로 선언
	static int[][] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		B = Long.parseLong(s[1]);
		matrix = new int[N][N]; //인풋배열
		
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}//input 받기
		
		int[][] result = divide(matrix,B);
		for(int i=0;i<N;i++) { //출력
			for(int j=0;j<N;j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static int[][] divide(int[][] mat,long count) { //
		if(count==1) {//count가 1인경우 mat return
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					mat[i][j] %=1000;
				}
			}
			return mat;
		}
		int[][] tmp = divide(mat,count/2);
		if(count%2 ==1) { // 2로 나누어떨어지지 않을경우 
			return mul(mul(tmp,tmp),matrix); //원본 행렬인 matrix와 쪼갠거 두개 곱함
		}
		else {
			return mul(tmp,tmp);//2로 나누어떨어질 경우 쪼개진거 두개 곱함
		}
	}
	private static int[][] mul(int[][] tmp1, int[][] tmp2) { //행렬곱 연산 함수
		int[][] temp = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					temp[i][j] += (tmp1[i][k]*tmp2[k][j])%1000;
				}
				temp[i][j] %=1000;
			}
		}
		return temp;
	}
}

