#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

int dr[] = {0, 1, 1, 0};
int dc[] = {0, 0, 1, 1};
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<vector<int>> origin(N, vector<int>(N, 0));
    for (int i = 0; i < N; i++)
    {

        for (int j = 0; j < N; j++)
        {
            cin >> origin[i][j];
        }
    }

    while (N != 1)
    {

        N /= 2;

        vector<vector<int>> next(N, vector<int>(N, 0));
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                int r = i * 2;
                int c = j * 2;
                vector<int> arr(4);
                for (int d = 0; d < 4; d++)
                {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    arr[d] = origin[nr][nc];
                }
                sort(arr.begin(), arr.end());
                next[i][j] = arr[2];
            }
        }

        origin = next;
    }

    cout << origin[0][0];
}