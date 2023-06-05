import java.util.Scanner;

public class Main_1541_잃어버린괄호_백준 {

	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		int total=0;
		char [] ch=scann.next().toCharArray();//문장 읽음
		//중간 어디 한 곳이라도 마이너스가 있다면 그 뒤는 전부 마이너스가 가능해진다
		
		int preNum=0;//이전에 읽어드린 숫자
		boolean thisIsMinus=false;//이게 true가 되면 그 뒤는 전부 마이너스 계산
		for (char c:ch) {
			if(c=='+'||c=='-') {
				if(thisIsMinus) {
					total-=preNum;
				}else {
					total+=preNum;
					if(c=='-')thisIsMinus=true;
				}
				preNum=0;
				continue;
			}
			
			//읽은 c가 숫자이다
			preNum *= 10;
			preNum += c-'0';
		}
		
		//마지막 숫자 읽기
		if(thisIsMinus) total-=preNum;
		else total+=preNum;
		System.out.println(total);
	}

}
