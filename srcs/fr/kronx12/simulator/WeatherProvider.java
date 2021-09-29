package fr.kronx12.simulator;

import fr.kronx12.aircraft.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + (int)(Math.random() * 1234)) % 4];
    }
}
