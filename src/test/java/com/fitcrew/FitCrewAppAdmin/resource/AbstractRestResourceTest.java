package com.fitcrew.FitCrewAppAdmin.resource;

import com.fitcrew.FitCrewAppAdmin.FitCrewAppAdminApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.inject.Inject;

@TestPropertySource(locations = {"classpath:application-test.properties"})
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = FitCrewAppAdminApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractRestResourceTest {

	@Inject
	protected TestRestTemplate restTemplate;
}
