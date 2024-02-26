import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_G4_1339_단어수학 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Map<Character, Integer> map = new HashMap<>();
		List<Character> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int l = str.length();
			int value = (int) Math.pow(10, l-1);
			for (int j = 0; j < l; j++) {
				char c = str.charAt(j);
				if(map.containsKey(c)) {
					map.put(c, map.get(c) + value);
				} else {
					map.put(c, value);
					list.add(c);
				}
				value /= 10;
			}
		}
		
		Collections.sort(list, (o1, o2) -> {
			return Integer.compare(map.get(o2), map.get(o1));
		});
		
		int startNum = 9;
		int ans = 0;
		
		for (char c : list) {
			ans += map.get(c) * (startNum--);
		}
		
		System.out.println(ans);
		
	}

}
