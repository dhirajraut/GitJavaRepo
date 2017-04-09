import DataObjects.WeatherData;
import Observers.HumidityDisplay;
import Observers.TempratureDisplay;
import Observers.WeatherDisplay;

public class ObserverPattern {
	public static void main(String args[]) throws Exception {
		
		WeatherData weatherData = new WeatherData();
		weatherData.addObserver(new HumidityDisplay());
		weatherData.addObserver(new TempratureDisplay());
		weatherData.addObserver(new WeatherDisplay());
		
		for (int i = 0; i < 15; i++) {
			if (i >= 0 && i < 5) {
				weatherData.setMeasurements("10%", "30 C", "Clear");
			}
			if (i >= 5 && i < 10) {
				weatherData.setMeasurements("100%", "40 C", "Rains");
			}
			if (i >= 10 && i < 15) {
				weatherData.setMeasurements("20%", "50 C", "Dusty");
			}
			System.out.println("\n");
			Thread.sleep(1000);
		}
	}
}
