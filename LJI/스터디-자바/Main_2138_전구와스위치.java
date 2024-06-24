import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138_전구와스위치 {

	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		char[] now=br.readLine().toCharArray();
		char[] answer=br.readLine().toCharArray();

		//첫번째가 켜진 경우와 그렇지 않은 경우 두가지로 진행
		//그 뒤는 자신의 전 스위치의 상태만 맞춰준다.
		//마지막에 N-1의 스위치가 상태가 동일하다면 그건 출력 가능
		char [] now2=Arrays.copyOf(now, now.length);//첫번째를키는 스위치
		
		int cnt=0;
		int cnt2=1;
		
		switchLight(now2, 0);//첫번째 자리 키기
		
		for (int i = 1; i < N; i++) {//두번쨰부터 시작
			if(now[i-1]!=answer[i-1]) {
				switchLight(now, i);
				cnt++;
			}
			
			if(now2[i-1]!=answer[i-1]) {
				switchLight(now2, i);
				cnt2++;
			}
		}
		
		if(now[N-1]==answer[N-1]) {
			System.out.println(cnt);
		}else if(now2[N-1]==answer[N-1]) {
			System.out.println(cnt2);
		}else {
			System.out.println(-1);
		}
	}

	static void switchLight(char[] lights,int n) {
		if(n-1>=0) {
			if(lights[n-1]=='0')lights[n-1]='1';
			else lights[n-1]='0';
		}
		
		if(lights[n]=='0')lights[n]='1';
		else lights[n]='0';
		
		if(n+1<N) {
			if(lights[n+1]=='0')lights[n+1]='1';
			else lights[n+1]='0';
		}
	}
}
