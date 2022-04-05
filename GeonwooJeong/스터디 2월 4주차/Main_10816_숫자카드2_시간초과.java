import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_10816_숫자카드2_시간초과 {
	
	static class Number { // 숫자, 개수를 저장할 객체
		int num;
		int count;
		
		public Number(int num) {
			super();
			this.num = num;
			this.count = 1;
		}
		
	}
	
	static int N, M;
	static List<Number> nums; // 숫자카드를 저장할 리스트
	static int index; // 숫자카드가 존재하는 위치 인덱스를 저장할 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		nums = new ArrayList<Number>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if(search(n)) { // 숫자가 리스트에 존재하는지 찾아본다.
				nums.get(index).count++; // 존재한다면 count(개수)를 +1 해준다.
			} else {
				nums.add(new Number(n)); // 존재하지 않는다면 리스트에 넣어준다.(count = 1)
			}
		}
		
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (search(n)) {
				sb.append(nums.get(index).count).append(" "); // 숫자가 리스트에 있으면 개수를 쌓는다.
			} else {
				sb.append(0).append(" "); // 숫자가 리스트에 없으면 0을 쌓는다.
			}
		}
		
		sb.setLength(sb.length()-1);
		
		System.out.println(sb.toString());
		
	}

	private static boolean search(int n) {
		for (int i = 0; i < nums.size(); i++) { // 리스트 사이즈만큼 숫자n을 찾아보고
			if(nums.get(i).num == n) { // 리스트에 존재한다면
				index = i; // index를 업데이트 해주고 true를 리턴
				return true;
			}
		}
		return false; // 리스트에 숫자가 없다면 false를 리턴
	}

}
