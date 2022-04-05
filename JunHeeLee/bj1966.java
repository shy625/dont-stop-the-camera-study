package algo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class bj1966 {
	static int N;
	static int T;
	static int pick;
	static int[] nums;
	static int maxNum = -1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		T = scan.nextInt();
		for(int test=0; test<T;test++) {
			N = scan.nextInt();
			pick = scan.nextInt();
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<Boolean> check = new ArrayList<>();
			for(int i=0;i<N;i++) {
				int num = scan.nextInt();
				list.add(num);
				check.add(false);
			}
			check.set(pick, true);
			int cnt = 1;
			while(true) {
				maxNum = Collections.max(list);
				if(list.get(0) == maxNum) {
					if(check.get(0)==true) {
						System.out.println(cnt);
						break;
					}
					else {
						list.remove(0);
						check.remove(0);
						cnt++;
					}
					
				}
				else {
					Collections.rotate(list, -1);
					Collections.rotate(check, -1);
				}
			}
		}
	}
}
