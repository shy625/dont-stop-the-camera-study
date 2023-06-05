
public class Solution_키패드누르기 {

	class Solution {
	    public String solution(int[] numbers, String hand) {
	        String answer = "";
	        //키패드 배열 선언
	        char[][] pad=new char[][] {
	        	{'1','2','3'},
	        	{'4','5','6'},
	        	{'7','8','9'},
	        	{'*','0','#'}
	        };
	        
	        //왼손 오른손 좌표 설정
	        int[] Left=new int[] {3,0};
	        int[] Right=new int[] {3,2};
	        
	        
	        //작성 시작
	        StringBuilder sb=new StringBuilder();
	        for(int now:numbers) {
	        	if(now==1||now==4||now==7) {//왼손
	        		sb.append("L");
	        		Left[1]=0;
	        		if(now==1) {
	        			Left[0]=0;
	        		}else if(now==4) {
	        			Left[0]=1;
	        		}else {
	        			Left[0]=2;
	        		}
	        	}
	        	else if(now==3||now==6||now==9) {//오른손
	        		//int now=now;
	        		sb.append("R");
	        		Right[1]=2;
	        		if(now==3) {
	        			Right[0]=0;
	        		}else if(now==6) {
	        			Right[0]=1;
	        		}else {
	        			Right[0]=2;
	        		}
	        	}
	        	else {//중앙
	        		int[][] pointer=new int[][] {
	        			{0,1},
	        			{1,1},
	        			{2,1},
	        			{3,1}
	        		};
	        		
	        		int num=0;
	        		if(now==2) {//0,1
	        			num=0;
	        		}else if(now==5) {//1,1
	        			num=1;
	        		}else if(now==8) {//2,1
	        			num=2;
	        		}else {//3,1
	        			num=3;
	        		}
	        		
	        		int leftDis=Math.abs(pointer[num][0]-Left[0])+Math.abs(pointer[num][1]-Left[1]);
        			int rightDis=Math.abs(pointer[num][0]-Right[0])+Math.abs(pointer[num][1]-Right[1]);
        			
        			if(leftDis<rightDis) {
        				sb.append('L');
        				Left[0]=pointer[num][0];
        				Left[1]=pointer[num][1];
        			}else if(leftDis>rightDis) {
        				sb.append('R');
        				Right[0]=pointer[num][0];
        				Right[1]=pointer[num][1];
        			}else {
        				if(hand.equals("left")) {
        					sb.append('L');
	        				Left[0]=pointer[num][0];
	        				Left[1]=pointer[num][1];
        				}else {
        					sb.append('R');
	        				Right[0]=pointer[num][0];
	        				Right[1]=pointer[num][1];
        				}
        			}
	        	}
	        }
	        answer=sb.toString();
	        return answer;
	    }
	}
}
