package com.covid.tracker;

import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(SpringBootTest.WebEnvironment.MOCK, classes = CovidTrackerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CovidTrackerApplicationRestControllerIntegrationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private com.covid.tracker.beans.CovidPatientRepository CovidPatientRepository;
	@Autowired
	private com.covid.tracker.beans.VaccinatedPeopleRepository vaccinatedPeopleRepository;

	@Test
	public void givenCovidPatients_whenGetCovidPatients_thenStatus200()throws Exception {

		addCovidPatients("bob");

		mvc.perform(get("/api/employees")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].name", is("bob")));
	}

}
