package com.ogul.problemservice.controller;

import com.ogul.problemservice.dto.ProblemDto;
import com.ogul.problemservice.dto.ProblemFilterRequest;
import com.ogul.problemservice.dto.ProblemResponse;
import com.ogul.problemservice.entity.Problem;
import com.ogul.problemservice.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/problems")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public ProblemResponse getProblems(
        @Valid ProblemFilterRequest request,
        @PageableDefault(size = 20) Pageable pageable
    ) {
        return problemService.getProblems(request, pageable);
    }

    @GetMapping("/{title}")
    public Problem getProblemByTitle(@PathVariable("title") String title) {
        return problemService.getProblemByTitle(title);
    }

    @PostMapping
    public Problem createProblem(@Valid @RequestBody Problem problem) {
        return problemService.createProblem(problem);
    }

    @PutMapping("/{id}")
    public Problem updateProblem(
        @PathVariable("id") String id,
        @RequestBody ProblemDto problemDto
    ) {
        return problemService.updateProblem(id, problemDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProblem(@PathVariable("id") String id) {
        return problemService.deleteProblem(id);
    }
}
