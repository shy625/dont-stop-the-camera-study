import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// -가 나오면 뒤에는 다 빼준다

public class Main_1541_잃어버린괄호 {
	static ArrayList<Integer> nums;
	static ArrayList<Character> operator;
	static boolean checkMinus;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String formula = br.readLine();
		String num = "";
		int result = 0;
		for (int i = 0; i <= formula.length(); i++) {
			if(i== formula.length() || formula.charAt(i) == '-' || formula.charAt(i) == '+' ) {
				if(!checkMinus){ // -가 나오기 전에는 모든 값을 다 더해 준다..
					result += Integer.valueOf(num);
					num = "";
				}
				else { // -가 나오면 뒤에 값들 모두 빼 준다. 
					result -= Integer.valueOf(num);
					num = "";
				}
				if(i<formula.length() && formula.charAt(i) == '-') checkMinus = true;
			}
			else {
				num = num+formula.charAt(i);
			}
		}
		System.out.println(result);
	}
	
}
