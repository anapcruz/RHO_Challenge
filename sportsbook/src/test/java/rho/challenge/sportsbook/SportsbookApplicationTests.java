package rho.challenge.sportsbook;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@RunWith(JUnitPlatform.class)
@SpringBootTest
class SportsbookApplicationTests {

    @Test
    void contextLoads() {
    }

}
