package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class UserEntityRepositoryTest {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            UserEntity user = UserEntity.builder()
                    .email("user" + i + "@kopo.ac.kr")
                    .pw("1234")
                    .name("username" + i)
                    .id("userid" + i)
                    .build();
            userEntityRepository.save(user);
        });
    }
}