

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1041_주사위 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int[] nums = new int[6];
		for (int i = 0; i < 6; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
		// 그리디 알고리즘 활용
		// N*N*N 크기의 정육면체를 만들면, 한쪽 면만 보이는 주사위, 두쪽 면만 보이는 주사위, 세쪽 면만 보이는 주사위가 있다.
		// 한쪽 면만 보이는 주사위 개수는 총 5*(N-2)*(N-2)(5개의 면 중앙 (N-2)*(N-2) 정사각형) + 4*(N-2)(아래쪽 면과 접하는 모서리 부분 (N-2)개 * 4)
		// 두쪽 면만 보이는 주사위 개수는 총 8*N-12(아래쪽 면과 접하지 않는 모서리 부분 (N-2)개 * 8 + 아래쪽 면과 접하는 꼭짓점 부분 4개)
		// 세쪽 면만 보이는 주사위 개수는 총 4개(아래쪽 면과 접하는 꼭짓점 부분 4개)
		long min1 = Integer.MAX_VALUE;
		long min2 = Integer.MAX_VALUE;
		long min3 = Integer.MAX_VALUE;
		// 한쪽 면만 보이는 주사위의 경우 가장 작은 값만 보이도록 하면 된다.
		for (int i = 0; i < 6; i++) {
			min1 = Math.min(min1, nums[i]);
		}
		// 두쪽 면 이상 보이는 주사위의 경우 주사위 방향에 주의해 최솟값을 구한다.
		min2 = Math.min(min2, nums[0]+nums[1]+nums[2]);
		min2 = Math.min(min2, nums[0]+nums[1]+nums[3]);
		min2 = Math.min(min2, nums[0]+nums[3]+nums[4]);
		min2 = Math.min(min2, nums[0]+nums[2]+nums[4]);
		min2 = Math.min(min2, nums[5]+nums[1]+nums[2]);
		min2 = Math.min(min2, nums[5]+nums[1]+nums[3]);
		min2 = Math.min(min2, nums[5]+nums[3]+nums[4]);
		min2 = Math.min(min2, nums[5]+nums[2]+nums[4]);
		min3 = Math.min(min3, nums[0]+nums[1]);
		min3 = Math.min(min3, nums[0]+nums[2]);
		min3 = Math.min(min3, nums[0]+nums[3]);
		min3 = Math.min(min3, nums[0]+nums[4]);
		min3 = Math.min(min3, nums[1]+nums[2]);
		min3 = Math.min(min3, nums[1]+nums[3]);
		min3 = Math.min(min3, nums[1]+nums[5]);
		min3 = Math.min(min3, nums[2]+nums[4]);
		min3 = Math.min(min3, nums[2]+nums[5]);
		min3 = Math.min(min3, nums[3]+nums[4]);
		min3 = Math.min(min3, nums[3]+nums[5]);
		min3 = Math.min(min3, nums[4]+nums[5]);
		long sum = 0L;
		if (N == 1) {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 6; i++) {
				max = Math.max(max, nums[i]);
				sum += nums[i];
			}
			sum -= max;
		} else {
			sum = min1*5*(N-2)*(N-2)+min1*4*(N-2)+min3*(8*N-12)+min2*4;
		}
		System.out.println(sum);
	}

}
