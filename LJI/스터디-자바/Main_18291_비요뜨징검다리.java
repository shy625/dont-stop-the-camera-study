import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_18291_비요뜨징검다리 {
	//1-> 1 , 2 -> 1, 3 -> 2, 4 -> 4 , 5 -> 8
	//따라서 N은 2^(N-2)
	static int T,N;
	static int NUM=1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		
		for (int t = 0; t < T; t++) {
			int temp=Integer.parseInt(br.readLine());
			if(temp<=2) System.out.println(1);
			else System.out.println(pow(temp-2));
		}
		
	}
	private static long pow(int temp) {
		if(temp==0) return 1;
		
		//분할정복 temp가 짝수면 pow(temp/2)*pow(temp/2)면 됨
		//홀수면 2*pow(temp/2)*pow(temp/2)이어야한다
		long half= pow(temp/2);
		if(temp%2==1) {
			return (2*half*half)%NUM;
		}else {
			return (half*half)%NUM;
		}
	}

}
