import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_G5_11509_풍선맞추기 {

	public static void main(String[] args) throws IOException {
		// Map<Integer, Integer> map을 사용해서 현재의 높이 +1의 높이가 있는지 확인하고,
		// 없으면 현재의 높이로 map의 value를 +1 해준다.
		// 있으면 +1 높이의 value를 1개 제거하고, 마찬가지로 현재의 높이의 value를 +1 해준다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 높이 N이 이전에 몇 번 등장했는지를 저장할 map
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(map.containsKey(n+1)) {
				// n+1 값 -1
				map.put(n+1, map.get(n+1)-1);
				// value가 0이 되면 높이 n+1 key를 삭제한다.
				if(map.get(n+1) == 0) {
					map.remove(n+1);
				}
			} 
			
			// n 값 + 1
			if(map.containsKey(n)) {
				map.put(n, map.get(n)+1);
			} else {
				map.put(n, 1);
			}
		}
		
		int ans = 0;
		
		// map을 전체 순회하면서 value를 모두 더해준다.
		for (int n : map.values()) {
			ans += n;
		}
		
		System.out.println(ans);
		
	}

}
