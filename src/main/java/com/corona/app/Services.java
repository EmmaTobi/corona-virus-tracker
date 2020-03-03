package com.corona.app;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.model.LocationStat;

@Service
public class Services {

	private List<LocationStat> allStats = new ArrayList<LocationStat>();
	
	private String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	
	public List<LocationStat> getAllStats() {
		return allStats;
	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStat> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CORONA_VIRUS_URL)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		StringReader stringCsvReader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringCsvReader);
		
		for(CSVRecord record : records) {
			LocationStat stat = new LocationStat();
			stat.setState(record.get("Province/State"));
			stat.setCountry(record.get("Country/Region"));
			int latestCount = Integer.parseInt(record.get(record.size() - 1));
			int previousCount = Integer.parseInt(record.get(record.size() - 2));
			stat.setInfectedCount(latestCount);
			stat.setPreviousInfectedCount(previousCount);
			stat.setChangeFromInfectedCount(latestCount - previousCount); 
			newStats.add(stat);
		}
		allStats = newStats;
	}
	
}
