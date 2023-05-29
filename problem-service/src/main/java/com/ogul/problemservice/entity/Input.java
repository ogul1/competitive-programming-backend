package com.ogul.problemservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {

    @NotBlank
    private String variable;

    @NotBlank
    private int lowerBound;

    @NotBlank
    private int upperBound;
}
