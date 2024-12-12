package kr.ac.kopo.midtermproject.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.midtermproject.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserEntityService userEntityService;

//    @GetMapping("/checkId/{id}")
//    public ResponseEntity<Boolean> checkId(@PathVariable String id) {
//        boolean exists = userEntityService.checkId(id);
//        return ResponseEntity.ok(exists);
//    }
//
//    @PostMapping("/matchPw")
//    public ResponseEntity<Boolean> matchPw(@RequestBody)

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id,
                                        @RequestParam String pw,
                                        HttpSession session) {
        boolean success = userEntityService.logIn(id, pw);
        if (success) {
            session.setAttribute("userId", id); // 세션에 사용자 ID 저장
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 제거
        return ResponseEntity.ok("로그아웃 성공");
    }
}
