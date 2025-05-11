# 💻 코딩 테스트 스터디
## 📅 2025년 5월 3주차 문제

### 📌 월요일
- [[BOJ] 2661번 좋은수열](https://www.acmicpc.net/problem/2661) 
- [[PGS] 42579번 베스트앨범](https://school.programmers.co.kr/learn/courses/30/lessons/42579)

### 📌 화요일
- [[BOJ] 22944번 죽음의 비](https://www.acmicpc.net/problem/22944)  
- [[PGS] 81303번 표 편집](https://school.programmers.co.kr/learn/courses/30/lessons/81303)

### 📌 수요일
- [[BOJ] 1300번 K번째 수](https://www.acmicpc.net/problem/1300)
- [[BOJ] 1600번 말이 되고픈 원숭이](https://www.acmicpc.net/problem/1600)

### 📌 목요일
- [[BOJ] 15831번 준표와 조약돌](https://www.acmicpc.net/problem/15831)  
- [[PGS] 49189번 가장 먼 노드](https://school.programmers.co.kr/learn/courses/30/lessons/49189)

### 📌 금요일
- [[BOJ] 2616번 소형기관차](https://www.acmicpc.net/problem/2616)
- [[PGS] 92345번 사라지는 발판](https://school.programmers.co.kr/learn/courses/30/lessons/92345)


## ✅ 스터디 진행 방법
1. 현재 저장소를 자신의 깃허브 계정으로 **포크**한다.
2. 포크한 저장소를 로컬 환경에 **클론**한다.
```
git clone https://github.com/{GitHub ID}/coding-test-study.git
```
3. 자신의 깃허브 ID로 **브랜치를 생성**한다.
```
git checkout -b {GitHub ID}
```
4. 본인의 폴더에 문제 풀이 코드를 작성하고 커밋 메시지와 함께 **커밋**한다.
```
git add .
git commit -m "feat: BOJ / 1234번 / 실버1"
```
5. 작업이 완료되면, 포크한 개인 저장소의 **해당 브랜치로 푸시**한다.
```
git push origin {GitHub ID}
```
6. `main` 브랜치를 대상으로 포크한 저장소의 작업 브랜치에서 **Pull Request**를 생성한다.
7. 관리자가 PR을 확인한 뒤 `main` 브랜치에 **머지**한다.

## ✅ 파일 및 폴더 구조
- 형식: `GitHub ID/플랫폼/플랫폼_문제 번호`
  - 예시: `wda067/boj/BOJ_1234.java`

## ✅ 커밋 컨벤션
- 형식: `타입: 플랫폼 / 문제 번호 / 난이도`
  - 예시: `feat: BOJ / 1234번 / 실버1`
- 플랫폼 코드
  - BOJ: 백준
  - PGS: 프로그래머스
- 타입
  - feat: 새로운 문제 풀이 추가
  - fix: 기존 문제 풀이의 오류 수정
  - refactor: 코드 리팩토링
  - chore: 기타 변경 사항

## ✅ PR 컨벤션
- 제목: `이름 / 날짜 / 푼 문제 수`
  - 예시: `myeong-geun / 2025년 1월 1일 / 2문제`
- 내용은 자유롭게 작성
