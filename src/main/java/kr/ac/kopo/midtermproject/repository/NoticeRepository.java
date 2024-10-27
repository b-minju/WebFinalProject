package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
