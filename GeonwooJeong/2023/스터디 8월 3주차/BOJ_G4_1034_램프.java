import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_G4_1034_램프 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 행의 패턴이 몇번 나왔는지 저장할 맵
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			// 행의 패턴을 입력받는다.
			String str = br.readLine();
			// 해당 패턴이 이전에 존재했다면 +1로 해서 저장한다.
			if(map.containsKey(str)) {
				map.put(str, map.get(str)+1);
			// 해당 패턴이 한번도 등장하지 않았으면 횟수 1회로 저장한다.
			} else {
				map.put(str, 1);
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		int max = 0;
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			// 패턴의 0의 개수를 확인하기 위해 key를 확인한다.
			String pattern = entry.getKey();
			// 0의 개수를 저장할 변수 cnt
			int cnt = 0;
			for (int i = 0; i < pattern.length(); i++) {
				char c = pattern.charAt(i);
				if(c == '0') cnt++;
			}
			
			// 0의 개수가 K보다 많으면 해당 패턴은 조건을 만족하지 못한다.
			if(cnt > K) continue;
			// 0의 개수가 홀수면 K도 홀수여야하고, 0의 개수가 짝수면 K도 짝수여야 한다.
			if(cnt % 2 == K % 2) {
				max = Math.max(max, entry.getValue());
			}
			
		}
		
		System.out.println(max);
		
	}

}
