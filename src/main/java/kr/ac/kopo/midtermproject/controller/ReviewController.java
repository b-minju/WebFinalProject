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
    @GetMapping("/{noticeNum}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("noticeNum") Long num){
        log.info("--------------list---------------");
        log.info("NUM: " + num);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfNotice(num);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    // 댓글 추가
    @PostMapping("/{noticeNum}")
    public ResponseEntity<Long> addReview(@PathVariable("noticeNum") Long num, @RequestBody ReviewDTO reviewDTO){
        log.info("--------------add noticeReview---------------");
        log.info("reviewDTO: " + reviewDTO);

        reviewDTO.setNum(num); // noticeNum 설정
        Long reviewnum = reviewService.register(reviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{noticeNum}/{reviewNum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewNum,
                                             @RequestBody ReviewDTO reviewDTO){
        log.info("---------------modify noticeReview--------------" + reviewNum);
        log.info("reviewDTO: " + reviewDTO);

        reviewService.modify(reviewDTO);

        return new ResponseEntity<>( reviewNum, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{noticeNum}/{reviewNum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum){
        log.info("---------------remove review--------------");
        log.info("reviewNum: " + reviewNum);

        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}