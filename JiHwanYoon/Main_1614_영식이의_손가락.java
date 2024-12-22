

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1614_영식이의_손가락 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long finger = Integer.parseInt(br.readLine());
		long cnt = Integer.parseInt(br.readLine());
		// 다친 손가락이 엄지면 cnt가 0, 1, 2, 3, 4, ...일 때 0, 8, 16, 24, 32, ...
		// 다친 손가락이 검지면 cnt가 0, 1, 2, 3, 4, ...일 때 1, 7, 9, 15, 17, ...
		// 다친 손가락이 중지면 cnt가 0, 1, 2, 3, 4, ...일 때 2, 6, 10, 14, 18, ...
		// 다친 손가락이 약지면 cnt가 0, 1, 2, 3, 4, ...일 때 3, 5, 11, 13, 19, ...
		// 다친 손가락이 새끼면 cnt가 0, 1, 2, 3, 4, ...일 때 4, 12, 20, 28, 36, ...
		if (finger == 1 || finger == 5) {
			System.out.println((finger - 1l) + cnt * 8l);
		} else {
			System.out.println((finger - 1l) * (cnt%2 == 0 ? 1l : -1l) + 8l * ((cnt+1l)/2));
		}
	}

}
