package fr.kronx12.aircraft;

import fr.kronx12.simulator.WeatherProvider;
import fr.kronx12.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String condition = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        try {
            if (condition.equalsIgnoreCase("SUN")) {
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                System.out.println("JetPlane#" + name + "(" + id + "): " + "Sortez vos lunettes de soleil commandant !");
            } else if (condition.equalsIgnoreCase("RAIN")) {
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                System.out.println("JetPlane#" + name + "(" + id + "): " + "Heu chef, y'a des gouttes d'eau qui tombent du plafond");
            } else if (condition.equalsIgnoreCase("FOG")) {
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                System.out.println("JetPlane#" + name + "(" + id + "): " + "Activez le pilotage automatique");
            } else if (condition.equalsIgnoreCase("SNOW")) {
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                System.out.println("JetPlane#" + name + "(" + id + "): " + "La température extérieure est de -17 degrés");
            }

            if (coordinates.getHeight() > 100)
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
            else if (coordinates.getHeight() <= 0) {
                weatherTower.unregister(this);
                System.out.println("JetPlane#" + name + "(" + id + ") On atterris !");
            }
        } catch (InvalidCoordinatesException e) { e.printStackTrace(); }
    }

    public String getType() {
        return "JetPlane";
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}
