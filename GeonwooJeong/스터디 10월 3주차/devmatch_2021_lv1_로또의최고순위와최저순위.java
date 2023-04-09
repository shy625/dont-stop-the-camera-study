import java.util.*;

class devmatch_2021lv1로또의최고순위와최저순위 {
    // 1. lottos에 0의 개수를 구한다.
    // 2. win_nums를 먼저 탐색하고, HashMap 안에 담는다. (Integer, Boolean)
    // 3. lottos를 확인하고, 0이 아닌 숫자에 대해 HashMap에 있으면 true로 변환한다.
    // 4. HashMap을 탐색하면서 true인 것들의 개수를 구한다.
    // 5. 최고 순위와 최저 순위를 계산한다.

    public int[] solution(int[] lottos, int[] win_nums) {
        int min = 0;
        int custom = 0;

        for(int n : lottos) {
            if(n == 0) {
                custom++;
            }
        }

        HashMap<Integer, Boolean> map = new HashMap<>();
        for(Integer n : win_nums) {
            map.put(n, false);
        }

        for(int n : lottos) {
            if(n != 0 && map.containsKey(n)) {
                map.put(n, true);
            }
        }

        for(Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            boolean correct = entry.getValue();
            if(!correct) continue;
            else min++;
        }

        int max = min + custom;
        int ansMax = 0;
        int ansMin = 0;

        if(max <= 1) {
            ansMax = 6;
        } else {
            ansMax = 7-max;
        }

        if(min <= 1) {
            ansMin = 6;
        } else {
            ansMin = 7-min;
        }

        int[] answer = {ansMax, ansMin};
        return answer;
    }
}
