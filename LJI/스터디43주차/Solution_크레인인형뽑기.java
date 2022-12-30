import java.util.Stack;

public class Solution_크레인인형뽑기 {
	
	//그냥 단순 스택?
	class Solution {
	    public int solution(int[][] board, int[] moves) {
	        int answer = 0;	        
	        Stack<Integer> stack=new Stack<>();
	        for (int i = 0; i < moves.length; i++) {
				int dollsNum=findDoll(moves[i],board);//인형을 찾고
				if(dollsNum==0) continue;//아무일 일어나지 않음
				
				if(stack.isEmpty()) {
					stack.push(dollsNum);
					}
				else {
					if(dollsNum==stack.peek()) {//제거
						stack.pop();
						answer+=2;
					}else {
						stack.push(dollsNum);
					}
				}
			}
	        return answer;
	    }

		private int findDoll(int lineNum,int[][] board) {
			int dollsNum=0;
			int c=lineNum-1;
			for (int i = 0; i < board.length; i++) {
				if(board[i][c]!=0) {
					int temp=board[i][c];
					board[i][c]=0;
					return temp;
					}
			}
			return dollsNum;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
