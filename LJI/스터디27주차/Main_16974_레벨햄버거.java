import java.util.Scanner;

public class Main_16974_레벨햄버거 {

	//버거를 총 사이즈와 패티의 장수를 각각 기록하고 계산해 나가면 될 것 같다
	static int N;
	static long X;
	static long[] dpPatty;
	static long[] dpSize;
	static long total;//먹은 총 패티수
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		X=scann.nextLong();
		dpPatty=new long[N+1];
		dpSize=new long[N+1];
		
		dpPatty[0]=1L;
		dpSize[0]=1L;
		for (int i = 1; i <=N; i++) {
			dpPatty[i]=dpPatty[i-1]*2+1;
			dpSize[i]=dpSize[i-1]*2+3;
		}
		total=0;
		eat(N,X);//N버거를 X장까지 먹었다

		System.out.println(total);
	}
	private static void eat(int n, long x) {
		if(n==0&&x==1) {//레벨-0버거 까지 왔으므로 패티 한장 더해주기
			total++;
			return;
		}
		if(x<=1) {//먹을수 있는 패티가 없다
			return;
		};
		
		long middle=dpSize[n]/2 +1;//현재 가운데 패티의 위치 찾기
		
		if(x==dpSize[n]) {
			total+=dpPatty[n];
		}
		else if(x==middle) {//패티 왼쪽은 다 먹고 중앙 패티까지
			total++;
			total+=dpPatty[n-1];
		}else if(x<middle) {//패티보다 왼쪽 //왼쪽 번 하나 제거해줘야한다
			eat(n-1,x-1);
		}else {//패티보다 오른쪽//왼쪽 버거는 다 먹고  추가로 가운데 패티 먹고 오른쪽 또 들어가야한다 이때 오른쪽 빵 제거
			total++;
			total+=dpPatty[n-1];
			long rest=x-middle;
			eat(n-1,rest);
		}
		return;
	}

}
