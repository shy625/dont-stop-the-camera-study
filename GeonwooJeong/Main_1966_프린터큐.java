import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1966_프린터큐 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		LinkedList<int[]> queue = new LinkedList<int[]>();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int max = -1; // 중요도
			int count = 0; // 출력에 사용할 count
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) { // queue에 넣으면서 중요도의 첫 max값 구함
				int n = Integer.parseInt(st.nextToken());
				queue.offer(new int[] {i, n});
				max = Math.max(max, n);
			}
			
			while(true)
			{
				int [] nums = queue.poll();
				if (nums[1] == max) { // 중요도가 가장 높을 경우 빼낸다.
					count++;
					if(nums[0] == M) { // 빼낸 index가 M일 경우 루프문 종료
						break;
					} else { // max 재설정
						max = -1; 
						for (int i = 0; i < queue.size(); i++) {
							max = Math.max(max, queue.get(i)[1]);
						}
					}
				} else {
					queue.offer(nums); // 중요도가 max보다 작을 경우 queue에 다시 넣는다.
				}
				
			}
			
			System.out.println(count);
			
			queue.clear(); // queue 초기화
			
		}

	}

}
