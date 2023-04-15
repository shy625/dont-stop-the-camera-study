/**
 * BJ 11578 팀원 모집
 * @ prob : https://www.acmicpc.net/problem/11578
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Week13_BJ_11578_팀원모집 {

    static int N, M;
    static List<Integer>[] students;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 문제 수
        M = Integer.parseInt(st.nextToken());   // 학생 수

        students = new ArrayList[M];    // 각각의 학생들이 풀 수 있는 문제 리스트
        for (int i = 0; i < M; i++) {
            students[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int O = Integer.parseInt(st.nextToken());
            for (int j = 0; j < O; j++) {
                students[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        min = 100;  // 1 <= M <= 10
        subset(0, 0, new boolean[M]);

        System.out.println(min > 10 ? -1 : min);

        br.close();
    }

    // 학생들을 팀원으로 선택하는 경우를 구하기 위해 부분집합 수행
    private static void subset(int idx, int selectedCount, boolean[] isSelected) {
        if (idx == M) {     // 마지막 원소까지 고려 완료
            Set<Integer> probSet = new HashSet<>();
            for (int i = 0; i < M; i++) {
                if (isSelected[i]) {    // 선택된 학생이 풀 수 있는 문제 리스트를 Set에 저장
                    probSet.addAll(students[i]);
                }
            }
            if (probSet.size() == N) {  // 모든 문제를 풀 수 있는 경우
                min = Math.min(min, selectedCount);
            }
            return;
        }
        isSelected[idx] = true;
        subset(idx + 1, selectedCount + 1, isSelected);
        isSelected[idx] = false;
        subset(idx + 1, selectedCount, isSelected);
    }
}

// 풀이 1
// 부분집합과 Set을 이용
// 부분집합을 이용해 팀원을 선택하는 모든 경우를 확인
// 선택된 팀원이 풀 수 있는 문제들을 모두 Set에 넣은 뒤 Set의 크기가 문제의 개수와 같은지 확인
// 모든 문제를 풀 수 있는 팀 구성 중 가장 작은 수를 출력

// 다른 풀이 1
// Set 대신에 비트마스킹 이용 가능

// 다른 풀이 2
// 부분집합 대신에 조합 이용 가능
// 1명을 뽑는 조합, 2명을 뽑는 조합, ... , N명을 뽑는 조합을 진행하며 모든 문제를 풀 수 있는지 확인
// 적은 수를 뽑는 경우부터 조합 진행 시, 부분집합처럼 모든 경우를 다 확인할 필요없이 문제를 풀 수 있는 경우가 나오면 바로 출력 가능