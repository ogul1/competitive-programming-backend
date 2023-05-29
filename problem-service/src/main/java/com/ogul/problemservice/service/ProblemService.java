package com.ogul.problemservice.service;

import com.ogul.problemservice.dto.ProblemDto;
import com.ogul.problemservice.dto.ProblemFilterRequest;
import com.ogul.problemservice.dto.ProblemResponse;
import com.ogul.problemservice.mapper.ProblemMapper;
import com.ogul.problemservice.entity.Problem;
import com.ogul.problemservice.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public ProblemResponse getProblems(ProblemFilterRequest request, Pageable pageable) {
        Page<Problem> problems = problemRepository.filter(request, pageable);

        return ProblemResponse.builder()
                .problems(problems.getContent())
                .currentPage(problems.getNumber())
                .totalPages(problems.getTotalPages())
                .totalCount(problems.getTotalElements())
                .build();
    }

    public Problem getProblemByTitle(String title) {
        return problemRepository.findProblemByTitle(title).orElseThrow(
                () -> new RuntimeException("Problem not found!")
        );
    }

    public Problem updateProblem(String id, ProblemDto problemDto) {
        Problem problem = problemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Problem not found!")
        );
        problemMapper.updateProblemFromDto(problemDto, problem);
        return problemRepository.save(problem);
    }

    public String deleteProblem(String id) {
        problemRepository.deleteById(id);
        return id;
    }
}
