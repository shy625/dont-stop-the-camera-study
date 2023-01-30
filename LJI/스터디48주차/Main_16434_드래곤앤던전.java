import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16434_드래곤앤던전 {

	static int N;
	static long MaxHP,CurHP,ATK;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		ATK=Integer.parseInt(st.nextToken());
		MaxHP=1;
		CurHP=0;
		for (int t = 0; t < N; t++) {
			st=new StringTokenizer(br.readLine());
			int type=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int h=Integer.parseInt(st.nextToken());
			
			if(type==1) {
				//버텨야하는 총 횟수 // 몬스터의 체력/내 현재 공격력(나머지 있을 시)
				int time=(int) (h/ATK);
				if(h%ATK==0)time--;//만약 딱코면 한번 덜버텨도 된다
				long damage=(long)time*a;//이번턴에 받을 총 데미지
				
				//필요한 내 최소한의 체력 계산
				long needHP=damage+1;
				needHP -= CurHP;
				if(needHP>MaxHP) MaxHP=needHP;
				CurHP -= damage;
			}else {//포션
				ATK+=a;
				CurHP+=h;
				if(CurHP>0) CurHP=0;
			}
		}
		System.out.println(MaxHP);
	}

}
