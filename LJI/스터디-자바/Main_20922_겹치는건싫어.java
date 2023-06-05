import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20922_겹치는건싫어 {

	static int N;
	static int K;
	static int [] arr;
	static int [] cntArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new int[N];
		cntArr=new int[100001];
		st=new StringTokenizer(br.readLine());
		
		for (int i = 0; i <N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		
		int cnt=0;
		int start=0;
		int end=0;
		int max=0;
		while(end!=N) {
			cntArr[arr[end]]++;
			cnt++;
			if(cntArr[arr[end]]>K) {
				while(cntArr[arr[end]]>K) {
					cntArr[arr[start++]]--;
					cnt--;
				}
			}
			if(max<cnt) max=cnt;
			end++;
		}
		
		System.out.println(max);
	}

}
