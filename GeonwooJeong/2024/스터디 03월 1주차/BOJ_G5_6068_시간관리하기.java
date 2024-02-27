import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_6068_시간관리하기 {
	static class Job implements Comparable<Job> {
		int needTime;
		int endTime;
		
		public Job(int needTime, int endTime) {
			this.needTime = needTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Job o) {
			if(this.endTime != o.endTime) {
				return Integer.compare(o.endTime, this.endTime);
			} else {
				return Integer.compare(this.needTime, o.needTime);
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Job> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			list.add(new Job(t, s));
		}
		
		// Job을 list에 담아서 끝내야하는 시간이 느리게, 걸리는 시간이 짧게 sorting 한다.
		Collections.sort(list);
		
		// 이전 Job(list에서 하나씩 꺼냈을 때 먼저 오는 Job, 시간상으로는 더 느림)의 최소 시작시간을 저장할 변수
		int pastTime = Integer.MAX_VALUE;
		
		for (Job now : list) {
			int needTime = now.needTime;
			int endTime = now.endTime;
			// pastTime과 지금 꺼낸 Job의 끝내야하는 시간 중 더 작은 값을 선택한다.
			int min = Math.min(pastTime, endTime);
			// 그 값에서 필요한 시간을 빼서 이 일을 끝내려면 언제 시작해야 하는지 구한다.
			pastTime = min - needTime;
		}
		
		// 최소 시작 시간이 0보다 작으면 -1 출력 아니라면 pastTime 출력
		if(pastTime < 0) {
			System.out.println(-1);
		} else {
			System.out.println(pastTime);
		}
		
	}

}
