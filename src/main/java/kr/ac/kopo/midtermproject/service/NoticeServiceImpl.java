package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.NoticeDTO;
import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import kr.ac.kopo.midtermproject.dto.PageResultDTO;
import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.entity.UserEntity;
import kr.ac.kopo.midtermproject.repository.NoticeRepository;
import kr.ac.kopo.midtermproject.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository repository;
    private final UserEntityRepository userEntityRepository;

    //    글 등록
    @Override
    public Long register(NoticeDTO dto) {
        UserEntity writer = userEntityRepository.findById(dto.getWriterId()).get();
        Notice notice = new Notice();
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        notice.setWriter(writer);
        repository.save(notice);

        return notice.getNum();
    }

    //    공지사항 목록 처리
    @Override
    public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], NoticeDTO> fn = (en -> entityToDTO((Notice) en[0], (UserEntity) en[1]));
        Page<Object[]> result = repository.searchPage(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable(Sort.by("num").descending()));
        return new PageResultDTO<>(result, fn);
    }

    //    특정 번호 게시물 조회
    @Override
    public NoticeDTO get(Long num) {
        Object result = repository.getNoticeByNum(num);
        Object[] arr = (Object[]) result;
        return entityToDTO((Notice) arr[0], (UserEntity) arr[1]);
    }


    //    특정 번호 게시물 삭제
    @Override
    @Transactional
    public void remove(Long num) {
        repository.deleteById(num);
    }

    //    공지사항 수정
    @Override
    @Transactional
    public void modify(NoticeDTO noticeDTO) {
        Notice notice = repository.findByNum(noticeDTO.getNum());
        notice.setTitle(noticeDTO.getTitle());
        notice.setContent(noticeDTO.getContent());

        repository.save(notice);
    }
}
