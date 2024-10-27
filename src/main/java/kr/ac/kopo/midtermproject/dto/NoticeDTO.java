package kr.ac.kopo.midtermproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Long num;
    private String title;
    private String content;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}

