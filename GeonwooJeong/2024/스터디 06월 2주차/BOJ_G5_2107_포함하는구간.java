import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_2107_포함하는구간 {
	// 시작점, 끝점, 포함하는 구간의 수
	static class Section implements Comparable<Section> {
		int start;
		int end;
		int val;
		
		public Section(int start, int end, int val) {
			this.start = start;
			this.end = end;
			this.val = val;
		}

		@Override
		public int compareTo(Section o) {
			return Integer.compare(this.start, o.start);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// start점이 가장 낮은 수의 구간부터 확인하기 위해 PQ를 사용함.
		PriorityQueue<Section> origin = new PriorityQueue<>();
		// 한 번 확인한 구간을 저장해서 그 사이에 몇 개의 구간이 존재하는지 확인하기 위한 list
		List<Section> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			origin.add(new Section(start, end, 0));
		}
		
		// 가장 첫 구간은 미리 뺀다.
		list.add(origin.poll());
		
		// 가장 많은 구간을 포함하는 구간
		int max = 0;
		// list를 탐색하는 과정에서 시간복잡도를 줄이기 위한 idx,
		// idx보다 낮은 구간은 더이상 확인하지 않아도 된다.
		int idx = 0;
		
		// 모든 구간을 확인한다.
		while(!origin.isEmpty()) {
			Section poll = origin.poll();
			
			int newIdx = idx;
			// 지금 뽑은 구간이 한 번이라도 list안의 구간 사이에 끼어있는지 확인할 변수
			boolean flag = true;
			// newIdx부터 list의 size만큼 모든 구간을 탐색한다.
			while(newIdx < list.size()) {
				Section now = list.get(newIdx);
				
				// list안에 있는 구간이 현재 뽑은 구간을 포함하고 있다면
				// 해당 구간의 val을 ++해주고, flag를 false처리 해준다.
				if(now.end > poll.end) {
					list.get(newIdx).val++;
					flag = false;
				// list안에 있는 구간의 끝점이 현재 뽑은 구간의 시작점보다 작다면,
				// 해당 구간은 더이상 확인해주지 않아도 된다. (앞으로도 val이 오를 일이 없음)
				} else if(now.end < poll.start) {
					idx++;
					// 더이상 탐색하지 않을 구간에 대해 max를 갱신해준다.
					max = Math.max(max, now.val);
				}
				
				newIdx++;
			}
			
			// 만약 한 번이라도 list에 있는 구간에 사이에 끼어있지 않다면 해당 구간도 list에 넣어준다.
			if(flag) list.add(poll);
		}
		
		// 마지막으로 list에 남아있는 구간에 대해 max를 갱신해준다.
		for (int i = idx; i < list.size(); i++) {
			max = Math.max(max, list.get(i).val);
		}
		
		System.out.println(max);
		
	}

}
