package kr.ac.kopo.midtermproject.repository;

import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.entity.Review;
import kr.ac.kopo.midtermproject.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByNotice(Notice notice);

    @Modifying
    @Query("delete from Review rw where rw.user=:user")
    void deleteByUser(UserEntity user);

}
