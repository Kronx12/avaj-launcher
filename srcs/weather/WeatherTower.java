package srcs.weather;

import srcs.aircraft.Coordinates;

public class WeatherTower extends Tower {
	public WeatherTower() { super(); }
	public String getWeather(Coordinates coordinate) { return WeatherProvider.getProvider().getCurrentWeather(coordinate); }
	public void changeWeather() { this.conditionsChanged(); }
}
