import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14233_악덕사장 {

	static int n,k;
	//일을 할 수 있다?
	//문제가 뭔가 이상하다...
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		int [] arr=new int[n+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		//
		k=arr[1];
		
		for (int i = 1; i <=n; i++) {
			if(k==1) break;
			
			if(k*i>arr[i]) {
				while(k*i>arr[i]) {
					k--;
				}
				if(k==0)k=1;
			}
		}
		
		
		System.out.println(k);
	}
}
