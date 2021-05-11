package srcs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import srcs.aircraft.AircraftFactory;
import srcs.exceptions.InvalidTypeException;
import srcs.weather.WeatherTower;

public class Main extends AircraftFactory {
	public static final String regex_header = "^[0-9]{1,}$";
	public static final String regex_md5 = "^[a-f0-9]{32} [A-Za-z]{1}[0-9]{1} [0-9]{1,} [0-9]{1,} [0-9]{1,}$";
    public static final String regex_normal = "^[a-zA-Z]{1,} [A-Za-z]{1}[0-9]{1} [0-9]{1,} [0-9]{1,} [0-9]{1,}$";
    public static final String helicopter_hash = "2ab8b43468e8b92b0fc5c81e70e35a2d";
    public static final String jetplane_hash = "554cd647d6b135f7e36ab1214c5e816a";
    public static final String baloon_hash = "994736b4f0aec72f6e5ae580051d012f";
    
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
        int loop = 0;
        
        // Arguments Error
        if (args.length != 1) {
        	System.err.println("Invalid arguments count !");
        	return ;
        }
        
        // Open/Read Error
        try {
        	File file = new File(args[0]);
        	Scanner Reader = new Scanner(file);
	        while (Reader.hasNextLine())
	        	lines.add(Reader.nextLine());
	        Reader.close();
        } catch (FileNotFoundException e) {
        	System.err.println("An error occurred.");
        	e.printStackTrace();
        }
        
        // Parsing Error
        for (int i = 0; i < lines.size(); i++)
        {
        	if (i == 0 && lines.get(i).matches(regex_header)) {
        		loop = Integer.parseInt(lines.get(i));
        	} else if (i != 0) {
        		if ((lines.get(i).matches(regex_md5) &&
	        		!lines.get(i).split(" ")[0].equals(helicopter_hash) &&
	        		!lines.get(i).split(" ")[0].equals(jetplane_hash) &&
	        		!lines.get(i).split(" ")[0].equals(baloon_hash)) ||
	        		(lines.get(i).matches(regex_normal) &&
        			!lines.get(i).split(" ")[0].equals("Helicopter") &&
        			!lines.get(i).split(" ")[0].equals("JetPlane") &&
        			!lines.get(i).split(" ")[0].equals("Baloon"))) {
        			System.err.printf("No Matching Type at line (%d) !\n", i);
        			return ;
        		}
        	} else {
            	System.err.printf("Invalid file format at line (%d) !\n", i);
            	return ;
            }
        }
        
        // Run
        simulation(lines, loop);
    }
    
    static void simulation(ArrayList<String> lines, int loop) {
    	WeatherTower tower = new WeatherTower();
    	
    	System.setOut(outputFile("simulation.txt"));
    	
    	for (String line : lines) {
			String[] tokens = line.split(" ");
			switch (tokens[0]) {
				case helicopter_hash:
				case "Helicopter": {
					try {
						tower.register(newAircraft("Helicopter", tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
					} catch (NumberFormatException e) { 
						e.printStackTrace(); 
					} catch (InvalidTypeException e) { 
						e.printStackTrace(); 
					}
					break;
				} case jetplane_hash:
				case "JetPlane": {
					try {
						tower.register(newAircraft("JetPlane", tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
					} catch (NumberFormatException e) { 
						e.printStackTrace(); 
					} catch (InvalidTypeException e) { 
						e.printStackTrace(); 
					}
					break;
				} case baloon_hash:
				case "Baloon": {
					try {
						tower.register(newAircraft("Baloon", tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
					} catch (NumberFormatException e) { 
						e.printStackTrace(); 
					} catch (InvalidTypeException e) { 
						e.printStackTrace(); 
					}
					break;
				} 
			}
		}
    	for (int i = 0; i < loop; i++)
        	tower.changeWeather();
    }
    
    protected static PrintStream outputFile(String name) {
        try {
			return new PrintStream(new BufferedOutputStream(new FileOutputStream(name)), true);
		} catch (FileNotFoundException e) { e.printStackTrace(); }
        return (null);
    }
}
