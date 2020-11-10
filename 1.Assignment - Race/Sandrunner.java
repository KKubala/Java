package race;

public class Sandrunner extends Creature {

    public Sandrunner(String name, int water) {
        super(name, water, 8);
    }

    @Override
    public void sunny() {
        if (isAlive()) {
            changeWater(-1);
            walk(3);
        }
    }

    @Override
    protected void cloudy(){
        if (isAlive()) {
            walk(1);
        }
    };

    @Override
    protected void rainy(){
        if (isAlive()) {
            changeWater(3);
        }
    };
}
