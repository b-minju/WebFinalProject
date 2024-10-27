package kr.ac.kopo.midtermproject.repository;

import jakarta.transaction.Transactional;
import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class NoticeRepositoryTest {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    public void insertNotices(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            UserEntity user = userEntityRepository.findById("userid" + i)
                    .orElseThrow(() -> new RuntimeException("User not found: userid" + i));

            Notice notice = Notice.builder()
                    .title("Title " + i)
                    .content("content" + i)
                    .writer(user)
                    .build();

            noticeRepository.save(notice);
        });
    }

    @Transactional
    @Test
    public void testRead1(){
        Optional<Notice> result = noticeRepository.findById(5L);
        Notice notice = result.get();

        System.out.println(notice);
        System.out.println(notice.getWriter());
    }

    @Test
    public void testRead2(){
        Object result = noticeRepository.getNoticeWithWriter(10L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testRead3(){
        Object result = noticeRepository.getNoticeByNum(100L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("num").descending().and(Sort.by("title").ascending()));
        noticeRepository.searchPage("t", "10", pageable);
    }
}