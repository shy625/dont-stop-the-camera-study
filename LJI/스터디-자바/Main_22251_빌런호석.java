import java.util.Scanner;

public class Main_22251_빌런호석 {

	static int N,K,P,X;
	static int answer;
	//a->b로 숫자 바뀔때 필요한 LED수 기록
	static int [][] LED= {
			{0,4,3,3,4,3,2,3,1,2},
            {4,0,5,3,2,5,6,1,5,4},
            {3,5,0,2,5,4,3,4,2,3},
            {3,3,2,0,3,2,3,2,2,1},
            {4,2,5,3,0,3,4,3,3,2},
            {3,5,4,2,3,0,1,4,2,1},
            {2,6,3,3,4,1,0,5,1,2},
            {3,1,4,2,3,4,5,0,4,3},
            {1,5,2,2,3,2,1,4,0,1},
            {2,4,3,1,2,1,2,3,1,0}
	};
	//1부터 가능한 수(N이하||10^K이하)까지 
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		K=scann.nextInt();
		P=scann.nextInt();
		X=scann.nextInt();
		answer=0;
		for (int n = 1; n <= N; n++) {
			if(n==X)continue;//현재 층 패스
			
			if(CanChange(n)) {//바뀔 수 있다면
				answer++;
			}
		}
		System.out.println(answer);
	}
	private static boolean CanChange(int n) {
		int cnt=0;
		int x=X;
		
		for (int i = 0; i <K; i++) {
			int a = x % 10;
			int b = n % 10;
			n/=10;
			x/=10;
			cnt+=LED[a][b];//a->b로 바꾸는데 반전시켜야하는 LED 수
			
			if(cnt>P)return false;
		}
		return true;
	}

}
