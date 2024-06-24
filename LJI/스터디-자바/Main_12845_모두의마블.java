import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12845_모두의마블 {

	//값이 가장 큰 수* n-1 + 나머지 값 하면 끝 아닌가?
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		int max=0;//최대 레벨 카드 기록
		int sum=0;//1000*100000 해도 int 범위 안
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int temp=Integer.parseInt(st.nextToken());
			max=Math.max(max, temp);
			sum+=temp;
		}
		
		//sum에 최댓값이 한번 더해졌을 것이므로 max*(n-2)
		System.out.println(max*(n-2)+sum);
	}

}
