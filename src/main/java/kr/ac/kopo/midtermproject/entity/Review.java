package kr.ac.kopo.midtermproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"notice", "user_entity"})
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private String text;

    public void changeText(String text){
        this.text = text;
    }

}