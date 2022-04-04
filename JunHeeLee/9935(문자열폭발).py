import sys

info = input() ##초기 문자열 
bomb = input() ##폭탄 문자열

check = bomb[-1] ##폭탄문자열의 마지막 문자
stack = []
limit = len(bomb) ##폭탄문자열의 길이

for s in info:
    stack.append(s) #for문을 돌며 스택에 문자를 쌓아줌
    if s==check: #스택에 쌓인 문자가 폭탄문자열의 마지막 문자랑 같은경우
        result = ''.join(stack[-limit:]) # 폭탄문자열인지 확인하기 위해 스택 뒤에서부터 폭탄문자열 길이만큼 join
        if result==bomb: ##폭탄문자열인 경우
            for i in range(limit): #그 길이만큼 stack에서 pop해줌
                stack.pop()
if len(stack)==0: #스택이 비어있는 경우
    print("FRULA")
else: #스택이 비어있지 않은 경우 join 하여 결과값 출력
    print(''.join(stack))