package race;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private final ArrayList<Creature> creatures_list;
    protected String days;


    public Database() {
        creatures_list = new ArrayList<>();
        String days;
    }

    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numCreatures = sc.nextInt();
        for (int i = 0; i < numCreatures; i++) {
            Creature creature;
            String name = sc.next();
            switch (sc.next()) {
                case "r":
                    creature = new Sandrunner(name, sc.nextInt());
                    break;
                case "w":
                    creature = new Walker(name, sc.nextInt());
                    break;
                case "s":
                    creature = new Sponge(name, sc.nextInt());
                    break;
                default:
                    throw new InvalidInputException();
            }
        
            creatures_list.add(creature);
        }
        days = sc.next();
    }
    public String getDays() {
        return days;
    }

    public ArrayList<Creature> getCreatureList() {
        return creatures_list;
    }
    
    public void clear() {
        creatures_list.clear();
    }

}
