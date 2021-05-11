package srcs.weather;

import srcs.aircraft.Coordinates;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
	
	private WeatherProvider() {}
	public static WeatherProvider getProvider() { return (WeatherProvider.weatherProvider); }
	public String getCurrentWeather(Coordinates coordinates) {
		int newIndex = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + (int)(Math.random() * 1234)) % 4;
		return weather[newIndex];
	}
}
