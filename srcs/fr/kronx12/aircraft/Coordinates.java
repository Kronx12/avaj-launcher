package fr.kronx12.aircraft;

public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude, int latitude, int height) throws InvalidCoordinatesException {
        if (height < 0 || height > 100)
            throw new InvalidCoordinatesException("Coordinates invalid height, need to be in 0 to 100 range");
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
