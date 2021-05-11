package srcs.aircraft;

import srcs.exceptions.InvalidTypeException;

public abstract class AircraftFactory {
	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws InvalidTypeException {
		Flyable flyable;
		
		if (type == "Helicopter")
			flyable = new Helicopter(name, new Coordinates(longitude, latitude, height));
		else if (type == "JetPlane")
			flyable = new JetPlane(name, new Coordinates(longitude, latitude, height));
		else if (type == "Baloon")
			flyable = new Baloon(name, new Coordinates(longitude, latitude, height));
		else
			throw new InvalidTypeException("Invalid Aircraft Name");
		return (flyable);
	}
}
