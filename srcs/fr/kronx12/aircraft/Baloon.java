package fr.kronx12.aircraft;

import fr.kronx12.simulator.WeatherProvider;
import fr.kronx12.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String condition = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        if (condition.equalsIgnoreCase("SUN")) {
            coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
            System.out.println("Baloon#" + name + "(" + id + "): " + "C'est parti pour une séance de bronzage !");
        } else if (condition.equalsIgnoreCase("RAIN")) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
            System.out.println("Baloon#" + name + "(" + id + "): " + "Mettez vos k-way ! Ca va mouiller");
        } else if (condition.equalsIgnoreCase("FOG")) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
            System.out.println("Baloon#" + name + "(" + id + "): " + "On vient de percuter un nuage");
        } else if (condition.equalsIgnoreCase("SNOW")) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
            System.out.println("Baloon#" + name + "(" + id + "): " + "GlaGlaGla ca caille les miches");
        }
        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        else if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            System.out.println("Baloon#" + name + "(" + id + ") Landing !");
        }
    }

    public String getType() {
        return "Baloon";
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}
