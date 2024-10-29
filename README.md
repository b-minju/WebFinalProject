# WebMidtermProject
- 데이터베이스 연결
- 기본 페이지, 공지사항 페이지, 로그인 모달, 회원가입 페이지 완성 
- 아이디/비밀번호 찾기 페이지 완성
- 기본 페이지로 돌아가는 방식 변경 -> home.html을 새 창으로 여는 것 대신 현재 창을 닫음
- 공지사항 등록 페이지 완성 / User, BaseEntity, Notice entity 작성 / PageRequestDTO, PageResultDTO 추가 및 NoticeDTO 작성
- 공지사항 페이지 수정
- 공지사항 조회 페이지 완성
- service 계층 작성 시작 및 공지사항 관련 페이지들 수정
- 공지사항 페이지 기능 구현 완료, 테스트 성공
- 공지사항 페이지 기능 연결 시작
1. list.html은 접속이 되지만 공지사항 등록 페이지로 넘어갈 수 있는 것 빼고는 어떤 기능도 실행 불가
2. 조회는 정상적으로 됨. RegDate가 나오지 않음 - 데이터베이스에도 null로 입력됨. 날짜를 함께 저장하는지 확인 필요
3. Search, Cancel btn 이벤트 추가 필요
4. 페이징 처리에 있어서 PREV, NEXT가 나오지 않고 있음

- 공지사항 페이지 기능 연결
1. list html 접속 됨, 공지사항 조회, 공지사항 수정 및 저장 가능, 공지사항 삭제 가능
2. 등록 페이지로 넘어가지긴 하지만 등록이 안됨
2. Search, Cancel btn 이벤트 추가 필요 
3. 페이징 처리에 있어서 PREV, NEXT가 나오지 않고 있음

- 공지사항 페이지 기능 연결 2
1. list.html 접속 됨, 공지사항 조회, 공지사항 수정 및 저장, 공지사항 삭제 가능
2. 공지사항 등록 가능
3. 페이징 처리에 있어서 PREV, NEXT가 나오는 것 같긴 한데 페이징 처리가 안됨.
4. home.html에서 list.html로 접속할 수 있도록 수정해야 함

- 공지사항 페이지 기능 연결 3
1. user name과 id가 상충하는 부분들 해결
2. PREV, NEXT 페이징 처리 하기
3. home.html에서 list.html로 접속할 수 있도록 수정하기

** Search, Cancel btn 이벤트 추가하기
