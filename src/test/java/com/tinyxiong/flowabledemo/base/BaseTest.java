package com.tinyxiong.flowabledemo.base;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringRunner.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest
@Transactional
@ExtendWith({BaseLoggingExtension.class})
public class BaseTest {
}
