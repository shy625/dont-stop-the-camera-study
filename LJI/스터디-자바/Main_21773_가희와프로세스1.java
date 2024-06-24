import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21773_가희와프로세스1 {

	static int T, n;

	// 나머지의 우선순위가 1상승한다 == 이번에 작업한 프로세스의 우선순위만 하나 낮아진다
	// 우선순위 숫자가 클수록 낮은 순위로 정하자
	static class Process implements Comparable<Process> {
		int id;
		int time;
		int pri;
		public Process(int id, int time, int pri) {
			super();
			this.id = id;
			this.time = time;
			this.pri = pri;
		}
		@Override
		public int compareTo(Process o) {
			
			return this.pri==o.pri?Integer.compare(this.id, o.id):Integer.compare(this.pri, o.pri);
		}

		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		n=Integer.parseInt(st.nextToken());
		
		PriorityQueue<Process> schedular=new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			int id=Integer.parseInt(st.nextToken());
			int time=Integer.parseInt(st.nextToken());
			int pri=-1*Integer.parseInt(st.nextToken());
			schedular.offer(new Process(id, time, pri));
		}
		
		StringBuilder ans=new StringBuilder();
		for (int t = 0; t < T; t++) {
			Process cur=schedular.poll();
			
			ans.append(cur.id).append('\n');
			cur.time--;
			if(cur.time==0)continue;//프로세스 끝내기
			
			//우선순위 올려서 다시 스케줄러 넣기
			cur.pri++;
			schedular.offer(cur);
			
		}
		
		System.out.println(ans.toString());
	}

}
