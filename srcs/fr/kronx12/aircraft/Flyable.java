package fr.kronx12.aircraft;

import fr.kronx12.simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
    String getType();
}
