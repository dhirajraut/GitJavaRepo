package Observers;

import java.util.Observable;
import java.util.Observer;

import DataObjects.WeatherData;

public class HumidityDisplay implements Observer {

	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherData) {
			WeatherData weatherdata = (WeatherData) observable;
			System.out.println("Humidity = " + weatherdata.getHumidity());
		}
	}
}
