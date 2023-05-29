package com.ogul.problemservice.dto;

import com.ogul.problemservice.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemResponse {
    private List<Problem> problems;
    private int currentPage;
    private int totalPages;
    private long totalCount;
}
