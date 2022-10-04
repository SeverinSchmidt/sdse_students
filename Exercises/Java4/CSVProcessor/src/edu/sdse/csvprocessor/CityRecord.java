package edu.sdse.csvprocessor;

public class CityRecord {
	//instance fields
	int id;
	int year;
	String city;
	int population;
	
	//constructor
	public CityRecord(int id, int year, String city, int population) {
		super();
		this.id = id;
		this.year = year;
		this.city = city;
		this.population = population;
	}
	
	//methods
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "id: " + id + ", year: " + year + ", city: " + city + ", population: " + population;
	}

}
