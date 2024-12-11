package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.UserDTO;
import kr.ac.kopo.midtermproject.entity.UserEntity;
import kr.ac.kopo.midtermproject.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService{
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public boolean checkId(String id) {
        return !userEntityRepository.existsById(id);
    }

    @Override
    public boolean matchPw(String pw, String confirmPw) {
        return pw.equals(confirmPw);
    }

    @Override
    public void register(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPw(userDTO.getPw());
    }

    @Override
    public boolean logIn(String id, String pw) {
        if (userEntityRepository.findById(id).isPresent()) {
            UserEntity requestUser = userEntityRepository.findById(id).get();
            String encodedPw = requestUser.getPw();

            if (passwordEncoder.matches(pw, encodedPw)) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }
}
