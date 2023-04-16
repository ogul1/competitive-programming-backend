package com.ogul.problemservice;

import com.ogul.problemservice.mapper.ProblemMapper;
import com.ogul.problemservice.model.Input;
import com.ogul.problemservice.model.Problem;
import com.ogul.problemservice.repository.ProblemRepository;
import com.ogul.problemservice.service.ProblemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Testcontainers
@SpringBootTest
class ProblemServiceApplicationTests {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ProblemMapper problemMapper;

    private ProblemService problemService;

    @Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:5"));

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        problemService = new ProblemService(problemRepository, problemMapper);
        List<Input> inputs = Arrays.asList(
                new Input(
                        "a",
                        1,
                        10000
                ),
                new Input(
                        "b",
                        1,
                        10000
                )
            );
        Problem problem = Problem.builder()
                        .title("Problem 1")
                        .description("Add two numbers a and b, then print the result.")
                        .inputs(inputs)
                        .inputDescription("Output the sum of the two numbers, that is a + b.")
                        .difficulty(5)
                        .build();

        Problem problem2 = Problem.builder()
                .title("Problem 2")
                .description("Add two numbers a and b, then print the result.")
                .inputs(inputs)
                .inputDescription("Output the sum of the two numbers, that is a + b.")
                .difficulty(5)
                .build();

        problemRepository.insert(problem);
        problemRepository.insert(problem2);
    }

    @Test
    void shouldGetAllProblems() {
        List<Problem> problems = problemService.getProblems(0, 10, "id", "DESC");
        assertThat(problems).hasSize(2);
    }
}
