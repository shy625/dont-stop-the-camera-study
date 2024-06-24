import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16139_인간컴퓨터상호작용_100점 {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		char [] S=br.readLine().toCharArray();
		ArrayList<ArrayList<int[]>> list=new ArrayList<>();
		int[][] alphaList=new int[26][S.length];
		//위치마다 문자열 저장 {현재 인덱스,알파벳 갯수}
		int temp=S[0]-'a';
		alphaList[temp][0]=1;
		for (int i = 1; i < S.length; i++) {
			int alpha=S[i]-'a';//현재 알파벳을 int로 변환
			
			for (int j = 0; j < 26; j++) {
				alphaList[j][i]=alphaList[j][i-1];
			}
			alphaList[alpha][i]+=1;
			
		}
		
		
		//질문 검색
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			char alpha=st.nextToken().charAt(0);
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			
			int alphaIndex=alpha-'a';
			
			sb.append(alphaList[alphaIndex][end]-(start==0?0:alphaList[alphaIndex][start-1])).append('\n');
		}
		System.out.println(sb.toString());
	}
}
