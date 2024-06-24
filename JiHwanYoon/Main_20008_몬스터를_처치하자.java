

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20008_몬스터를_처치하자 {
	// 스킬의 대기 시간과 대미지를 저장하는 클래스
	static class Skill {
		int coolTime, damage;
		public Skill(int coolTime, int damage) {
			this.coolTime = coolTime;
			this.damage = damage;
		}
	}
	static int N, HP, min;
	static Skill[] skills;
	static int[] times;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		HP = Integer.parseInt(st.nextToken());
		skills = new Skill[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int coolTime = Integer.parseInt(st.nextToken());
			int damage = Integer.parseInt(st.nextToken());
			skills[i] = new Skill(coolTime, damage);
		}
		times = new int[N]; // 각 스킬마다 다음으로 스킬을 사용할 수 있는 시간을 저장하는 배열
		min = Integer.MAX_VALUE; // 몬스터를 처치하는 데 걸리는 최소 시간
		dfs(HP, 0);
		System.out.println(min);
	}
	// 브루트포스 알고리즘을 활용해 몬스터를 처치하는 데 걸리는 최소 시간을 구한다.
	private static void dfs(int curHP, int time) {
		// 몬스터를 처치한 경우
		if (curHP <= 0) {
			min = Math.min(min, time);
			return;
		}
		// 아무 스킬도 못 쓰는지를 확인하는 변수
		boolean canUseSkill = false;
		for (int i = 0; i < N; i++) {
			if (times[i] > time) continue; // 현재 스킬을 쓸 수 없는 경우
			canUseSkill = true; // 스킬을 사용한다.
			Skill skill = skills[i];
			int prev = times[i]; // 백트래킹을 활용하기 위해 현재 times[i]에 있는 숫자 저장
			times[i] = time + skill.coolTime;
			dfs(curHP - skill.damage, time+1); // 스킬을 사용한다.
			times[i] = prev;
		}
		// 아무 스킬도 사용하지 못하는 경우 그냥 넘어간다.
		if (!canUseSkill) dfs(curHP, time+1);
	}

}
