import java.util.ArrayList;

public class Solution_자동완성 {

	

	class Solution {
		public class Word {
			char alpha;
			int cnt;
			ArrayList<Word> next;
			public Word(char alpha, int cnt) {
				this.alpha = alpha;
				this.cnt = cnt;
				next=new ArrayList<>();
			}
			
		}
		// 중복되는 단어들은 같이 포함
		// 그 뒤에는 +1 하나의 단어만 더 검색하면 된다
		// 연결리스트로 만들어볼까...
		public int solution(String[] words) {
			int answer = 0;
			
			//단어 학습
			
			//먼저 학습 리스트 만들기 
			Word dict=new Word('a',0);
			for(String word:words) {
				int n=word.length();
				
				//만약 그 문자가 이미 있으면 cnt+1
				//없으면 새로 만들기
				Word now=dict;
				for (int i = 0; i < n; i++) {
					Word temp=FindWord(now,word.charAt(i));
					temp.cnt++;
					now=temp;
				}
			}
			
			//단어 더하기
			for(String word:words) {
				int n=word.length();
				
				int cnt=0;
				//만약 그 문자가 이미 있으면 cnt+1
				//없으면 새로 만들기
				Word now=dict;
				for (int i = 0; i < n; i++) {
					Word temp=FindWord(now,word.charAt(i));
					cnt++;
					if(temp.cnt==1)break;
					now=temp;
				}
				answer+=cnt;
			}
			
			return answer;
		}
		
		//현재 Word now밑에 c라는 문자를 가진 Word가 있으면 그거 반환 없으면 새로 만들어서 주자
		private Word FindWord(Word now, char c) {
			//Word findWord=null;
			for (Word w:now.next) {
				if(w.alpha==c) return w;
			}
			Word newWord=new Word(c,0);
			now.next.add(newWord);
			return newWord;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
