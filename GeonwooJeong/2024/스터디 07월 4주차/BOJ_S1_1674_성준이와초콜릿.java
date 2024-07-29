import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_1674_성준이와초콜릿 {
	static class Caffeine implements Comparable<Caffeine> {
		String name;
		int T;
		double N;
		
		public Caffeine(String name, int t, Double n) {
			this.name = name;
			N = n;
			T = t;
		}

		@Override
		public int compareTo(Caffeine o) {
			return Integer.compare(this.T, o.T);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// Query를 저장할 리스트
		List<Integer> list = new ArrayList<>();
		// Chocolate와 Coffee를 저장할 리스트
		List<Caffeine> caffe = new ArrayList<>();
		
		String input = "";
		
		while((input = br.readLine()) != null) {
		
			StringTokenizer st = new StringTokenizer(input);
			
			String name = st.nextToken();
			
			// Query라면 list에 저장한다.
			if(name.equals("Query")) {
				int T = Integer.parseInt(st.nextToken());
				list.add(T);
				continue;
			}
			
			// 그 외의 경우라면 caffe에 저장한다.
			int T = Integer.parseInt(st.nextToken());
			Double N = Double.parseDouble(st.nextToken());
			
			caffe.add(new Caffeine(name, T, N));
		}
		
		// 두 리스트 모두 시간이 빠른 순서로 정렬한다.
		Collections.sort(list);
		Collections.sort(caffe);
		
		// 시간이 빠른 Query부터 하나씩 꺼내보면서 계산한다.
		for (int n : list) {
			// 공격 반경의 합을 저장할 변수
			double sum = 0.0;
			// caffe의 공격 반경을 모두 계산해본다.
			for (int i = 0; i < caffe.size(); i++) {
				Caffeine now = caffe.get(i);
				
				// 만약 초콜릿이나 커피를 마신 시간이 n 이후라면 더이상의 계산을 멈춘다.
				if(now.T > n) break;
				
				double cal = 0.0;
				// 각각 정해진 공식대로 계산한다.
				if(now.name.equals("Chocolate")) {
					cal = 8.0*now.N - (double)((double)n-now.T)/12.0;
				} else if(now.name.equals("Coffee")) {
					cal = 2.0*now.N - (double)((double)n-now.T)*((double)n-now.T)/79.0;
				}
				
				// 효과 반경이 음수가 되면, 그 음식을 완전히 소화한 것이다.
				// 그 외의 경우에는 sum에 더해준다.
				if(cal > 0) sum += cal;
			}
			
			// 안전거리가 1보다 작을 경우에는 1로 맞춰준다. 
			if(sum < 1.0) sum = 1.0;
			// 소숫점 첫째 자리까지 출력
			sb.append(n+" "+Math.round(sum*10)/10.0+"\n");
		}
		
		if(sb.length() > 0) {			
			sb.setLength(sb.length()-1);
		}
		System.out.println(sb.toString());
		
	}

}
