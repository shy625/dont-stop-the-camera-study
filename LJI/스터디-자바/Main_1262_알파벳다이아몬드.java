import java.util.Scanner;

public class Main_1262_알파벳다이아몬드 {
	
	
	//N부터 1까지 왔다 갔다 하는 형태//N이 26이 넘으면?
	//이걸 한 사이클로 하면? 2N-1
	//N=3, 사이클=5 , 1,2,3,2,1
	//행의 경우
	//r을 5로 나눴을때 나머지가 N보다 작거나 같으면? 1,2,3 나머지 그대로 열 문자 갯수
	//N보다 크거나 0이면? N보다 클 때:N-나머지 , 0일 때 :1
	
	//열의 경우: .위치를 고려, c,b,a,b,c
	//현재 문자 갯수가 2일때? .cbc. 의 형태
	//c가 5로 나눴을 때 나머지가 N보다 작거나 같으면? 1일 떄 . , 2일때 c , 3일떄 b
	//가운데 문자에서 절댓값 차이만큼 증가하는 구조??? 2보다 크거나 같으면 .
	static int N;
	static int R1,C1,R2,C2;
	static char [] Alpha= 
		{'z','a','b','c','d','e','f','g','h','i',
		 'j','k','l','m','n','o','p','q','r','s',
		 't','u','v','w','x','y','z'};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		R1=scann.nextInt()+1;
		C1=scann.nextInt()+1;
		R2=scann.nextInt()+1;
		C2=scann.nextInt()+1;
		
		int max=2*N-1;//최대 수
		StringBuilder sb=new StringBuilder();
		for (int i = R1; i <=R2; i++) {
			//현재 열의 표시할 문자 사이클 수 구하기
			int cycle=0;
			int temp=i%max;
			
			if(temp==0||temp>N) {
				if(temp==0) temp=max;
				
				cycle=2*N-temp;
			}else {
				cycle=temp;
			}
			//현재 열의 중간 글자 구하기?
			int center=(N+1-cycle)%26;
			if(center==0)center=26;
			
			
			for (int j = C1; j <= C2; j++) {
				//N의 사이클로 나누기 일단
				temp=j%max;
				int diff=0;//가운데와의 차이 ,이게 cycle보다 크거나 같으면 . , 아니면 (center+diff)%26
				
				if(temp==0) temp=max;
				diff=Math.abs(temp-N);
				
				if(diff>=cycle)sb.append('.');
				else {
					int idx=(center+diff)%26;//0==26
					sb.append(Alpha[idx]);
				}
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
