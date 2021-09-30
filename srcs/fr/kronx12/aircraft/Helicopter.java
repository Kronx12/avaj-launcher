package fr.kronx12.aircraft;

import fr.kronx12.simulator.WeatherProvider;
import fr.kronx12.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String condition = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        if (condition.equalsIgnoreCase("SUN")) {
            coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
            System.out.println("Helicopter#" + name + "(" + id + "): " + "Pourquoi vous avez pas pris l'option par-soleil ??");
        } else if (condition.equalsIgnoreCase("RAIN")) {
            coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
            System.out.println("Helicopter#" + name + "(" + id + "): " + "Nous allons traverser une zone de turbulences");
        } else if (condition.equalsIgnoreCase("FOG")) {
            coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
            System.out.println("Helicopter#" + name + "(" + id + "): " + "Il va faire tout noir !");
        } else if (condition.equalsIgnoreCase("SNOW")) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
            System.out.println("Helicopter#" + name + "(" + id + "): " + "Allons sauver des skieurs !");
        }
        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        else if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            System.out.println("Helicopter#" + name + "(" + id + ") Landing !");
        }
    }

    public String getType() {
        return "Helicopter";
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}
