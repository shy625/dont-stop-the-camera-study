import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_S2_1411_비슷한단어 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String [] arr = new String[N];
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		for (int i = 0; i < N; i++) {
			String str1 = arr[i];
			second_for:
			for (int j = i+1; j < N; j++) {
				String str2 = arr[j];
				Map<Character, Character> map = new HashMap<>();
				for (int k = 0; k < str1.length(); k++) {
					char c1 = str1.charAt(k);
					char c2 = str2.charAt(k);
					if(map.containsKey(c1)) {
						if(map.get(c1) != c2) {
							continue second_for;
						}
					} else {
						for (Map.Entry<Character, Character> entry : map.entrySet()) {
							if(entry.getValue() == c2) {
								continue second_for;
							}
						}
						
						map.put(c1, c2);
						
					}
				}
				ans++;
			}
		}
		
		System.out.println(ans);
		
	}

}
