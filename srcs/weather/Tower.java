package srcs.weather;

import java.util.ArrayList;

import srcs.aircraft.Aircraft;
import srcs.aircraft.Flyable;

public abstract class Tower {
	ArrayList<Flyable> observers;
	int i = 0;
	
	public Tower() { observers = new ArrayList<Flyable>(); }
	
	public void register(Flyable flyable) {
		System.out.println("Tower says: " + ((Aircraft) flyable).getType() + "#" + ((Aircraft) flyable).getName() + "(" + ((Aircraft) flyable).getId() + ") registered to weather tower.");
		observers.add(flyable);
		flyable.registerTower((WeatherTower) this);
	}
	
	public void unregister(Flyable flyable) {
		System.out.println("Tower says: " + ((Aircraft) flyable).getType() + "#" + ((Aircraft) flyable).getName() + "(" + ((Aircraft) flyable).getId() + ") unregistered from weather tower.");
		observers.remove(flyable);
		i--;
	}
	
	protected void conditionsChanged() {
		for (i = 0; i < observers.size(); i++)
			observers.get(i).updateConditions();
	}
}

