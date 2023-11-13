import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_G5_20157_화살을쏘자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int gcd = getGCD(x, y);
			String str = x/gcd + " " + y/gcd;
			if(map.containsKey(str)) {
				map.put(str, map.get(str)+1);
			} else {
				map.put(str, 1);
			}
		}
		
		int max = 0;
		
		for(int value : map.values()) {
			max = Math.max(max, value);
		}
		
		System.out.println(max);
		
	}

	private static int getGCD(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		
		while(y != 0) {
			int tmp = y;
			y = x % y;
			x = tmp;
		}
		
		return x;
	}

}
