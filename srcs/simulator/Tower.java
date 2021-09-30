package simulator;

import aircraft.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tower {
    private final ArrayList<Flyable> observers = new ArrayList<Flyable>();;

    public void register(Flyable flyable) {
        observers.add(flyable);
        flyable.registerTower((WeatherTower) this);
        System.out.println("Tower says: " + flyable.getType() + "#" + ((Aircraft) flyable).getName() + "(" + ((Aircraft) flyable).getId() + ") registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        System.out.println("Tower says: " + flyable.getType() + "#" + ((Aircraft) flyable).getName() + "(" + ((Aircraft) flyable).getId() + ") unregistered to weather tower.");
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
    }
}
