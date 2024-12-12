package kr.ac.kopo.midtermproject.service;

import kr.ac.kopo.midtermproject.dto.ReviewDTO;
import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.entity.Review;
import kr.ac.kopo.midtermproject.entity.UserEntity;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfNotice(Long mno);

    Long register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewNum);

    default Review dtoToEntity(ReviewDTO reviewDTO){

        Review review = Review.builder()
                .reviewnum(reviewDTO.getReviewnum())
                .notice(Notice.builder().num(reviewDTO.getNum()).build())
                .user(UserEntity.builder().id(reviewDTO.getUid()).build())
                .text(reviewDTO.getText())
                .build();

        return review;
    }

    default ReviewDTO entityToDto(Review review){

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewnum(review.getReviewnum())
                .num(review.getNotice().getNum())
                .uid(review.getUser().getId())
                .name(review.getUser().getName())
                .email(review.getUser().getEmail())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return reviewDTO;
    }
}