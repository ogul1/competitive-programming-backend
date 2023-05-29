package com.ogul.problemservice.client;

import com.ogul.problemservice.dto.ProblemResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface MyClient {

    @GetExchange("/")
    ProblemResponse problemResponse();
}
