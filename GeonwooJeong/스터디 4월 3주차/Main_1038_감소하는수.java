import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main_1038_감소하는수 {
	// 범위가 int를 넘어서기 때문에 Long으로 선언
	static List<Long> list = new ArrayList<Long>();

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int N = scann.nextInt();
		
		// 나올 수 있는 감소하는 수 중 가장 큰 수인 9876543210을 넘어서는 숫자가 나오면 -1을 출력
		if(N> 1022) {
			System.out.println(-1);
		} else {
			// 0번부터 9번까지 감소하는 수를 구한다
			for (int i = 0; i < 10; i++) {
				recur(i, 1);
			}
			// 순서대로 정렬
			Collections.sort(list);
			// N번째로 감소하는 수 출력
			System.out.println(list.get(N));
		}
		
	}

	private static void recur(long n, int idx) {
		// 10을 넘어서면 바로 return
		if (idx > 10) {
			return;
		}
		// 리스트에 추가해준다.
		list.add(n);
		
		// 숫자 제일 마지막 자리와 비교해서 작은 수를 맨 뒤에 넣는다.
		// 기존 감수하는 수인 n에 *10을 해서 자릿수를 늘리고 작은 수를 맨 뒤에 넣어주고, 길이를 +1 해준다.
		for (int i = 0; i < n%10; i++) {
			recur((n*10)+i, idx+1);
		}
		
	}

}
