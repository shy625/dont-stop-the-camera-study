#include<iostream>
#include<queue>
using namespace std;

main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N,M;
    cin >> N >> M;

    int cost[N];//공간당 요금 단위비용
    int carW[M+1];//차의 무게
    
    for (int i = 0; i < N; i++)
    {
        cin >> cost[i];
    }
    
    for (int i = 1; i <=M; i++)
    {
        cin >> carW[i];
    }

    //주차 시뮬

    //주차 공간은 우선순위큐
    //어떤차가 어떤 위치에 주차되어있는지는 차량이 랜덤으로 들어온다면 map을 썼겠지만 2*M개 들어오니 그냥 배열 쓰자
    //주차 순서표는 큐
    
    //자리가 없다면 대기 처리

    //주차공간
    priority_queue<int, vector<int>, greater<int>> pq;
    for (int i = 0; i < N; i++)
    {
        pq.push(i);
    }
    //주차 위치
    int parking[M + 1]={0};//i번호의 차는 parking[i]위치에 보관
    //주차 순서표
    queue<int> que;
    queue<int> waitingQue;//진짜 대기줄
    for (int i = 0; i < 2*M; i++)
    {
        int temp;
        cin >> temp;
        que.push(temp);
    }

    int totalCost = 0;
    
    while (!que.empty()||!waitingQue.empty())
    {
        //명령 리스트
        if(!que.empty()){
        int order = que.front();
        que.pop();

        if(order>0){//차량 주차
            waitingQue.push(order);//대기 줄에 차 넣기

        }else{//차량빼기
            //주차한 곳 알아내기
            order = abs(order);
            int parkingPlace = parking[order];

            //주차한 곳 빈자리되니 다시 pq에 넣어주기
            pq.push(parkingPlace);
        }
        }
        //주차 시도
        //주차공간이 없거나 주차할 차가 없다면?? 패스
            if(pq.empty()||waitingQue.empty())continue;
            //주차 가능한 위치 꺼내기
            int parkingPlace = pq.top();
            pq.pop();

            //주차할 차 꺼내기
            int car = waitingQue.front();
            waitingQue.pop();
            //차량 주차 표시
            parking[car] = parkingPlace;
            //비용계산
            int parkingCost = cost[parkingPlace] * carW[car];
            //비용 더해주기
            totalCost += parkingCost;
    }

    cout << totalCost;
}