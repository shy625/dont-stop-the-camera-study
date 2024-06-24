class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        
        int[][] newLock = new int[3 * n][3 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[n + i][n + j] = lock[i][j];
            }
        }
        
        for (int k = 0; k < 4; k++) {
            key = rotateKey(key);
            for (int i = 1; i < 2 * n; i++) {
                for (int j = 1; j < 2 * n; j++) {
                    for (int r = 0; r < m; r++) {
                        for (int c = 0; c < m; c++) {
                            newLock[i + r][j + c] += key[r][c];
                        }
                    }
                    if (checkLockOpen(newLock)) {
                        return true;
                    }
                    for (int r = 0; r < m; r++) {
                        for (int c = 0; c < m; c++) {
                            newLock[i + r][j + c] -= key[r][c];
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private int[][] rotateKey(int[][] key) {
        int m = key.length;
        int[][] tmp = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                tmp[j][m - 1 - i] = key[i][j];
            }
        }
        return tmp;
    }
    
    private boolean checkLockOpen(int[][] newLock) {
        int n = newLock.length / 3;
        boolean result = true;
        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                if (newLock[i][j] != 1) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}

// 풀이 1
// 열쇠를 회전 & 이동해서 자물쇠의 모든 홈을 채워야 함
// 구현의 편의를 위해 기존 자물쇠를 중앙에 두고 자물쇠의 각 변을 3배로 확대한 새 배열을 선언
// 열쇠 회전 -> 열쇠가 자물쇠 일부 또는 전체와 겹치도록 이동
// -> 열쇠와 자물쇠의 각 값들을 더함 -> 합친 값이 모두 1인지 확인
// -> 모두 1이면 true 반환 및 종료 / 1이 아닌 값이 있으면 다시 반복
// -> 모든 회전과 이동 반복 시에도 열 수 없다면 false 반환