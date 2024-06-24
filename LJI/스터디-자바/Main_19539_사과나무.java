import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19539_사과나무 {

	static int N;
	//홀수는 무조건 1이 필요하다 -> 홀수 갯수만큼 2 블럭도  넣어져야한다 / 그밖에는 3의 배수만 되면 상관 없다
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int oddCnt=0;
		int total=0;
		for (int i = 0; i < N; i++) {
			int temp= Integer.parseInt(st.nextToken());
			total += temp;
			if(temp%2==1) oddCnt++;
		}
		
		//홀수를 표현하기 위한 갯수만큼 2,1 블록이 나눠 있는지 확인
		total-= oddCnt*3;
		
		System.out.println((total>=0 && total%3==0)?"YES":"NO");
	}
}
