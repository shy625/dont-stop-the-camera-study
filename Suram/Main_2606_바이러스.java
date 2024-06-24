import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2606_바이러스 {
	static int N;
	static int link;
	static ArrayList<ArrayList<Integer>> pair = new ArrayList<ArrayList<Integer>>();
	static int count;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		N = Integer.parseInt(br.readLine());
		link = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		
		for (int i = 0; i <= N ; i++) {
			ArrayList<Integer> element = new ArrayList<>(); 
			pair.add(element);
		}
		
		for (int i = 0 ; i < link ; i++) {
			st= new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pair.get(start).add(end);
			pair.get(end).add(start);
		} // 입력 처리 - pair에는 모든 연결들이 저장이 됨 
		
		bfs(1); 
		
		System.out.println(count);	
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>(); 
		queue.offer(start);
		
		visited[start] = true; 
		
		while(!queue.isEmpty()) { // bfs 사용해서 visit하지 않은 노드들 세 주면서 count++
			int next = queue.poll();
			for(int i = 0 ; i < pair.get(next).size(); i++){
				int temp = pair.get(next).get(i);
				if(!visited[temp]) {
					visited[temp] = true;
					count++;
					queue.offer(temp);
				}
			}
		}
	}
}
