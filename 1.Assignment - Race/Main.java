package race;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("What is the name of the file? ");
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();

        Database database = new Database();
        try {
            database.read(data);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }

        String days = database.getDays();
        ArrayList<Creature> creature_list = database.getCreatureList(); 

        Race race = new Race(creature_list, days);
        race.startRace();
        ArrayList<Creature> winners = race.getWinners();

        if (winners.size() > 0){

            for (Creature w : winners) {
                System.out.print(w.getName() + " is alive and his distance is: ");
                System.out.println(w.getDistance());
            }
        }
        else{
            System.out.println("No winner!");
        }

        
    }
}
