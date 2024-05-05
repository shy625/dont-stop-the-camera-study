

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14718_용감한_용사_진수 {
	// 병사의 힘, 민첩, 지능을 나타내는 클래스
	static class Soldier {
		int s, d, i;
		public Soldier(int s, int d, int i) {
			this.s = s;
			this.d = d;
			this.i = i;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Soldier[] soldiers = new Soldier[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			soldiers[n] = new Soldier(s, d, i);
		}
		int min = Integer.MAX_VALUE; // 필요한 스탯의 최소치
		// 브루트포스 알고리즘을 활용
		// 우선 병사들을 지능에 대해 오름차순으로 정렬한 뒤,
		// 한 병사의 힘과 다른 병사의 민첩으로 가능한 스탯의 경우 각각에 대해
		// 지능이 낮은 병사부터 차례대로 해당 병사의 힘과 민첩을 이길 수 있는지 판단한다.
		// 그러다가 K명의 병사에 대해 힘과 민첩으로만 이길 수 있는 순간 그 병사의 지능을 스탯으로 하면 
		// 이전에 힘과 민첩으로만 이겼던 병사들 모두를 지능으로도 이길 수 있다.
		Arrays.sort(soldiers, (x, y) -> Integer.compare(x.i, y.i));
		for (int x = 0; x < N; x++) {
			int s = soldiers[x].s;
			for (int y = 0; y < N; y++) {
				int d = soldiers[y].d;
				int cnt = 0; // 힘과 민첩으로 이길 수 있는 병사의 수
				for (int z = 0; z < N; z++) {
					if (s >= soldiers[z].s && d >= soldiers[z].d) {
						cnt++;
					}
					// 힘과 민첩으로 이길 수 있는 병사의 수가 K명이 되면 현재 병사의 지능을 스탯으로 하면 
					// 이전에 힘과 민첩으로만 이겼던 병사들을 모두 이길 수 있다.
					if (cnt == K) {
						min = Math.min(s + d + soldiers[z].i, min);
						break;
					}
				}
			}
		}
		System.out.println(min);
	}

}
