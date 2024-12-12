package kr.ac.kopo.midtermproject.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.midtermproject.entity.UserEntity;
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

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestParam String name,
                                         @RequestParam String id,
                                         @RequestParam String email,
                                         @RequestParam String pw
                                         ) {
        if (name.isEmpty()) return ResponseEntity.badRequest().body("이름을 기입하세요.");
        if (id.isEmpty()) return ResponseEntity.badRequest().body("아이디를 기입하세요.");
        if (email.isEmpty()) return ResponseEntity.badRequest().body("이메일을 기입하세요.");
        if (pw.isEmpty()) return ResponseEntity.badRequest().body("비밀번호를 기입하세요.");

        if (userEntityService.checkId(id)) {
            userEntityService.register(name, id, email, pw);
            return ResponseEntity.ok("가입 완료");
        }
        else return ResponseEntity.status(401).body("id already exists");
    }

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
