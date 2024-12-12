package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.entity.UserEntity;
import kr.ac.kopo.midtermproject.entity.UserEntityRole;
import kr.ac.kopo.midtermproject.repository.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityServiceImplTest {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @Rollback(value = false)
    public void register() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            UserEntity user = new UserEntity();
            user.setId("user" + i);
            user.setName("사용자" + i);
            user.setEmail("user"+i+"@kopo.ac.kr");
            String encodedPw = passwordEncoder.encode("1234");
            user.setPw(encodedPw); // 비밀번호 암호화

            user.addUserRole(UserEntityRole.USER);

            if (i > 20) {
                user.addUserRole(UserEntityRole.MANAGER);
            }

            if (i > 25) {
                user.addUserRole(UserEntityRole.ADMIN);
            }

            userEntityRepository.save(user);
        });
    }
}