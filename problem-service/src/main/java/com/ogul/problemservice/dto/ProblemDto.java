package com.ogul.problemservice.dto;

import com.ogul.problemservice.model.Input;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDto {

    private String id;
    private String title;
    private String description;
    private String inputDescription;
    private List<Input> inputs;
    private String output;
    private Integer difficulty;
    private Date createdAt;
    private Date updatedAt;
}
