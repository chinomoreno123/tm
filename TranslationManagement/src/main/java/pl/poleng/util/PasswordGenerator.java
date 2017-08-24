package pl.poleng.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PasswordGenerator {
	public static void main(String[] args) throws ClientProtocolException, IOException  {
		String password = "123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));

		// Given
		HttpUriRequest request = new HttpGet("https://api.github.com/users/eugenp");

		// When
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		// Then
		//GitHubUser resource = PasswordGenerator.retrieveResourceFromResponse(response, GitHubUser.class);
		//assertThat("eugenp", Matchers.is(resource.getLogin()));

	}

	public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {

		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(jsonFromResponse, clazz);
	}
}
