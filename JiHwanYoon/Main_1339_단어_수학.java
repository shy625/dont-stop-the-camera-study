

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main_1339_단어_수학 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// greedy algorithm 활용
		// 우선 모든 알파벳을 1로 간주하고, 각 알파벳만 대해서만 합을 구한다.
		// 그리고, 각 알파벳에 대해 구한 합이 높은 순서대로 9, 8, 7, ... 을 곱하면 된다.
		// 예를 들어, GCF + ACDEB인 경우 
		// A만 고려했을 때 0 + 10000 = 10000
		// B만 고려헀을 때 0 + 1 = 1
		// C만 고려했을 때 10 + 1000 = 1010
		// D만 고려했을 때 0 + 100 = 100
		// E만 고려했을 때 0 + 10 = 10
		// F만 고려했을 때 1 + 0 = 1
		// G만 고려헀을 때 100 + 0 = 100
		// 따라서, 최댓값은 10000*9 + 1010*8 + 100*7 + 100*6 + 10*5 + 1*4 + 1*3 = 99437이다.
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				map.putIfAbsent(c, 0);
				map.put(c, map.get(c) + (int)Math.pow(10, s.length()-j-1));
			}
		}
		ArrayList<Integer> arr = new ArrayList<>();
		for (char c : map.keySet()) {
			arr.add(map.get(c));
		}
		Collections.sort(arr, Collections.reverseOrder());
		int max = 0;
		int cur = 9;
		for (int i = 0; i < arr.size(); i++) {
			max += arr.get(i)*(cur--);
		}
		System.out.println(max);
	}

}
