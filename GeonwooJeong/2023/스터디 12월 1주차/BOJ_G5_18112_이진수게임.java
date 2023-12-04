import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_18112_이진수게임 {
	static int ans, target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		// 이진수를 십진수로 바꿔준다.
		int start = Integer.parseInt(str1, 2);
		target = Integer.parseInt(str2, 2);
		
		// bfs 알고리즘 사용
		bfs(start);
		
		System.out.println(ans);
		
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		// 이진수 길이가 최대 10글자 이므로 1 << 11으로 범위를 잡아준다.
		boolean [] v = new boolean[1 << 11];
		v[start] = true;
		q.add(start);
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size > 0) {
				int cur = q.poll();
				
				if(cur == target) {
					ans = cnt;
					return;
				}
				
				// 덧셈 작업 (최대 숫자를 넘으면 안된다.)
				if(cur < (1 << 11) - 1 && !v[cur+1]) {
					v[cur+1] = true;
					q.add(cur+1);
				}
				
				// 뺄셈 작업 (0보다 작아지면 안된다.)
				if(cur > 0 && !v[cur-1]) {
					v[cur-1] = true;
					q.add(cur-1);
				}
				
				// cur을 이진수로 바꾼 String
				String str = Integer.toBinaryString(cur);
				int length = str.length();
				// 보수 바꾸기(맨 앞자리는 바꿀 수 없다.)
				for (int i = 0; i < length-1; i++) {
					int n = cur;
					// 뒤에서부터 i번째 글자가 0인지 1인지 판별한다.
					char d = str.charAt(length-1-i);
					
					// 뒤에서부터 i번째 글자가 1인 숫자. ex) "100" = 4, "10000" = 16
					int tmp = (int) Math.pow(2, i);
					// 0일 경우 tmp만큼 더해줘서 1로 만든다.
					if(d == '0') {
						n += tmp;
					// 1일 경우 tmp만큼 빼줘서 0으로 만든다.
					} else if(d == '1') {
						n -= tmp;
					}
					
					if(!v[n]) {
						v[n] = true;
						q.add(n);
					}
					
				}
				
				size--;
			}
			
			cnt++;

		}
		
		
	}

}
