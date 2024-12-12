package kr.ac.kopo.midtermproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    //review num
    private Long reviewnum;

    //Notice num
    private Long num;

    //user id
    private String uid;
    //user name
    private String name;
    //user email
    private String email;

    private String text;

    private LocalDateTime regDate, modDate;
}