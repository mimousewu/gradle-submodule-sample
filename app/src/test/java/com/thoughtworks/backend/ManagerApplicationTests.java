package com.thoughtworks.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ManagerApplication.class})
@ActiveProfiles("test")
public class ManagerApplicationTests {

    @Test
    public void contextLoads() {
    }

}
