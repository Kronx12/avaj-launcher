package fr.kronx12.simulator;

import fr.kronx12.aircraft.*;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Tower {
    private final ArrayList<Flyable> observers = new ArrayList<>();

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
        for (Iterator<Flyable> itr = observers.iterator(); itr.hasNext();) {
            Flyable f = itr.next();
            f.updateConditions();        
	}
    }
}
