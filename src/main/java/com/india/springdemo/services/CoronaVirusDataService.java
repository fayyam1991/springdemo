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

import com.india.springdemo.model.LocationStats;

/**
 * Corona Virus Service
 * 
 * @author Fayyam
 *
 */

@Service
public class CoronaVirusDataService {

	private static String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

	private List<LocationStats> allStats = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	private void fetchCoronaViruisData() throws IOException, InterruptedException, URISyntaxException {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			HttpGet getRequest = new HttpGet(CORONA_VIRUS_URL);

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

			List<LocationStats> tempStats = new ArrayList<>();

			for (CSVRecord record : records) {

				LocationStats locationStats = new LocationStats();
				locationStats.setState(record.get("Province/State"));
				locationStats.setCountry(record.get("Country/Region"));
				Integer latestReportedCases = Integer.valueOf(record.get(record.size() - 1));
				Integer prevDayReportedCases = Integer.valueOf(record.get(record.size() - 2));
				locationStats.setLatestReportedCases(latestReportedCases);
				locationStats.setDiffFromPrevDay(latestReportedCases - prevDayReportedCases);
				tempStats.add(locationStats);
			}

			this.allStats = tempStats;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @return the allStats
	 */
	public List<LocationStats> getAllStats() {
		return allStats;
	}

}
