class Solution {
    public String[] solution(int n, int[] map1, int[] map2) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = "";
        }
        
        // 풀이 1
        for (int i = 0; i < n; i++) {
            int row1 = map1[i];
            int row2 = map2[i];
            for (int j = 0; j < n; j++) {
                int val1 = row1 & 1;
                int val2 = row2 & 1;
                if (val1 == 1 || val2 == 1) {
                    result[i] = '#' + result[i];
                } else {
                    result[i] = ' ' + result[i];
                }
                row1 = row1 >> 1;
                row2 = row2 >> 1;
            }
        }

        // 풀이 2
        // for (int i = 0; i < n; i++) {
        //     int row = map1[i] | map2[i];
        //     for (int j = 0; j < n; j++) {
        //         if ((row & 1) == 1) {
        //             result[i] = "#" + result[i];
        //         } else {
        //             result[i] = " " + result[i];
        //         }
        //         row = row >> 1;
        //     }
        // }
        
        return result;
    }
}

// 풀이 1
// 두 지도의 각 행에서 각 열의 값을 비트 연산을 통해 구하기
// 구한 두 값이 0, 1인지 확인하여 벽 또는 공백 추가

// 풀이 2
// 두 지도의 각 행을 바로 비트or 연산
// 연산 결과의 각 비트값이 0, 1인지 확인하여 벽 또는 공백 추가