import java.util.*;

class Solution {
    
    private static class Stage implements Comparable<Stage> {
        int num;
        int tryCount;
        int failCount;
        double failRate;
        
        public Stage(int num, int tryCount, int failCount, double failRate) {
            this.num = num;
            this.tryCount = tryCount;
            this.failCount = failCount;
            this.failRate = failRate;
        }
        
        public void calculateFailRate() {
            if (tryCount == 0) {
                failRate = 0.0;
            } else {
                failRate = (double) failCount / tryCount;
            }
        }
        
        @Override
        public int compareTo(Stage s) {
            if (this.failRate == s.failRate) {
                return Integer.compare(this.num, s.num);
            }
            return Double.compare(s.failRate, this.failRate);
        }
        
    }
    
    public int[] solution(int N, int[] stages) {
        Stage[] stageArr = new Stage[N];
        for (int i = 0; i < N; i++) {
            stageArr[i] = new Stage(i + 1, 0, 0, 0.0);
        }
        
        for (int userPos : stages) {
            if (userPos == N + 1) {
                for (int i = 0; i < stageArr.length; i++) {
                    stageArr[i].tryCount++;
                }
            } else {
                for (int i = 0; i < userPos; i++) {
                    stageArr[i].tryCount++;
                }
                stageArr[userPos - 1].failCount++;
            }
        }
        
        for (Stage stage : stageArr) {
            stage.calculateFailRate();
        }
        
        Arrays.sort(stageArr);
        
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = stageArr[i].num;
        }
        
        return result;
    }
}

// 풀이 1
// 각 스테이지별 구해야하는 정보 → 스테이지 번호, 도전자 수, 실패자 수, 실패율
// 각각을 별도의 배열로 생성하기 or 위 정보들을 가지는 클래스 생성하기
// 실패율로 정렬한 뒤, 정렬된 순서대로 스테이지 번호를 반환해야 하므로 실패율 정렬 시 번호가 같이 따라갈 수 있도록 클래스로 선언하는 것이 편리
// 주어진 각 유저의 스테이지 위치 정보를 받아 해당 스테이지 이전은 모두 1씩 증가 → 도전자 수
// 해당 스테이지만 증가 → 실패자 수
// 각 스테이지의 값들이 모두 구해지면 실패율을 구하고 정렬하여 출력

// 다른 풀이 1
// 각 스테이지별 실패자 수만 저장
// 도전자 수 = 각 스테이지 별로 전체 사람 수 - 이전 스테이지 실패자 수
// → 각 스테이지 이전의 모든 스테이지에 대해서 도전자 수를 증가시키기 위해 반복을 돌 필요 X