import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_G5_1490_자리수로나누기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		// 자릿수들을 저장할 set
		Set<Integer> set = new HashSet<>();
		
		// 0이 아닌 자릿수를 set에 저장한다.
		for (int i = 0; i < str.length(); i++) {
			int n = str.charAt(i) - '0';
			
			if(n != 0) set.add(n);
		}
		
		// 만약 N 그대로 조건을 만족한다면 N을 출력하고 끝낸다.
		boolean check = true;
		for(int n : set) {
			if(Integer.parseInt(str) % n != 0) {
				check = false;
				break;
			}
		}
		
		if(check) {
			System.out.print(str);
			System.exit(0);
		}
		
		// 10의 제곱의 지수를 저장할 변수 idx
		int idx = 1;
		
		// N 뒤에 숫자들을 붙여보면서 조건을 만족하는 수를 찾는다.
		outer1:
			while(true) {
				// N뒤에 숫자를 어디까지 붙여볼건지 미리 정한다. (10, 100, 1000...)
				int tail = (int) Math.pow(10, idx);
				
				// 0부터 tail-1까지의 숫자를 N 뒤에 붙여본다.
				outer2:
					for (int i = 0; i < tail; i++) {
						long num = Long.parseLong(str)*tail + i;
						
						// 조건을 하나라도 만족하지 않으면 다음 숫자를 확인해본다.
						for(int n : set) {
							if(num % n != 0) continue outer2;
						}
						
						// 조건을 모두 만족하는 수를 찾으면 출력하고 루프문을 빠져나온다.
						System.out.print(num);
						break outer1;	
					}
				
				// 지수를 1 늘린다.
				idx++;
			
			}
		
	}

}
