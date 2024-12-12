package kr.ac.kopo.midtermproject.controller;

import kr.ac.kopo.midtermproject.dto.ReviewDTO;
import kr.ac.kopo.midtermproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 댓글 목록 조회
    @GetMapping("/{num}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("num") Long num){
        log.info("--------------list---------------");
        log.info("NUM: " + num);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfNotice(num);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    // 댓글 추가
    @PostMapping("/{num}")
    public ResponseEntity<Long> addReview(@PathVariable("num") Long num, @RequestBody ReviewDTO noticeReviewDTO){
        log.info("--------------add noticeReview---------------");
        log.info("reviewDTO: " + noticeReviewDTO);

        noticeReviewDTO.setNum(num); // noticeNum 설정
        Long reviewnum = reviewService.register(noticeReviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{num}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum,
                                             @RequestBody ReviewDTO noticeReviewDTO){
        log.info("---------------modify noticeReview--------------" + reviewnum);
        log.info("reviewDTO: " + noticeReviewDTO);

        reviewService.modify(noticeReviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{num}/{reviewNum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum){
        log.info("---------------remove noticeReview--------------");
        log.info("reviewnum: " + reviewNum);

        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}