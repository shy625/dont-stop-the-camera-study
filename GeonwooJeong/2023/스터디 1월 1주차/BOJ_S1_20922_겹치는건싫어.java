import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_S1_20922_겹치는건싫어 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
			if(!map.containsKey(n)) map.put(n, 0);
		}
		
		int max = 0;
		int start = 0;
		int end = 0;
		
		while(end < N) {
			while(end < N) {
				int value = map.get(arr[end]);
				if(value == K) break;
				map.put(arr[end], value+1);
				end++;
			}
			
			max = Math.max(max, end-start);
			
			int value = map.get(arr[start]);
			map.put(arr[start], value-1);
			start++;
		}
		
		System.out.println(max);
		
	}

}
