package io.github.trgfriendscovers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    private final CoverController controller;

    //The usual component scan doesn't happen before junit testing?
    //Spring interprets the @Autowired annotation, and the controller is injected before the test methods are run.
    @Autowired
    public SmokeTest(CoverController controller) {
        this.controller = controller;
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();

    }
}
