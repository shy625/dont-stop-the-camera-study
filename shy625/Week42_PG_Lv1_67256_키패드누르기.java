class Solution {
    
    private static final int KEYPAD_WIDTH = 3;
    
    private static int[] leftFingerPos;
    private static int[] rightFingerPos;
    
    public String solution(int[] numbers, String hand) {
        leftFingerPos = new int[] { 3, 0 };
        rightFingerPos = new int[] { 3, 2 };
        String main = "";
        
        if (hand.charAt(0) == 'l') {
            main = "L";
        } else {
            main = "R";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            int[] numPos = findNumPos(number);
            String clickFinger = selectFinger(numPos, main);
            sb.append(clickFinger);
            if (clickFinger.equals("L")) {
                leftFingerPos = numPos;
            } else {
                rightFingerPos = numPos;
            }
        }
        
        return sb.toString();
    }
    
    private int[] findNumPos(int number) {
        int[] pos = new int[2];
        if (number == 0) {
            pos[0] = 3;
            pos[1] = 1;
            return pos;
        }
        number -= 1;
        pos[0] = number / KEYPAD_WIDTH;
        pos[1] = number % KEYPAD_WIDTH;
        return pos;
    }
    
    private String selectFinger(int[] numPos, String main) {
        if (numPos[1] == 0) {
            return "L";
        } else if (numPos[1] == KEYPAD_WIDTH - 1) {
            return "R";
        } else {
            int leftDist = calculateDist(leftFingerPos, numPos);
            int rightDist = calculateDist(rightFingerPos, numPos);
            if (leftDist > rightDist) {
                return "R";
            } else if (leftDist < rightDist) {
                return "L";
            } else {
                return main;
            }
        }
    }
    
    private int calculateDist(int[] fingerPos, int[] numPos) {
        return Math.abs(fingerPos[0] - numPos[0]) + Math.abs(fingerPos[1] - numPos[1]);
    }
}

// 풀이 1
// 입력된 숫자에 대해 / 와 % 연산을 이용해 키패드 상 위치 계산
// 0은 하드 코딩
// 숫자 위치 확인, 적절한 손가락 찾기, 숫자와 손가락 사이 거리 구하기의 단계를 함수화하고 조합하여 풀이

// 다른 풀이 1
// 숫자 키패드는 0~9까지로 10개만 존재
// 개수가 많지 않으니 그냥 각 숫자별를 2차원 배열의 인덱스 값으로 하여 키패드 상의 위치를 미리 하드코딩 해놓는 것이 편리

// 다른 풀이 2
// 입력이 0~9의 정수로 제한 → case 문 활용
// if 문보다 좀 더 직관적이고 깔끔한 코드