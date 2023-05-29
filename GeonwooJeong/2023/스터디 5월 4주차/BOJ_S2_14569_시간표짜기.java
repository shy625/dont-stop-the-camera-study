import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_S2_14569_시간표짜기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			list.add(new ArrayList<Integer>());
			for (int j = 0; j < n; j++) {
				list.get(i).add(Integer.parseInt(st.nextToken())-1);
			}
			
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			boolean [][] arr = new boolean[5][10];
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < n; j++) {
				int n2 = Integer.parseInt(st.nextToken())-1;
				arr[n2/10][n2%10] = true;
			}
			
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				boolean check = true;
				for(int num : list.get(j)) {
					if(!arr[num/10][num%10]) {
						check = false;
						break;
					}
				}
				if(check) cnt++;
			}
			
			sb.append(cnt+"\n");
			
		}
		
		System.out.println(sb.toString());
		
	}

}
