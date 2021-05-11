package srcs.aircraft;

import srcs.weather.WeatherProvider;
import srcs.weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	
	Baloon(String pname, Coordinates pcoordinates) { super(pname, pcoordinates); this.type = "Baloon"; }

	@Override
	public void updateConditions() {
		String condition = WeatherProvider.getProvider().getCurrentWeather(coordinates);
		if (condition.equalsIgnoreCase("SUN")) {
			coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
			System.out.println(type + "#" + name + "(" + id + "): " + "Sortez vos lunettes de soleil !");
		} else if (condition.equalsIgnoreCase("RAIN")) {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
			System.out.println(type + "#" + name + "(" + id + "): " + "Nous allons traverser une zone de turbulences");
		} else if (condition.equalsIgnoreCase("FOG")) {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
			System.out.println(type + "#" + name + "(" + id + "): " + "On vient de percuter un nuage");
		} else if (condition.equalsIgnoreCase("SNOW")) {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
			System.out.println(type + "#" + name + "(" + id + "): " + "La température extérieure est de -17 degrés");
		}
	
		if (coordinates.getHeight() > 100)
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
		else if (coordinates.getHeight() <= 0) {
			weatherTower.unregister(this);
			System.out.println(type + "#" + name + "(" + id + ") Landing !");
		}
	}

	@Override
	public void registerTower(WeatherTower pweatherTower) { this.weatherTower = pweatherTower; }
}
