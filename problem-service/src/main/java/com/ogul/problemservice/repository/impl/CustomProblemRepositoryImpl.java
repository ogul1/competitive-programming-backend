package com.ogul.problemservice.repository.impl;

import com.ogul.problemservice.dto.ProblemFilterRequest;
import com.ogul.problemservice.entity.Problem;
import com.ogul.problemservice.repository.CustomProblemRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomProblemRepositoryImpl implements CustomProblemRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Problem> filter(ProblemFilterRequest request, Pageable pageable) {
        List<Criteria> criteria = new ArrayList<>();

        Integer difficulty = request.getDifficulty();
        if (difficulty != null) {
            criteria.add(Criteria.where("difficulty").is(difficulty));
        }

        String title = request.getTitle();
        if (title != null) {
            criteria.add(Criteria.where("title").is(title));
        }

        Criteria andCriteria = criteria.isEmpty() ? new Criteria() : new Criteria().andOperator(criteria);

        List<Problem> problems = mongoTemplate.query(Problem.class)
            .matching(Query.query(andCriteria).with(pageable))
            .all();

        long totalCount = mongoTemplate.query(Problem.class)
            .matching(Query.query(andCriteria))
            .count();

        return new PageImpl<>(problems, pageable, totalCount);
    }
}
