
import java.util.Scanner;

public class Main_1107_리모컨 {

	static int N,M;
	static boolean [] v;
	static int min;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);

		v=new boolean[10];
		
		N=scann.nextInt();
		M=scann.nextInt();
		
		for (int i = 0; i < M; i++) {
			v[scann.nextInt()]=true;//고장난 버튼 표시
		}
		min=Math.abs(N-100);//모든 채널버튼이 고장났을 경우 ,100번에서 이동
		//count(0,0);
		
		for (int i = 0; i < 1000000; i++) {
			if(!check(i))continue;//고장난 숫자버튼 있다면 패스
			
			//고장난 숫자가 없다면
			int channelDiff=Math.abs(N-i);//채널 간격
			
			int size=(int)Math.log10(i)+1;//현재 채널 버튼수
			if(i==0)size=1;//0번을 누른것이므로 1
			
			min=Math.min(channelDiff+size, min);
			
		}
		
		
		System.out.println(min);
	}
	private static boolean check(int n) {
		if(n==0&&v[0]) {//0번이 고장났을 때 0을 가능한걸로 통과시키면 안된다
			return false;
		}
		while(n!=0) {
			int temp=n%10;
			if(v[temp]) return false;
			
			n /=10;
		}
		return true;
	}
}
