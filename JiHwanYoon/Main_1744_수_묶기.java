

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_1744_수_묶기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 양수들과 0, 음수들을 따로 저장한다.
		ArrayList<Integer> positives = new ArrayList<>(); 
		ArrayList<Integer> negatives = new ArrayList<>();		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n > 0) positives.add(n);
			else negatives.add(n);
		}
		// 양수들은 내림차순, 0 또는 음수들은 오름차순으로 정렬
		Collections.sort(positives, Collections.reverseOrder());
		Collections.sort(negatives);
		int sum = 0; // 합의 최댓값
		int i = 0; // 현재 인덱스
		// 양수들에 대해서는 2개씩 뽑고, 둘다 1이 아닌 경우에만 곱한 뒤 sum에 더해준다.  
		while (i < positives.size()) {
			if (i+1 < positives.size() && positives.get(i) != 1 && positives.get(i+1) != 1)
				sum += positives.get(i++) * positives.get(i++);
			else
				sum += positives.get(i++);
		}
		i = 0;
		// 음수들에 대해서는 2개씩 뽑고, 곱한 뒤 sum에 더해준다.
		while (i < negatives.size()) {
			if (i + 1 < negatives.size())
				sum += negatives.get(i++) * negatives.get(i++);
			else
				sum += negatives.get(i++);
		}
		System.out.println(sum);
	}

}
