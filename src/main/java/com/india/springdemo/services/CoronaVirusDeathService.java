package com.india.springdemo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.india.springdemo.model.DeathStats;

/**
 * 
 * @author Fayyam
 *
 */

@Service
public class CoronaVirusDeathService {
	
	private static String CORONA_VIRUS_DEATH_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Deaths.csv";

	private List<DeathStats> deathStats = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	private void fetchDeathReported() throws IOException, InterruptedException, URISyntaxException {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			HttpGet getRequest = new HttpGet(CORONA_VIRUS_DEATH_URL);

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
			StringReader csvBodyReader = new StringReader(apiOutput);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(csvBodyReader);

			List<DeathStats> tempStats = new ArrayList<>();

			for (CSVRecord record : records) {

				DeathStats locationStats = new DeathStats();
				locationStats.setState(record.get("Province/State"));
				locationStats.setCountry(record.get("Country/Region"));
				Integer deaths = Integer.valueOf(record.get(record.size() - 1));
				locationStats.setDeaths(deaths);
				tempStats.add(locationStats);
			}

			this.deathStats = tempStats;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @return the allStats
	 */
	public List<DeathStats> getDeathsStats() {
		return deathStats;
	}

}
