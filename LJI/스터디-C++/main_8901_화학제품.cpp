#include<iostream>
#include<cmath>
using namespace std;

int T, A, B, C, AB, BC, CA;
main(){
    cin >> T;

    //testCase
    for (int t = 0; t < T; t++)
    {   
        //값 입력
        cin >> A >> B >> C >> AB >> BC >> CA;

        //그냥 모든 경우의 수를 따져야하나?
        int MAX = 0;//금액 최대

        //AB의 양을 정하고 BC의 양을 정하면 CA는 무조건 최대값?
        
        int ABMAX =min(A,B) ;//만들 수 있는 최대 AB

        for (int i = 0; i <=ABMAX; i++)//처음
        {
            int firA = A-i;
            int firB = B-i;
            int firC = C;

            int cntAB = i;
            
            //AB가 정해진 상태에서 BC정하기
            int BCMAX = min(firB, firC);
            for (int j = 0; j <= BCMAX; j++)
            {
                int secA = firA;
                int secB = firB - j;
                int secC = firC - j;

                int cntBC = j;

                //남은 CA양은 무조건 최대
                int cntCA = min(secA, secC);

                MAX = max(MAX, cntAB * AB + cntBC * BC + cntCA * CA);
            }
            
        }
        cout << MAX << endl;
    }
    
}
