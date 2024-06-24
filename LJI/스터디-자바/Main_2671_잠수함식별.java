import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_2671_잠수함식별 {

	// https://hbase.tistory.com/160 
	// 정규 표현식 참고
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		String str=scann.next();
		Pattern pattern = Pattern.compile("(100+1+|01)+");
		
		Matcher matcher=pattern.matcher(str);
		
		System.out.println(matcher.matches()?"SUBMARINE":"NOISE");
	}
}
