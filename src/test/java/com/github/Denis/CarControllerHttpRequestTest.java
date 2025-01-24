//package com.github.Denis;
//
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest//(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class CarControllerHttpRequestTest {
//
////    @LocalServerPort
////    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void greetingShouldReturnDefaultMessage() throws Exception {
////        System.out.println(this.restTemplate.getForObject("http://localhost:" + 8081 + "/api/cars",
////                String.class));
////        assertThat(this.restTemplate.getForObject("http://localhost:" + 8081 + "/",
////                String.class)).contains("Bor");
//    }
//}