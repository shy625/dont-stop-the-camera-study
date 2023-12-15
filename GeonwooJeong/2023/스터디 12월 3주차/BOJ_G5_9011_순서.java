import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_9011_순서 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		testcase: 
			for (int t = 1; t <= T; t++) {
				List<Integer> list = new ArrayList<>();
				ArrayDeque<Integer> q = new ArrayDeque<>();
				
				int N = Integer.parseInt(br.readLine());
				int [] R = new int[N+1];
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 1; i <= N; i++) {
					R[i] = Integer.parseInt(st.nextToken());
				}
				
				for (int i = N; i > 0; i--) {
					list.add(i);
				}
				
				for (int i = N; i > 0; i--) {
					int n = R[i];
					int size = list.size();
					int idx = size - 1 - n;
					
					if(n > size-1) {
						sb.append("IMPOSSIBLE\n");
						continue testcase;
					}
					
					int s = list.get(idx);
					list.remove(idx);
					
					q.add(s);
					
				}
				
				while(!q.isEmpty()) {
					int n = q.pollLast();
					sb.append(n).append(" ");
				}
				
				sb.setLength(sb.length()-1);
				sb.append("\n");
				
			}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
