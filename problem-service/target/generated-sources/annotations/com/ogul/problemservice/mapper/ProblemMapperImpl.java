package com.ogul.problemservice.mapper;

import com.ogul.problemservice.dto.ProblemDto;
import com.ogul.problemservice.model.Input;
import com.ogul.problemservice.model.Problem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-03T18:13:54+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProblemMapperImpl implements ProblemMapper {

    @Override
    public void updateProblemFromDto(ProblemDto problemDto, Problem problem) {
        if ( problemDto == null ) {
            return;
        }

        if ( problemDto.getId() != null ) {
            problem.setId( problemDto.getId() );
        }
        if ( problemDto.getTitle() != null ) {
            problem.setTitle( problemDto.getTitle() );
        }
        if ( problemDto.getDescription() != null ) {
            problem.setDescription( problemDto.getDescription() );
        }
        if ( problemDto.getInputDescription() != null ) {
            problem.setInputDescription( problemDto.getInputDescription() );
        }
        if ( problem.getInputs() != null ) {
            List<Input> list = problemDto.getInputs();
            if ( list != null ) {
                problem.getInputs().clear();
                problem.getInputs().addAll( list );
            }
        }
        else {
            List<Input> list = problemDto.getInputs();
            if ( list != null ) {
                problem.setInputs( new ArrayList<Input>( list ) );
            }
        }
        if ( problemDto.getOutput() != null ) {
            problem.setOutput( problemDto.getOutput() );
        }
        if ( problemDto.getDifficulty() != null ) {
            problem.setDifficulty( problemDto.getDifficulty() );
        }
        if ( problemDto.getCreatedAt() != null ) {
            problem.setCreatedAt( problemDto.getCreatedAt() );
        }
        if ( problemDto.getUpdatedAt() != null ) {
            problem.setUpdatedAt( problemDto.getUpdatedAt() );
        }
    }
}
