package kr.ac.kopo.midtermproject.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @Builder.Default
    private Set<UserEntityRole> roleSet = new HashSet<>();

    public void addUserRole(UserEntityRole userEntityRole) {
        roleSet.add(userEntityRole);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
