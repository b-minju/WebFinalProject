package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.repository.search.SearchNoticeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Long>, SearchNoticeRepository {
    @Query("select n, w from Notice n left join n.writer w where n.num=:num")
    Object getNoticeWithWriter(@Param("num") Long num);

    @Query("select n, w "
            + "from Notice n left join n.writer w "
            + "where n.num=:num group by n, w")
    Object getNoticeByNum(@Param("num")Long num);
}
