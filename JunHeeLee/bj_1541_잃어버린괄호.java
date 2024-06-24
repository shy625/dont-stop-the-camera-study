package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("\\-");// (-)를 기준으로 식을 쪼갬
		int answer = 0; // 계산값담을 변수
		String[] temp = input[0].split("\\+"); //첫 -가 나오기 전까지는 더해줌
		for(int i=0;i<temp.length;i++) { //양수와 -,+만 들어온다 했으므로 처음들어온 식을 -기준으로 쪼갰을때 input[0]는 무조건 더해야하는수
			answer += Integer.parseInt(temp[i]);
		}
		for(int i=1;i<input.length;i++) { //그 뒤로는 전부 -기준으로 나눠져있는 수식이므로 전부 빼주면 됨
			String[] temp2 = input[i].split("\\+");
			for(int j=0;j<temp2.length;j++) {
				answer -= Integer.parseInt(temp2[j]);
			}
		}
		System.out.println(answer);
	}

}
