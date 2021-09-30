package fr.kronx12.simulator;

import fr.kronx12.aircraft.AircraftFactory;
import fr.kronx12.aircraft.InvalidTypeException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {
    public static final String regex_header = "^[0-9]+$";
    public static final String regex_md5 = "^[a-f0-9]{32} [A-Za-z][0-9] [0-9]+ [0-9]+ [0-9]+$";
    public static final String regex_normal = "^[a-zA-Z]+ [A-Za-z][0-9] [0-9]+ [0-9]+ [0-9]+$";
    public static final String helicopter_hash = "2ab8b43468e8b92b0fc5c81e70e35a2d";
    public static final String jetplane_hash = "554cd647d6b135f7e36ab1214c5e816a";
    public static final String baloon_hash = "994736b4f0aec72f6e5ae580051d012f";

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        int loop = 0;

        // Arguments Error
        if (args.length != 1) {
            System.out.println("Invalid arguments count !");
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Parsing Error
        for (int i = 0; i < lines.size(); i++)
        {
            if (i == 0 && lines.get(i).matches(regex_header)) {
                loop = Integer.parseInt(lines.get(i));
            } else if (i != 0) {
                if (!lines.get(i).matches(regex_normal) && !lines.get(i).matches(regex_md5)) {
                    System.out.printf("Bad line format at line (%d) [TYPE NAME LONGITUDE LATITUDE HEIGHT]!\n", i);
                    System.exit(1);
                } else if (lines.get(i).matches(regex_md5) &&
                        !lines.get(i).split(" ")[0].equals(helicopter_hash) &&
                        !lines.get(i).split(" ")[0].equals(jetplane_hash) &&
                        !lines.get(i).split(" ")[0].equals(baloon_hash)) {
                    System.out.printf("No Matching Type at line (%d) !\n", i + 1);
                    System.exit(1);
                } else if (lines.get(i).matches(regex_normal) &&
                        !lines.get(i).split(" ")[0].equals("Helicopter") &&
                        !lines.get(i).split(" ")[0].equals("JetPlane") &&
                        !lines.get(i).split(" ")[0].equals("Baloon")) {
                    System.out.printf("No Matching Type at line (%d) !\n", i + 1);
                    System.exit(1);
                }
            } else {
                System.out.printf("Invalid file format at line (%d) !\n", i + 1);
                return ;
            }
        }

        // Run
        simulation(lines, loop);
    }

    static void simulation(ArrayList<String> lines, int loop) {
        WeatherTower tower = new WeatherTower();

        System.setOut(outputFile());

        for (String line : lines) {
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case helicopter_hash:
                case "Helicopter": {
                    try {
                        tower.register(AircraftFactory.newAircraft("Helicopter", tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                    } catch (NumberFormatException | InvalidTypeException e) { e.printStackTrace(); }
                    break;
                }
                case jetplane_hash:
                case "JetPlane": {
                    try {
                        tower.register(AircraftFactory.newAircraft("JetPlane", tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                    } catch (NumberFormatException | InvalidTypeException e) { e.printStackTrace(); }
                    break;
                }
                case baloon_hash:
                case "Baloon": {
                    try {
                        tower.register(AircraftFactory.newAircraft("Baloon", tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                    } catch (NumberFormatException | InvalidTypeException e) { e.printStackTrace(); }
                    break;
                }
            }
        }
        for (int i = 0; i < loop; i++)
            tower.changeWeather();
    }

    protected static PrintStream outputFile() {
        try {
            return new PrintStream(new BufferedOutputStream(new FileOutputStream("simulation.txt")), true);
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        return (null);
    }
}
