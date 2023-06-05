import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_1148_단어만들기 {

	//글자를 만약 만들 수 있다면 그 글자 구성 글자들에 ++?
	static ArrayList<String> dict;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		dict=new ArrayList<>();
		while(true) {
			String temp=br.readLine();
			if(temp.equals("-")) break;
			dict.add(temp);
		}

		//퍼즐
		while(true) {
			String temp=br.readLine();
			if(temp.equals("#")) break;
			
			char [] puzzle=new char[9];
			for (int i = 0; i <9; i++) {
				puzzle[i]=temp.charAt(i);
			}
			//알파벳 순 정렬
			Arrays.sort(puzzle);
			
			int [] cntArr=new int[9];
			boolean [] c=new boolean[9];
			
			for(String word:dict) {
				Arrays.fill(c, false);

				boolean can=true;
				//단어 하나하나 체크
				for (int i = 0; i < word.length(); i++) {
					boolean isCheck=false;
					char now=word.charAt(i);
					for (int j = 0; j < 9; j++) {
						if(puzzle[j]==now && !c[j]) {//사전의 현재 글자가 퍼즐에 있으면서 사용되지 않았을때
							c[j]=true;
							isCheck=true;
							break;
						}
					}
					
					if(! isCheck) {//단어 만들기 불가했다
						can=false;
						break;
					}
				}
				
				if(can) {//단어 퍼즐로 만들기 가능
					for (int i = 0; i <9; i++) {
						if(c[i])cntArr[i]++;
					}
				}
			}
			
			
			//중복글자 방지 대책
			Arrays.fill(c, false);
			for (int i = 1; i <9; i++) {
				if(puzzle[i]==puzzle[i-1]) c[i]=true;
			}
			
			//System.out.println(Arrays.toString(cntArr));
			//System.out.println(Arrays.toString(puzzle));
			//System.out.println(Arrays.toString(c));
			//단어별로 글자 빌드
			int max=-1;
			int min=Integer.MAX_VALUE;
			StringBuilder sbMax=new StringBuilder();
			StringBuilder sbMin=new StringBuilder();
			
			for (int i = 0; i < 9; i++) {
				if(c[i])continue;
				
				if(cntArr[i]<min) {
					min=cntArr[i];
					sbMin=new StringBuilder();
					sbMin.append(puzzle[i]);
				}else if(cntArr[i]==min) {
					sbMin.append(puzzle[i]);
				}
				
				if(cntArr[i]>max) {
					max=cntArr[i];
					sbMax=new StringBuilder();
					sbMax.append(puzzle[i]);
				}else if(cntArr[i]==max) {
					sbMax.append(puzzle[i]);
				}
			}
			
			System.out.println(sbMin.toString()+" "+min+" "+sbMax.toString()+" "+max);
		}
	}

}
