import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_24954_물약구매 {
	static int [] cost;
	static List<ArrayList<int []>> list;
	static boolean [] v;
	static int N, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N];
		v = new boolean[N];
		list = new ArrayList<>();
		min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<int[]>());
		}
		
		for (int i = 0; i < N; i++) {
			int P = Integer.parseInt(br.readLine());
			for (int j = 0; j < P; j++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken())-1;
				int value = Integer.parseInt(st.nextToken());
				list.get(i).add(new int[] {idx, value});
			}
		}
		
		dfs(0, 0);
		
		System.out.println(min);
		
	}

	private static void dfs(int cnt, int sum) {
		if(cnt == N) {			
			min = Math.min(min, sum);
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			for (int [] arr : list.get(i)) {
				int idx = arr[0];
				int value = arr[1];
				
				cost[idx] -= value;
			}
			
			cnt++;
			if(cost[i] >= 1) sum += cost[i];
			else sum++;
			
			dfs(cnt, sum);

			cnt--;
			if(cost[i] >= 1) sum -= cost[i];
			else sum--;
			
			for (int [] arr : list.get(i)) {
				int idx = arr[0];
				int value = arr[1];
				
				cost[idx] += value;
			}
			
			v[i] = false;
			
		}
		
	}

}
