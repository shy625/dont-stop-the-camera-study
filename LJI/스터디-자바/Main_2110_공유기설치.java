import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2110_공유기설치 {

	//특정 거리가 가능한지 여부를 따져야한다 ,이것을 이분탐색하는게 매개변수탐색인 것 같다
	static int N,C;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		
		list=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		list.sort(Comparator.naturalOrder());
		
		
		//최댓값을 찾는 다는 것은  [t,t,t,.....,f,f,] 로 되어있는 공유기를 설치하는 거리에 대한 배열이 있다고 가정했을 때
		//...t,t,f,f... 배열 중에 좌측에 처럼 t->f 값으로 넘어가는 부분에서 t, 즉, 마지막 t를 찾는게 목적
		//반대로 최솟값을 찾는다면 [f,f,f,...,t,t] 배열에서 내려가는 마지막 t를 찾아야 할 것이다
		int start=1;//최소로 가능한 거리
		int end=list.get(list.size()-1);//최대로 가능한 거리
		while(start+1<end) {
			int answer=(start+end)/2;//현재 가능한 후보군인 거리들의 중간값 //여기서부턴 이분 탐색과 유사
			
			if(isP(answer)) {//거리가 더 작아 가능한 영역
				start=answer;//가능하다고 확정된 answer를 start로 잡는다
			}else {//거리가 더 넓어 answer만큼의 거리로 공유기 설치가 불가
				end=answer;//불가능하다고 판별된 answer를 end 값에 넣어서 그 뒤를 전부 제거
			}
		}
		
		System.out.println(start);//isP(start)는 가능하고 isP(End)는 불가하다
		//특이한 점은 mid값을 찾는게 일치하면 멈추는 이진탐색과 다르게 가능한 영역에서 끝까지 찾는 방식이기에 mid 값을 답으로 가져오고 멈추지 않고 끝까지 배열 탐색 후 start나 end값으로 결과를 낸다는 점이다
	}
	private static boolean isP(int answer) {
		int cnt=1;//처음 집에 설치
		int index=0;
		for (int i = 1; i < N; i++) {
			
			if(list.get(i)-list.get(index)>=answer) {//answer라는 최대 거리보다 멀어졌다
				index=i;
				cnt++;
			}
			
			if(cnt>=C)return true;
		}
		return false;
	}

}
