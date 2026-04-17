package com.uwlFood.api;

import com.uwlFood.api.service.S3Service;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class ApiApplicationTests {

    @MockitoBean
    private S3Service s3Service;

    @Test
    void contextLoads() {
    }
}
