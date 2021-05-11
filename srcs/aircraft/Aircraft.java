package srcs.aircraft;

public abstract class Aircraft {
    public long id;
    public String name;
    public String type;
    public Coordinates coordinates;

    private static long idCounter;

    public Aircraft(String pname, Coordinates pcoordinates) {
        this.name = pname;
        this.coordinates = pcoordinates;
        this.id = nextId();
    }

    long nextId() {
        Aircraft.idCounter++;
        return (Aircraft.idCounter - 1);
    }
    
    public long getId() { return (id); }
    public String getName() { return (name); }
    public String getType() { return (type); }
    public Coordinates getCoordinates() { return (coordinates); }
}