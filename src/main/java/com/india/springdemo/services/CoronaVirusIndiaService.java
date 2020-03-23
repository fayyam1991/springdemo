package com.india.springdemo.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.india.springdemo.model.IndiaStats;

@Service
public class CoronaVirusIndiaService {

	String URL_INDIA_INFO = "https://raw.githubusercontent.com/ameerthehacker/corona-india-status/master/docs/covid19-indian-states.json";

	private ArrayList<IndiaStats> indiaInfo = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* 1 * * * *")
	private void fetchDeathReported() throws IOException, InterruptedException, URISyntaxException {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			HttpGet getRequest = new HttpGet(URL_INDIA_INFO);

			// Set the API media type in http accept header

			// Send the request; It will immediately return the response in HttpResponse
			// object
			HttpResponse response = httpClient.execute(getRequest);

			// verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			// Now pull back the response object
			HttpEntity entity = response.getEntity();
			String apiOutput = EntityUtils.toString(entity);
			@SuppressWarnings("deprecation")
			JsonElement parser = new JsonParser().parse(apiOutput);
			JsonObject asJsonObject = parser.getAsJsonObject();
			JsonElement jsonElement = asJsonObject.get("data");
			Set<Entry<String, JsonElement>> entrySet = jsonElement.getAsJsonObject().entrySet();
			ArrayList<IndiaStats> tempList = new ArrayList<>();
			for (Entry<?, ?> e : entrySet) {
				String state = e.getKey().toString();
				JsonObject value = (JsonObject) e.getValue();
				int totalIndianCases = parsetoInteger(value.get("totalIndianCases"));
				int totalForeignCases = parsetoInteger(value.get("totalForeignCases"));
				int totalRecovered = parsetoInteger(value.get("totalRecovered"));
				int totalDeaths = parsetoInteger(value.get("totalDeaths"));
				int newCasesToday = parsetoInteger(value.get("newCasesToday"));
				if (state != null && totalIndianCases != 0) {
					IndiaStats indiaStats = new IndiaStats(state, totalIndianCases, totalForeignCases, totalRecovered,
							totalDeaths, newCasesToday);
					tempList.add(indiaStats);
				}
			}

			this.indiaInfo = tempList;
			Collections.sort(indiaInfo);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private int parsetoInteger(JsonElement value) {
		if (value ==null || value.toString().equals("null"))
			return 0;
		return Integer.parseInt(value.toString());
	}

	/**
	 * @return the allStats
	 */
	public List<IndiaStats> getIndiaInfo() {
		return indiaInfo;
	}

}
