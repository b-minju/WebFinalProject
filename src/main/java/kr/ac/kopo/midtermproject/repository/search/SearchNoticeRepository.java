package kr.ac.kopo.midtermproject.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchNoticeRepository {
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
