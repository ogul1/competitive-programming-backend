package com.ogul.problemservice.controller;

import com.ogul.problemservice.dto.ProblemDto;
import com.ogul.problemservice.model.Problem;
import com.ogul.problemservice.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Value("${message}")
    private String message;

    @GetMapping("hello")
    public String hello() {
        return message;
    }

    @GetMapping
    public List<Problem> getProblems(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction
    ) {
        return problemService.getProblems(page, size, sort, direction);
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
