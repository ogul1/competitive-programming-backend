package com.ogul.problemservice.repository;

import com.ogul.problemservice.dto.ProblemFilterRequest;
import com.ogul.problemservice.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProblemRepository {
    Page<Problem> filter(ProblemFilterRequest request, Pageable pageable);
}
