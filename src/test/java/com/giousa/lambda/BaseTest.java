package com.giousa.lambda;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationMain.class})
@ActiveProfiles("test")
public abstract class BaseTest {

    public static void main(String[] args) {
        System.out.println("base test start");
    }
}
