package com.covid.tracker;

import com.covid.tracker.beans.CovidPatientRepository;
import com.covid.tracker.beans.VaccinatedPeopleRepository;
import com.covid.tracker.utilities.CSVService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovidTrackerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CovidTrackerApplicationRestControllerIntegrationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private com.covid.tracker.beans.CovidPatientRepository CovidPatientRepository;
	@Autowired
	private com.covid.tracker.beans.VaccinatedPeopleRepository vaccinatedPeopleRepository;
	@Autowired
	private CSVService fileService;

	//MockMultipartFile file = new MockMultipartFile("Covid_Patients.csv", "Awa, 26/08/1994, Thiaroye Azur, 02/11/2021,positive");

	@Test
	public void givenCovidPatients_whenGetCovidPatients_thenStatus200()throws Exception {

		//fileService.addCovidPatients(file);

		mvc.perform(get("/addCovidPatients")
						.contentType(MediaType.TEXT_HTML_VALUE))
						.andExpect(status().isOk());
	}

}
