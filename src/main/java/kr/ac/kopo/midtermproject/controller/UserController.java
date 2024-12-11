package kr.ac.kopo.midtermproject.controller;

import kr.ac.kopo.midtermproject.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserEntityService userEntityService;

//    @GetMapping("/checkId/{id}")
//    public ResponseEntity<Boolean> checkId(@PathVariable String id) {
//        boolean exists = userEntityService.checkId(id);
//        return ResponseEntity.ok(exists);
//    }
//
//    @PostMapping("/matchPw")
//    public ResponseEntity<Boolean> matchPw(@RequestBody)


}
