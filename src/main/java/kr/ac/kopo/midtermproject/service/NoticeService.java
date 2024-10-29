package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.NoticeDTO;
import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import kr.ac.kopo.midtermproject.dto.PageResultDTO;
import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.entity.UserEntity;

public interface NoticeService {
//    글 등록
    Long register(NoticeDTO dto);

//    공지사항 목록 처리
    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

//    특정 번호 게시물 조회
    NoticeDTO get(Long num);

//    특정 번호 게시물 삭제
    void remove(Long num);

//    공지사항 수정
    void modify(NoticeDTO noticeDTO);

    //    Entity를 DTO로 변환하는 메소드
    default NoticeDTO entityToDTO(Notice notice, UserEntity user){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .num(notice.getNum())
                .title(notice.getTitle())
                .content(notice.getContent())
                .regDate(notice.getRegDate())
                .modDate(notice.getModDate())
                .writerId(user.getName())
                .build();

        return noticeDTO;
    }

    //    DTO를 Entity로 변환하는 메소드(
    default Notice dtoToEntity(NoticeDTO dto){
        UserEntity user = UserEntity.builder()
                .id(dto.getWriterId())
                .build();

        Notice notice = Notice.builder()
                .num(dto.getNum())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(user)
                .build();
        return notice;
    }
}
