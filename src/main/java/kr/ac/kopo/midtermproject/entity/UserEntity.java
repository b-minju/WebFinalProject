package kr.ac.kopo.midtermproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String pw;
}
