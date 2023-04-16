package com.ogul.problemservice.service;

import com.ogul.problemservice.dto.ProblemDto;
import com.ogul.problemservice.mapper.ProblemMapper;
import com.ogul.problemservice.model.Problem;
import com.ogul.problemservice.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;

    private final ProblemMapper problemMapper;

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public List<Problem> getProblems(int page, int size, String sort, String direction) {
        Page<Problem> problems = problemRepository.findAll(
                PageRequest.of(
                        page, size,
                        Sort.by(
                                Objects.equals(direction, "ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sort
                        )
                )
        );
        return problems.getContent();
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
