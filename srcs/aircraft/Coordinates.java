package srcs.aircraft;

public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;
	
	public Coordinates(int plongitude, int platitude, int pheight) {
		this.longitude = plongitude;
		this.latitude = platitude;
		this.height = pheight;
	}
	
	public int getLongitude() { return (longitude); }
	public int getLatitude() { return (latitude); }
	public int getHeight() { return (height); }
}