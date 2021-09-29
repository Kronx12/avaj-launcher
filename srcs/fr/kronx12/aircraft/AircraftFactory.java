package fr.kronx12.aircraft;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws InvalidTypeException, InvalidCoordinatesException {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.equals("Helicopter"))
            return new Helicopter(name, coordinates);
        if (type.equals("JetPlane"))
            return new JetPlane(name, coordinates);
        if (type.equals("Baloon"))
            return new Baloon(name, coordinates);
        throw new InvalidTypeException(type);
    }
}