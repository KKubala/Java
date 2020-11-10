 package race;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Race {


    protected String days;
    protected ArrayList<Creature> participants;
    protected ArrayList<Creature> winners;


    public Race(ArrayList<Creature> participants, String days) {
        this.participants = participants;
        this.days = days;
        this.winners = new ArrayList<>();
    }

    public void startRace(){

        for (int i = 0; i < days.length(); i++) {
            char day = days.charAt(i);
            switch (day) {
                case 's':
                    for (Creature p : participants) {
                        p.sunny();
                    }
                    break;
                case 'c':
                    for (Creature p : participants) {
                        p.cloudy();
                    }
                    break;
                case 'r':
                    for (Creature p : participants) {
                        p.rainy();
                    }
                    break;
            }
        }
    }

    public ArrayList<Creature> getWinners() {
        int bestDistance = 0;
        for (Creature p : participants) {
            if(p.isAlive() && p.getDistance() > bestDistance){
                bestDistance = p.getDistance();
            }
        }
        for (Creature p : participants) {
            if(p.isAlive() && p.getDistance() == bestDistance){
                winners.add(p);
            }
        }
        return winners;
    }
        
        

}
