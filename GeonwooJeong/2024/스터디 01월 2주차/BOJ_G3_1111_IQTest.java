import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G3_1111_IQTest {
	static class Pair {
		int a;
		int b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	// 조건을 만족하는 a와 b의 쌍을 저장할 list
	static List<Pair> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int N = Integer.parseInt(br.readLine());
		
		// N이 1이면 항상 A를 출력해야 한다.
		if(N == 1) {
			System.out.println("A");
			System.exit(0);
		}
		
		list = new ArrayList<>();
		int [] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int n1 = nums[0];
		boolean check = true;
		
		// 모든 수가 같으면 그 숫자를 출력하고 종료한다.
		for (int i = 1; i < N; i++) {
			if(nums[i] != n1) {
				check = false;
				break;
			}
		}
		if(check) {
			System.out.println(n1);
			System.exit(0);
		}
		
		int n2 = nums[1];
		// n1과 n2를 가지고 만족하는 a와 b 쌍을 모두 구해본다.
		findPair(n1, n2);
		n1 = n2;
		
		// n1, n2를 갱신해가며 만족하는 a, b 쌍만 남긴다.
		for (int i = 2; i < N; i++) {
			n2 = nums[i];
			
			int size = list.size();
			for (int j = 0; j < size; j++) {
				Pair pair = list.get(j);
				if(n1*pair.a+pair.b != n2) {
					list.remove(j--);
					size--;
				}
			}
			
			n1 = n2;
		}
		
		// 만족하는 쌍이 여러개면 A 출력
		if(list.size() > 1) {
			System.out.println("A");
		// 만족하는 쌍이 없으면 B 출력
		} else if(list.size() == 0) {
			System.out.println("B");
		// 그 외의 경우(만족하는 쌍이 1개인 경우)에는 다음에 올 숫자를 출력한다.
		} else if(list.size() == 1) {
			Pair pair = list.get(0);
			System.out.println(n2*pair.a+pair.b);
		}
			
	}

	private static void findPair(int n1, int n2) {
		// 연립 방정식을 계산하면 a = (n1-n2)/(n0-n1)이므로, -200~200의 범위를 가진다.
		for (int i = -200; i <= 200; i++) {
			for (int j = -20000; j <= 20000; j++) {
				if(n1*i+j == n2) {
					list.add(new Pair(i, j));
				}
			}
		}
		
	}

}
