import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Beer implements Comparable<Beer> {
	int like;
	int level;
	
	public Beer(int like, int level) {
		this.like = like;
		this.level = level;
	}
	
	// 도수가 낮은 순서대로, 선호도가 높은 순서대로
	public int compareTo(Beer o) {
		if(this.level != o.level) {
			return Integer.compare(this.level, o.level);
		} else {
			return Integer.compare(o.level, this.level);
		}
	}
	
}

public class BOJ_S2_17503_맥주축제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int ans = -1;
		
		// 먹으려는 맥주의 선호도들을 집어넣을 pq
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Beer [] arr = new Beer[K];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int like = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			arr[i] = new Beer(like, level);
		}
		
		// 도수가 낮은 순서부터, 선호도가 높은 순서부터 정렬
		Arrays.sort(arr);
		
		// 선택한 맥주들의 선호도 합을 저장할 변수
		int sum = 0;
		
		for(Beer now : arr) {
			pq.add(now.like);
			sum += now.like;
			
			// 이미 N개의 맥주를 골랐다면, 선호도가 가장 낮은 맥주를 빼버린다.
			if(pq.size() > N) {
				sum -= pq.poll();
			}
			
			// N개를 선택했고, 선호도 합이 M을 넘으면 지금 고른 맥주가 조건을 만족하는 최소의 간 레벨이 되고, 지금 맥주의 도수가 선택한 맥주들의 도수 중 가장 높으므로 결과로 출력한다.
			if(pq.size() == N && sum >= M) {
				ans = now.level;
				break;
			}
			
		}
		
		System.out.println(ans);
		
	}

}
