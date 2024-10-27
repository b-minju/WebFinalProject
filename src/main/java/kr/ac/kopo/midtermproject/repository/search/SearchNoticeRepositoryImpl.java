package kr.ac.kopo.midtermproject.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.ac.kopo.midtermproject.entity.Notice;
import kr.ac.kopo.midtermproject.entity.QNotice;
import kr.ac.kopo.midtermproject.entity.QUserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchNoticeRepositoryImpl extends QuerydslRepositorySupport implements SearchNoticeRepository {
    public SearchNoticeRepositoryImpl(){
        super(Notice.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage() called");
        QNotice notice = QNotice.notice;
        QUserEntity user = QUserEntity.userEntity;

        JPQLQuery<Notice> jpqlQuery = from(notice);
        jpqlQuery.leftJoin(user).on(notice.writer.eq(user));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(notice, user);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = notice.num.gt(0L);
        booleanBuilder.and(expression);

        if(type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t" :
                        conditionBuilder.or(notice.title.contains(keyword));
                        break;

                    case "w" :
                        conditionBuilder.or(user.name.contains(keyword));
                        break;

                    case "c" :
                        conditionBuilder.or(notice.content.contains(keyword));
                        break;
                } // end switch
            } // end for
            booleanBuilder.and(conditionBuilder);
        } // end if

        tuple.where(booleanBuilder);
//        Sorting(Order by)
        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC:Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Notice.class, "notice");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(notice, user);

//        페이지 처리에 필요한 값(offset, limit) 설정
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        log.info("=======================");
        log.info(result);
        log.info("=======================");

        long count = tuple.fetchCount();

        log.info("실행된 행의 개수 : " + count);

        return new PageImpl<Object[]>(result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, count);
    }
}
