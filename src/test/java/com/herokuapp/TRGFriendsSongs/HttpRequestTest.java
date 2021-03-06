package com.herokuapp.TRGFriendsSongs;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void searchShouldReturnResult() throws Exception {
        CollectionModel<?> cm = this.restTemplate.getForObject("http://localhost:" + port + "/songs/api?search=toxic", CollectionModel.class);
        assertThat(cm.getLinks().toString().contains("toxic"));
    }
}
