package com.example.migrosOnlineCase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.migrosOnlineCase.dto.CourierGeoPathDTO;
import com.example.migrosOnlineCase.entity.CourierGeoPath;
import com.example.migrosOnlineCase.service.CourierService;
import com.example.migrosOnlineCase.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MigrosOnlineCaseApplicationTests {

	@Autowired
	private CourierService courierService;

	@Autowired
	private TestRestTemplate restTemplate;

	private String baseUrl;

	@BeforeEach
	void setup() {
		baseUrl = "http://localhost:8080/courier";
	}
	/**
	 * Tests the /courier/stream endpoint with a sample JSON input and compares the output.
	 */
	@Test
	void testStreamCourierLocation_withValidData() throws Exception {
		// Read the JSON input from the file
		// Read the JSON input array from the file
		String jsonArray = new String(Files.readAllBytes(Paths.get("src/test/resources/courier-stream-input.json")));

		// Parse the JSON array into a List of Strings
		ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
		List<CourierGeoPathDTO> jsonInputs = objectMapper.readValue(jsonArray, new TypeReference<List<CourierGeoPathDTO>>() {});

		for (CourierGeoPathDTO jsonInput : jsonInputs) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CourierGeoPathDTO> entity = new HttpEntity<>(jsonInput, headers);

			// Send a POST request with the JSON input
			ResponseEntity<String> response = restTemplate.exchange(
					baseUrl + "/stream",
					HttpMethod.POST,
					entity,
					String.class
			);
			assertEquals(200, response.getStatusCodeValue());

		}
	}

	@Test
	void testStreamCourierLocation_withValidDataWait1MinForEach() throws Exception {
		// Read the JSON input from the file
		// Read the JSON input array from the file
		String jsonArray = new String(Files.readAllBytes(Paths.get("src/test/resources/courier-stream-input-same-store.json")));

		// Parse the JSON array into a List of Strings
		ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
		List<CourierGeoPathDTO> jsonInputs = objectMapper.readValue(jsonArray, new TypeReference<List<CourierGeoPathDTO>>() {});
		for (CourierGeoPathDTO jsonInput : jsonInputs) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CourierGeoPathDTO> entity = new HttpEntity<>(jsonInput, headers);

			// Send a POST request with the JSON input
			ResponseEntity<String> response = restTemplate.exchange(
					baseUrl + "/stream",
					HttpMethod.POST,
					entity,
					String.class
			);
			assertEquals(200, response.getStatusCodeValue());

		}
	}

	/**
	 * Tests the /courier/{id}/distance endpoint to calculate total travel distance.
	 */
	@Test
	void testGetCourierDistance() throws IOException, JSONException {
		// Assuming the courier ID is "C001" and distance was already calculated.
		String expectedDistanceJson = loadResource("courier-distance-output.json");
		String jsonArray = new String(Files.readAllBytes(Paths.get("src/test/resources/courier-distance-input.json")));

		// Parse the JSON array into a List of Strings
		ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
		List<CourierGeoPathDTO> jsonInputs = objectMapper.readValue(jsonArray, new TypeReference<List<CourierGeoPathDTO>>() {});
		for (CourierGeoPathDTO jsonInput : jsonInputs) {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CourierGeoPathDTO> entity = new HttpEntity<>(jsonInput, headers);

			// Send a POST request with the JSON input
			ResponseEntity<String> response = restTemplate.exchange(
					baseUrl + "/stream",
					HttpMethod.POST,
					entity,
					String.class
			);
			assertEquals(200, response.getStatusCodeValue());
		}

		// Send GET request to /courier/{id}/distance endpoint and expect a JSONObject
		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl+"/C001/distance", String.class
		);

		// Assert the actual JSON response matches the expected distance
		JSONAssert.assertEquals(expectedDistanceJson, response.getBody(), JSONCompareMode.STRICT);
	}

	@Test
	void testInvalidCourierStream() throws IOException {
		// Load invalid input JSON
		String invalidInputJson = loadResource("invalid-courier-stream-input.json");

		// Create HTTP headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create an HTTP entity with the invalid JSON
		HttpEntity<String> request = new HttpEntity<>(invalidInputJson, headers);

		// Send POST request to the /courier/stream endpoint
		ResponseEntity<String> response = restTemplate.exchange(
				baseUrl +"/stream",
				HttpMethod.POST,
				request,
				String.class
		);

		// Assert the response status code is 400 BAD REQUEST
		assertEquals(400, response.getStatusCodeValue());
	}

	private String loadResource(String resourceName) throws IOException {
		ClassPathResource resource = new ClassPathResource(resourceName);
		return Files.readString(resource.getFile().toPath());
	}
}
