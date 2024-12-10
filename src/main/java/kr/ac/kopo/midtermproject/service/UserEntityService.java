//package kr.ac.kopo.midtermproject.service;
//
//import kr.ac.kopo.midtermproject.entity.UserEntity;
//import kr.ac.kopo.midtermproject.repository.UserEntityRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class UserEntityService implements UserDetailsService {
//    private final UserEntityRepository userEntityRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("♥♥♥ UserEntityService : " + username);
//        Optional<UserEntity> result = userEntityRepository.findById(username);
//
//        if(result.isEmpty()) {
//            throw new UsernameNotFoundException("Check id");
//        }
//
//        UserEntity user = result.get();
//
//        log.info(user);
//
//        AuthUserDTO authUserDTO = new AuthUserDTO(
//                user.getId(),
//                user.getEmail(),
//                user.getPw(),
//                user.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
//        );
//
//        authUserDTO.setName(user.getName());
//
//        return authUserDTO;
//    }
//}
