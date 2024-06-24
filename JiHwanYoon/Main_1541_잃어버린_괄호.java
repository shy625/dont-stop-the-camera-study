

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1541_잃어버린_괄호 {
	/**
	 * 전체적인 알고리즘
	 * 1. 핵심은 뺄셈 뒤에 있는 덧셈들만 괄호로 묶어줘서 빼 주는 값을 최대화하면 계산 결과를 최소화할 수 있다.
	 * 2. 이를 위해 뺄셈 기호를 기준으로 연산식을 나누고, 각 연산식을 덧셈식으로 나눠 이를 계산한다.
	 * 3. 그리고 첫 계산 결과에서 나머지 계산 결과를 차례로 빼 주면 된다. 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 뺄셈 기호를 기준으로 연산식을 나눈다.
		String[] cals = br.readLine().split("-");
		// 위에서 나눈 연산식을 계산한 결과를 저장하기 위한 ArrayList
		ArrayList<Integer> results = new ArrayList<>();
		// 각 연산식(덧셈만으로 이루어졌거나 하나의 수)을 계산해 results에 넣는다.
		for (String cal : cals) {
			int cur = 0;
			// String의 split 연산은 "+"을 인수로 하면 특이한 예외가 발생한다. 그래서 "\\+"로 나눈다.
			String[] nums = cal.split("\\+");
			for (String num : nums) {
				cur += Integer.parseInt(num);
			}
			results.add(cur);
		}
		// 첫 연산식의 연산 결과에서 나머지 연산식의 연산 결과를 빼준다.
		int res = results.get(0);
		for (int i = 1; i < results.size(); i++) {
			res -= results.get(i);
		}
		// 출력
		System.out.println(res);
	}
}
