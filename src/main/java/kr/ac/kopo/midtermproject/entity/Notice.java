package kr.ac.kopo.midtermproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Notice extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Setter
    private String title;
    @Setter
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private UserEntity writer;
}
