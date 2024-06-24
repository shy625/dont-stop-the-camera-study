#include <iostream>
#include <vector>
using namespace std;
int N;
vector<pair<int, int>> ZeroPoint;
vector<vector<int>> Sudoku(9, vector<int>(9));

// 각각 가로,세로,사각 영역에서 특정 숫자가 이미 존재하는지 여부
vector<vector<bool>> RowCheck(9, vector<bool>(10, false));
vector<vector<bool>> ColCheck(9, vector<bool>(10, false));
vector<vector<bool>> SquareCheck(9, vector<bool>(10, false));

bool SudokuCheck(int idx)
{
    if (idx == N)
    {
        // 출력
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                cout << Sudoku[i][j];
            }
            cout << endl;
        }

        return true;
    }

    int r = ZeroPoint[idx].first;
    int c = ZeroPoint[idx].second;
    int SquareNum = ((r / 3) * 3) + (c / 3);
    for (int i = 1; i <= 9; i++)
    {
        if (!RowCheck[r][i] && !ColCheck[c][i] && !SquareCheck[SquareNum][i])
        {
            // 해당 위치 삽입 가능
            RowCheck[r][i] = true;
            ColCheck[c][i] = true;
            SquareCheck[SquareNum][i] = true;
            Sudoku[r][c] = i;
            if (SudokuCheck(idx + 1))
                return true;

            RowCheck[r][i] = false;
            ColCheck[c][i] = false;
            SquareCheck[SquareNum][i] = false;
            Sudoku[r][c] = 0;
        }
    }

    return false;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    for (int i = 0; i < 9; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < 9; j++)
        {
            Sudoku[i][j] = str[j] - '0';

            if (Sudoku[i][j] == 0)
            {
                ZeroPoint.push_back(make_pair(i, j));
            }
            else
            {
                RowCheck[i][Sudoku[i][j]] = true;
                ColCheck[j][Sudoku[i][j]] = true;
                // 사각형 영역 구하기
                // row가 3배수 영역 0,3,6
                // col이 나머지 0,1,2,영역

                int SquareNum = ((i / 3) * 3) + (j / 3);
                SquareCheck[SquareNum][Sudoku[i][j]] = true;
            }
        }
    }
    N = ZeroPoint.size();

    // cout << endl;
    SudokuCheck(0);
}