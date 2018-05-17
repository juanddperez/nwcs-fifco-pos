package cr.nwcs.demo.gamification.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import cr.nwcs.demo.gamification.dto.PlayerDto;

@Service
public class GamificationApiService {
	private static final String DESTINATION = "https://gamification-p1219817459trial.hanatrial.ondemand.com/gamification/api/tech/JsonRPC";
	private static final String AUTHORIZATION_HEADER_VALUE = "Basic anVhbmRkcGVyZXpAZ21haWwuY29tOkhpbGxzb25nODY=";
	private static final String WRAPPER_NAME = "result";
	private static final String APP_VALUE = "FIFCO";
	private static final String APP = "app";
	private static final String JSON = "json";
	final HttpHeaders headers;

	@Autowired
	private RestTemplate restTemplate;

	private final ObjectMapper objectMapper;
	{
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
	}

	public GamificationApiService() {
		headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, AUTHORIZATION_HEADER_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
	}

	public static MultiValueMap<String, Object> buildMultiValueMapWithJsonValue(String json) {
		MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();
		multipartRequest.add(APP, APP_VALUE);
		multipartRequest.add(JSON, json);
		return multipartRequest;
	}

	public Boolean customerBuys(String playerId, Integer points) {
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
				buildMultiValueMapWithJsonValue("{\"method\":\"handleEvent\",\"params\":[{\"type\":\"customerBuys\",\"playerid\":\"" + playerId
						+ "\",\"data\":{\"points\":" + points + "}}]}"),
				headers);
		final String responseString = restTemplate.postForObject(DESTINATION, requestEntity, String.class);
		return processJsonResponse(() -> new TypeReference<Boolean>() {}, responseString);
	}

	public PlayerDto getPlayer(String id) {
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
				buildMultiValueMapWithJsonValue("{\"method\":\"getPlayer\",\"params\":[\"" + id + "\"]}"), headers);
		final String responseString = restTemplate.postForObject(DESTINATION, requestEntity, String.class);
		return processJsonResponse(() -> new TypeReference<PlayerDto>() {}, responseString);
	}

	public <T> T processJsonResponse(Parser parser, String responseString) {
		try {
			final JsonNode response = objectMapper.readTree(responseString);
			if (response.has(WRAPPER_NAME)) {
				final JsonNode wrapper = response.get(WRAPPER_NAME);
				return objectMapper.readValue(wrapper.traverse(), parser.parser());
			}
		} catch (IOException e) {
			throw new RuntimeException("Error al parsear la respuesta JSON", e);
		}
		return null;
	}

	public interface Parser {
		public TypeReference<?> parser();
	}
}
