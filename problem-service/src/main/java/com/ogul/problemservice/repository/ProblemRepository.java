package com.ogul.problemservice.repository;

import com.ogul.problemservice.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProblemRepository extends MongoRepository<Problem, String>, CustomProblemRepository {

    Optional<Problem> findProblemByTitle(String title);

    Page<Problem> findAll(Pageable pageable);

}
