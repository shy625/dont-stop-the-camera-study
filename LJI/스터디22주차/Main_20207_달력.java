import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20207_달력 {

	static int [] days;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->Integer.compare(x[0], y[0])==0?Integer.compare(x[1], y[1]):Integer.compare(x[0], y[0]));
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			pq.offer(new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
		}
		days=new int[365+1];
		
		
		int answer=0;
		int height=0;//가장 일이 많은 날 == 높이
		int start=0;//시작
		int end=0;//끝
		boolean canStart=true;//다시 시작하는 사각형인가
		
		//첫 일정
		int [] temp=pq.poll();
		start=temp[0];
		end=temp[1];
		height=1;
		for (int i = start; i <=end ; i++) {
			days[i]++;
		}
		
		//다음 일정들은 날짜 비교
		while(!pq.isEmpty()) {
			int[] cur=pq.poll();
			
			if (end+1<cur[0]) {//이전꺼를 하나의 사각형으로 묶어야한다
				//이전꺼 사각형 크기 저장 및 변수 초기화
				answer+=(end-start+1)*height;
				//System.out.println(start+" "+end+" "+height);
				//새로운 사각형 시작
				start=cur[0];
				end=cur[1];
				height=1;
				for (int i = start; i <= end; i++) {
					days[i]++;
				}
			}else {//사각형의 일부이다
				//높이 갱신
				for (int i = cur[0]; i <= cur[1]; i++) {
					height=Math.max(height, ++days[i]);
				}
				//end 값 갱신
				end=Math.max(end, cur[1]);
			}
		}
		
		answer+=(end-start+1)*height;//마지막 사각형 더해주기
		System.out.println(answer);
	}

}
