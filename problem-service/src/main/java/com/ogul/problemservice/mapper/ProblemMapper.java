package com.ogul.problemservice.mapper;

import com.ogul.problemservice.dto.ProblemDto;
import com.ogul.problemservice.model.Problem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProblemFromDto(ProblemDto problemDto, @MappingTarget Problem problem);
}
