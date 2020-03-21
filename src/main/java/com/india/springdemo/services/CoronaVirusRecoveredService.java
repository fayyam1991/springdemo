package com.india.springdemo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
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

import com.india.springdemo.model.RecoverdStats;

@Service
public class CoronaVirusRecoveredService {

	private static String CORONA_VIRUS_RECOVERED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv";

	private List<RecoverdStats> recoveredStats = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* 1 * * * *")
	private void fetchRecoveredReported() throws IOException, InterruptedException, URISyntaxException {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			HttpGet getRequest = new HttpGet(CORONA_VIRUS_RECOVERED_URL);

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

			List<RecoverdStats> tempStats = new ArrayList<>();

			for (CSVRecord record : records) {

				RecoverdStats recoverdStats = new RecoverdStats();
				recoverdStats.setState(record.get("Province/State"));
				recoverdStats.setCountry(record.get("Country/Region"));
				Integer recovered = Integer.valueOf(record.get(record.size() - 1));
				recoverdStats.setRecovered(recovered);
				tempStats.add(recoverdStats);
			}

			this.recoveredStats = tempStats;
			Collections.sort(recoveredStats);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @return the allStats
	 */
	public List<RecoverdStats> getRecoveredStats() {
		return recoveredStats;
	}

}
