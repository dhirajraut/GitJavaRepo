package DataObjects;

import java.util.Observable;

public class WeatherData extends Observable {

	private String humidity = "";
	private String temprature = "";
	private String weather = "";

	public void setMeasurements(final String humidity,
								final String temprature,
								final String weather) {
		this.humidity = humidity;
		this.temprature = temprature;
		this.weather = weather;
		measurementsChanged();
	}
	
	public void measurementsChanged() {
		setChanged();
		notifyObservers();
	}
	public String getHumidity() {
		return humidity;
	}
	public String getTemprature() {
		return temprature;
	}
	public String getWeather() {
		return weather;
	}
}
