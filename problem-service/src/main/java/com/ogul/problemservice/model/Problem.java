package com.ogul.problemservice.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "problems")
public class Problem {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String inputDescription;

    @NotEmpty
    private List<Input> inputs;

    @NotBlank
    private String output;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer difficulty;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
