import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_1931_회의실배정 {
	static class Time implements Comparable<Time> {
		int start;
		int end;
		
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			// 시작 시간이 같으면, 끝나는 시간이 큰 순서대로 정렬
			if(this.start == o.start) {
				return Integer.compare(o.end, this.end);
			}

			// 시작 시간이 다르면, 시작 시간이 큰 순서대로 정렬
			return Integer.compare(o.start, this.start);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Time> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list.add(new Time(start, end));
		}
		
		// 회의를 정해진 순서대로 정렬한다.
		Collections.sort(list);
		
		int ans = 0;
		
		// 초기 start 시간으로 MAX를 넣어 어떤 회의든 시작할 수 있게 한다.
		int lastTime = Integer.MAX_VALUE;
		
		// list의 회의를 하나씩 확인해보면서, 뒤에서부터 배치한다.
		for (Time now : list) {
			// 지금 꺼낸 회의의 끝나는 시간이 직전에 선택한 회의의 시작 시작보다 크면 선택할 수 없다.
			if(now.end > lastTime) continue;
			
			// 그 외의 경우에는, 지금 꺼낸 회의를 선택하고 lastTime을 갱신해준다.
			ans++;
			lastTime = now.start;
		}
		
		System.out.println(ans);
		
	}

}
