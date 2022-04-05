import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_15787_기차가어둠을_백준 {
	static int N,M;
	//기차를 int로 한다음에 숫자를 기차의 형태로 표현 사람이 앉는 자리에 맞춰서 2의i승을 더해주면 된다
	//2의 20승 부터 무시해도 되니 int 가능
	static int[] train;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		train=new int[N];
		
		int maxVal=1<<20;//20번째 자리 표현
		for (int t = 0; t < M; t++) {
			st= new StringTokenizer(br.readLine());
			int order=Integer.parseInt(st.nextToken());

			if(order==1 || order==2) {//인자 두개  들어옴
				int trainNum=Integer.parseInt(st.nextToken())-1;
				int seatNum=Integer.parseInt(st.nextToken())-1;
				
				int seat=1 << seatNum;//앉으려는 자리에 1 표시
				if(order==1) {//자리에 추가
					if(seat != (train[trainNum] & seat)){//즉 현재 앉으려는 자리가 비었다면
						train[trainNum]+=seat;
					}
				}if(order==2) {//자리에서 제거
					if(seat == (train[trainNum] & seat)){//즉 현재 빼려는 자리가 사람이 있다면 있다면
						train[trainNum]-=seat;
					}
				}
			}else {//인자 한개만 들어옴
				int trainNum=Integer.parseInt(st.nextToken())-1;
				if(order==3) {// <<1 연산 //만약 2의 20승자리에 도달하면 그 자리는 0
					train[trainNum]= train[trainNum]<<1;
					if(maxVal == (train[trainNum] & maxVal)){//즉 현재 앉으려는 자리가 비었다면
						train[trainNum]-=maxVal;
					}
				}else {// >>1 연산
					train[trainNum]= train[trainNum]>>1;
				}
			}
		}
		
		//train 배열 중 같은 수가 존재하면 통과 불가하다
		Map<Integer, Boolean> sameSeat=new HashMap<>();
		int cnt=0;
		for (int i = 0; i < N; i++) {
			if(!sameSeat.containsKey(train[i])) {//같은 좌석 배치가 없다면?
				cnt++;
				sameSeat.put(train[i], true);
			}
		}
		
		System.out.println(cnt);
	}
}
