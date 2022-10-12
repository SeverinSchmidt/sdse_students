package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

public class CityCSVProcessor {
	
	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			ArrayList<CityRecord> allRecords = new ArrayList<CityRecord>();
			Map<String, ArrayList<CityRecord>> recordsByCity = new HashMap<String, ArrayList<CityRecord>>();
			HashSet<String> uniqueCities = new HashSet<String>();
			
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
				
				System.out.println("id: " + id + ", year: " + year + ", city: " + city + ", population: " + population);
				
				//TODO: Extend the program to process entries!
				CityRecord cityRecord = new CityRecord(id, year, city, population);
				System.out.println(cityRecord);
				allRecords.add(cityRecord);
				uniqueCities.add(city);

			/* for unique city
		     
		     * for each record in allRecords add the ones where city == unique city to a temporary list of records
		     * Then create add key and value (list of records) to Map
		     */
		
				}
			for (String c : uniqueCities) {
				ArrayList<CityRecord> temp = new ArrayList<CityRecord>();
				for (CityRecord r : allRecords) {
					if (r.city.equals(c)) {
						temp.add(r);
					}
				}
				recordsByCity.put(c, temp);
			}
			/*
			 * for each city
			 * for each record in city:
			 * 	count entries
			 * 	add up population
			 *  if minimum year
			 *  if maximum year
			 *  
			 * divide to get avg pop
			 * print out the values
			 *
			 */
			for (Entry<String, ArrayList<CityRecord>> entry : recordsByCity.entrySet()) {
				int count = 0;
				int totalPop = 0;
				int minYear = 10000;
				int maxYear = 0;
				for (CityRecord r : entry.getValue()) {
					count += 1;
					totalPop += r.population;
					if (r.year < minYear) {
						minYear = r.year;
					} else if (r.year > maxYear) {
						maxYear = r.year;
					}
				}
				System.out.println("Average population of " + entry.getKey() + " (" + minYear + "-" + maxYear + "; " + count + "): " + totalPop / count);
			}
			
				
			
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
}
