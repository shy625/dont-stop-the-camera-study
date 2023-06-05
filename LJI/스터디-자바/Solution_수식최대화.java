import java.util.ArrayList;

public class Solution_수식최대화 {
	
	static long answer = 0;
	class Solution {
		
	    public long solution(String expression) {
	        //long answer = 0;
	        //연산기호 선언
	        char[] oper=new char[]{'*','+','-'};
	        //숫자랑 연산기호 분리
	        ArrayList<Long> nums=new ArrayList<>();
	        ArrayList<Character> opers=new ArrayList<>();
	        //
	        String [] strs=expression.split("\\*|\\+|-");
	        for (int i = 0; i < strs.length; i++) {
				nums.add(Long.parseLong(strs[i]));
			}
	        for (int i = 0; i < expression.length(); i++) {
				if(expression.charAt(i)=='*'||expression.charAt(i)=='+'||expression.charAt(i)=='-') {
					opers.add(expression.charAt(i));
				}
			}
	        
	        //순서에 맞게 문자열 계산
	        Calc(0,0,new boolean[3],oper,nums,opers);
	        return answer;
	        
	    }

		private void Calc(int cnt, long sum, boolean[] v, char[] oper, ArrayList<Long> nums, ArrayList<Character> opers) {
			if(cnt==3) {
				if(answer<Math.abs(nums.get(0)))answer=Math.abs(nums.get(0));
			}
			for (int i = 0; i < oper.length; i++) {
				if(v[i])continue;
				
				//연산 실행
				v[i]=true;
				ArrayList<Long> newNums=(ArrayList<Long>) nums.clone();
				ArrayList<Character> newOpers=(ArrayList<Character>) opers.clone();
				
				//연산 기호의 맞게 연산 실행// 0 + 1 이라면 0번째는 연산값이 들어가고 + 와 1은 삭제
				int idx=0;
				int size=newOpers.size();
				for (int j = 0; j < size; j++) {
					if(newOpers.get(idx)==oper[i]) {
						long temp=0;
						if(oper[i]=='*') {
							temp=newNums.get(idx)*newNums.get(idx+1);
						}else if(oper[i]=='+') {
							temp=newNums.get(idx)+newNums.get(idx+1);
						}else {
							temp=newNums.get(idx)-newNums.get(idx+1);
						}
						newNums.add(idx,temp);
						newNums.remove(idx+1);
						newNums.remove(idx+1);
						newOpers.remove(idx);
					}else {
						idx++;
					}
				}
				
				//
				Calc(cnt+1,sum,v,oper,newNums,newOpers);
				v[i]=false;
			}
		}

		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
