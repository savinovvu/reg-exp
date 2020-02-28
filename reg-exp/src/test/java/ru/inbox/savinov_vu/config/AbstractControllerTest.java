package ru.inbox.savinov_vu.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@WithMockUser(username = "admin", password = "password")
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableWebMvc
@Import(ControllerTestCommonConfiguration.class)
@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractControllerTest {

}
