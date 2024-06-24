import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16139_인간컴퓨터상호작용2_50점 {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		char [] S=br.readLine().toCharArray();
		ArrayList<ArrayList<int[]>> list=new ArrayList<>();
		for (int i = 0; i < 26; i++) {//알파벳 갯수만큼 초기화
			list.add(new ArrayList<>());
		}
		//위치마다 문자열 저장 {현재 인덱스,알파벳 갯수}
		for (int i = 0; i < S.length; i++) {
			int alpha=S[i]-'a';//현재 알파벳을 int로 변환
			
			int lastIndex=list.get(alpha).size()-1;//현재 알파벳의 마지막 인덱스
			if(lastIndex==-1) {//아직 알파벳 나온적 없다
				list.get(alpha).add(new int[] {i,1});
			}else {
				list.get(alpha).add(new int[] {i,list.get(alpha).get(lastIndex)[1]+1});//마지막 원소에서 하나 더한 값
			}
			
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
			
			int min=0;
			int max=0;
			for(int[] cur:list.get(alphaIndex)) {
				if(cur[0]<start) {
					min=cur[1];
				}else if(cur[0]<=end) {
					max=cur[1];
				}
			}
			if(max==0) {
				max=min;
			}
			sb.append(max-min).append('\n');
		}
		System.out.println(sb.toString());
	}
}
