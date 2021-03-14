package canis.review;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
class CanisReviewApplicationTests {

	@Autowired
	private MockReview mockReview;

	@Autowired
	private MockMvc mvc;

	// @Autowired
	// private ReviewRepository repository;

	private static String toJsonString(Review review) {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.findAndRegisterModules();
			// objectMapper.registerModule(new JavaTimeModule());
			// objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			String json = objectMapper.writeValueAsString(review);
			System.out.println(json);
			return json;
		} catch (Exception e) {
			throw new RuntimeException("fail conversion toJsonString");
		}
	}

	@Test
	void createReview() throws Exception {

		final Review submission = mockReview.mock();

		try {
		mvc.perform(
			post("/review/save")
      	.contentType(MediaType.APPLICATION_JSON)
      	.accept(MediaType.APPLICATION_JSON)
				.content(toJsonString(submission))
			)
			.andExpect(status().isCreated());

		mvc.perform(get("/review/subject/" + MockReview.SUBJECT_ID)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$.content[0].reviewId", is(MockReview.REVIEW_ID)));
		} catch (UnsupportedOperationException e) {
			throw e;
		}
	}
}
