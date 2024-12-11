package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.UserDTO;

public interface UserEntityService {
//    회원가입
    boolean checkId(String id);
    boolean matchPw(String pw, String confirmPw);
    void register(UserDTO userDTO);

//    로그인
    boolean logIn(String id, String pw);
//    비밀번호 찾기
//    아이디 찾기
}
