import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_1793_타일링 {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<StringBuilder> memo=new ArrayList<>();
		memo.add(new StringBuilder().append('1')); //*** n이 0일 때에는 못놓는다는 경우의 수 한가지가 존재한다고 본다
		memo.add(new StringBuilder().append('1'));//1
		memo.add(new StringBuilder().append('3'));//2
		//n-1 에서 세로로 하나 두기 , n-2 에서 가로 두개, 2*2짜리 하나 두기 해서 총 두개  , 따라서 (n-1)의 경우의수 + (n-2)의 경우의 수 *2
		
		ArrayList<Integer> question=new ArrayList<>();
		int max=0;
		String str="";
		while((str=br.readLine())!=null) {
			if(str.equals(""))break;
			int now=Integer.parseInt(str.trim());
			question.add(now);
			max=Math.max(now, max);
		}
		//sum 함수 구현하자
		for (int i = 3; i <= max; i++) {//1,2는 초기값으로 구했기에 할 필요 없다
			StringBuilder temp=sum(memo.get(i-1),memo.get(i-2));
			memo.add(sum(temp,memo.get(i-2)));
		}
		
		for (int now:question) {
			System.out.println(memo.get(now).toString());
		}
	}

	private static StringBuilder sum(StringBuilder a, StringBuilder b) {
		StringBuilder result=new StringBuilder();
		boolean up=false;//올림 표시
		int aLen=a.length();
		int bLen=b.length();
		
		while(aLen>0||bLen>0) {
			int aNum=0;
			if(aLen>0) {
				aNum=a.charAt(aLen-1)-'0';
				aLen--;
			}
			int bNum=0;
			if(bLen>0) {
				bNum=b.charAt(bLen-1)-'0';
				bLen--;
			}
			
			int temp=aNum+bNum;
			if(up) temp++;//올림이 있다
			
			if(temp>9) {
				temp %=10;
				up=true;
			}else {
				up=false;
			}
			
			result.insert(0, temp);
		}
		if(up)result.insert(0, '1');//만약 마지막의 올림수가 있다면 1 추가
		
		return result;
	}

}
