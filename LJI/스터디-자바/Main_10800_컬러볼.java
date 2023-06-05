import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10800_컬러볼 {
	//크기 순으로 정렬 한 뒤 색상 별로 누적합 더하고, 색깔 상관없는 총합 더한후 같은 색일 경우 색상별 누적합을 뺴주면 될 것 같다
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		//크기 순 정렬
		PriorityQueue<ColorBall> pq=new PriorityQueue<>();
		Queue<ColorBall> origin=new LinkedList<>();//들어간 순서대로 출력해야하기에 기록
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			pq.offer(new ColorBall(a, b));
			origin.offer(new ColorBall(a, b));
		}
		
		//각 색상별 누적합은 색상이 몇개 나올지 모르므로 map활용
		Map<Integer	, Integer> map=new HashMap<>();//<색상, 누적합>
		int total=0;
		Map<ColorBall,Integer> answerMap=new HashMap<>();//정답 기록할 맵 
		
		//같은 크기의 공들을 처리해야한다.
		int maxSize=0;//현재 나온 최대 크기 기록하기 위함
		
		Queue<ColorBall> que=new LinkedList<>();//동일한 크기의 요소들을 저장해놓기 위함
		for (int i = 0; i < N; i++) {
			ColorBall cur=pq.poll();
			int color=cur.color;
			int size=cur.size;
			//이전것들보다 사이즈가 커졌으므로 que에 있는 것들 전부 누적합 ,색별 누적합에 추가
			if(size>maxSize) {
				while(!que.isEmpty()) {
					ColorBall qCur=que.poll();
					total+= qCur.size;
					if(map.containsKey(qCur.color)) {//이미 같은색 존재
						map.put(qCur.color, map.get(qCur.color)+qCur.size);//현재 색 더한 누적합으로 갱신
					}else {
						map.put(qCur.color, qCur.size);//새로 추가
					}
				}
			}
			maxSize=size;
			//현재 공이 먹을 수 있는 총 합 구하고 answerMap에 넣기
			int point=total;
			if(map.containsKey(color)) {
				point -= map.get(color);
			}
			answerMap.put(new ColorBall(color, size), point);
			//현재 공 que에 추가
			que.offer(cur);
		}
		
		//origin 순서에 맞춰서 결과값 출력
		StringBuilder sb=new StringBuilder();
		while(!origin.isEmpty()) {
			ColorBall cur=origin.poll();
			sb.append(answerMap.get(cur)).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static class ColorBall implements Comparable<ColorBall>{
		int color;
		int size;
		
		public ColorBall(int color, int size) {
			super();
			this.color = color;
			this.size = size;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + color;
			result = prime * result + size;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ColorBall other = (ColorBall) obj;
			if (color != other.color)
				return false;
			if (size != other.size)
				return false;
			return true;
		}

		@Override
		public int compareTo(ColorBall o) {
			return Integer.compare(this.size, o.size);
		}
		
	}
}
