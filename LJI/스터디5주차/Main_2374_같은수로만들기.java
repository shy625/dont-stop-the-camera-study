import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_2374_같은수로만들기 {

	static class Group implements Comparable<Group> { //그룹의 값과 인덱스를 가진다//value를 비교해서 낮은것부터 꺼낼 것
		int value;
		int index;
		public Group(int value, int index) {
			super();
			this.value = value;
			this.index = index;
		}
		@Override
		public int compareTo(Group o) {
			return this.value-o.value;
		}
	}
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
	
		
		//동일한 숫자 끼리는 그룹핑 해서 value값으로 정렬하는 pq에 넣기
		PriorityQueue<Group> pq=new PriorityQueue<>();
		int now=0;//n은 자연수이기에 0으로 시작
		int index=0;
		
		for (int i = 0; i < N; i++) {
			int temp=Integer.parseInt(br.readLine());
			if(now== temp) continue;
			
			now=temp;
			pq.offer(new Group(now, index++));//value에는 현재 값을 넣고 인덱스는 ++해준다
		}
		
		//그룹수 카운트는 pq에서 새 그룹을 꺼낼떄마다 카운트
		//그룹수*(value값의 차이)=필요한 연산의 수
		//주변에 자기 value값까지 도달한 그룹이 있는지 파악하기 위한 boolean배열 좌우 중에 true가 있다면 그룹수 카운트에서 뺴도 된다
		int size=pq.size();
		boolean [] isGroup=new boolean[size];//pq의 사이즈만큼 반복
		int groupCnt=0;//그룹 갯수 세기
		long cnt=0; //연산횟수 카운트
		
		int preValue=0;//전 그룹의 value 값 
		while(!pq.isEmpty()) {
			Group g=pq.poll();
			
			if(g.value!=preValue) {//더 큰값이 왔으니 연산 실행
				cnt+= (long)(g.value-preValue)*groupCnt;//전 값과 현재값의 차이만큼을 존재하는 작은 그룹 수만큼 더해줘라
				preValue=g.value;
			}
			
			
			isGroup[g.index]=true;//현재가 그룹이 됐다는 것을 표시
			groupCnt++;
			
			//만약 현재 인덱스의 왼쪽이나 오른쪽이 true라면 그룹의 갯수가 하나씩 줄어든다
			if(g.index-1>=0 && isGroup[g.index-1]) {
				groupCnt--;
			}
			if(g.index+1<size && isGroup[g.index+1]) {
				groupCnt--;
			}
		}
		System.out.println(cnt);
	}
}
