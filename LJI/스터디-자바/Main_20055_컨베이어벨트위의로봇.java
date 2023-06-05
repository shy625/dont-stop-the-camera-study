import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇 {

	static class Belt{
		int dur;//내구도
		boolean exist;//로봇 존재 여부
		public Belt(int dur, boolean exist) {
			super();
			this.dur = dur;
			this.exist = exist;
		}
		
	}
	static int N,K;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		//Belt [] line=new Belt[2*N+1];
		ArrayList<Belt> line=new ArrayList<>();
		st=new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= 2*N; i++) {
			int dur=Integer.parseInt(st.nextToken());
			//line[i]=new Belt(dur, false);
			line.add(new Belt(dur, false));
		}
		
		int turn=1;
		while(true) {
			
			//벨트 통째로 회전
			Belt temp= line.get(2*N-1);
			line.remove(2*N-1);
			line.add(0,temp);
			//
//			StringBuilder sb=new StringBuilder();
//			for(Belt b:line) {
//				sb.append(b.dur).append(b.exist).append(" ");
//			}
//			System.out.println(sb.toString());
			int cnt=0;
			
			//N 라인에 로봇 있다면 내리기
			if(line.get(N-1).exist)line.get(N-1).exist=false;
			//역순으로 이동시키기
			for (int i = 2*N-2; i >= 0; i--) {
				if(line.get(i).exist) {//현재 칸의 로봇이 존재할 때
					if(!line.get(i+1).exist && line.get(i+1).dur>=1) {//이동 가능
						line.get(i).exist=false;
						line.get(i+1).dur--;
						line.get(i+1).exist=true;
					}
				}
				if(line.get(i+1).dur==0)cnt++;
			}
			if(line.get(N-1).exist)line.get(N-1).exist=false;
			
			
			
			//첫번째칸에 로봇 올리기
			if(!line.get(0).exist && line.get(0).dur>=1) {
				line.get(0).dur--;
				line.get(0).exist=true;
			}
			
			if(line.get(0).dur==0)cnt++;
			
			if(cnt>=K)break;
			turn++;
		}
		
		System.out.println(turn);
	}

}
