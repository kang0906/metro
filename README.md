# metro
seoul metro map


## 구현 고려사항 
- 열차 도착시간 정보 SubwayEdge.arrivalTimeTable 저장 방식 TreeSet 사용 고려
    - 간선의 개수만큼 생성, 유지 해야 하고 탐색이 자주 일어나는 만큼 공간복잡도, 시간복잡도의 절약을 위해 테스트 해본 후 TreeSet 사용을 고려