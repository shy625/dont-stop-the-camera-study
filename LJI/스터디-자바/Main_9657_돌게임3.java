import java.util.Scanner;

public class Main_9657_돌게임3 {
	//완벽하게 게임을 한다? 이길수 있는 경우를 찾아야한다
	//A플레이어 차례일 때 남은 돌이 1,3,4,5개면 무조건 A가 이긴다 (1,3,4는 가져가면 되고 5 는 3개만 가져가고 넘기면 된다)
	//2일때는? 하나밖에 못가져가기에 두개일 때는 무조건 질것
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		//상근이가 이기면 1 지면 0 
		int dp[] =new int[1001];
		dp[0]=0;
		dp[1]=1;
		dp[2]=0;
		dp[3]=1;
		dp[4]=1;
		dp[5]=1;
		
		int N=scann.nextInt();
		//자기 턴에 1,3,4개 가져갈 수 있기 때문에 i턴일 때 i-1,i-3,i-4 개를 가져갔을 때가 상대편의 턴이 된다.
		//이 때 완벽하게 한다는 조건이 있으니까 i-1,i-3,i-4 중 하나라도 상대를 패배하게 하는 조건이 있다면 그거를 낸다고 생각하자/
		//즉 ,상근이의 기준으로 i-1,i-3,i-4 중 하나라도 0이 있다면 영창이가 지는 경우인 0을 주는 방식으로 플레이하기에 1로 승리하게 될 것
		for (int i = 6; i <= N; i++) {
			if(dp[i-1]==0||dp[i-3]==0||dp[i-4]==0) dp[i]=1;
			else dp[i]=0;
		}
		
		if(dp[N]==1)System.out.println("SK");
		else System.out.println("CY");
	}

}
