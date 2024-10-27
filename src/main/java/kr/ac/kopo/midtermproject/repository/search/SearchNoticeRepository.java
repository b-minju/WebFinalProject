package kr.ac.kopo.midtermproject.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchNoticeRepository {
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
