package com.ansh.service;

import com.ansh.dto.RequestDTO;
import com.ansh.dto.RequestData;
import com.ansh.dto.ResponseDTO;
import com.ansh.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BasicService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    // this is a synchronous call but we can also do asynchronous using web-client
    public ResponseDTO getMethod(){
        log.info("Basic service : getMethod method");
        ResponseDTO<ResponseData> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/get")
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .block(); // when we use this it becomes synchronous call
        return response;
    }

    // this is a synchronous call but we can also do asynchronous using web-client
    public ResponseDTO postMethod(RequestDTO<RequestData> requestDTO){
        log.info("Basic service : postMethod method");
        ResponseDTO<ResponseData> response =webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/post")
                .body(Mono.just(requestDTO), RequestDTO.class)
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .block(); // when we use this it becomes synchronous call
        return response;
    }
}