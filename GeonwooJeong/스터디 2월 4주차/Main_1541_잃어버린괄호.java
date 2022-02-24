import java.util.Scanner;

public class Main_1541_잃어버린괄호 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int sum = 0; // 결과 값을 저장할 변수
		
		String str = scann.next(); // 문자열을 입력받음
		
		String [] nums = str.split("-"); // -를 기준으로 문자열을 나눈다.
		
		String [] tmp = nums[0].split("\\+"); // 첫 -가 나오지 전의 값은 sum에 +로 더해준다.
		
		for (String st : tmp) {
			sum += Integer.parseInt(st); // 첫 -가 나오기 전의 값을 더해주는 과정
		}
		
		for (int i = 1; i < nums.length; i++) { // 첫 - 이후의 문자열들은
			String [] nums2 = nums[i].split("\\+"); // + 기준으로 나누어 모두 더한 뒤
			for (int j = 0; j < nums2.length; j++) {
				sum -= Integer.parseInt(nums2[j]); // sum에서 빼준다.
			}
		}
		
		System.out.println(sum); // 결과 출력
		
	}

}
