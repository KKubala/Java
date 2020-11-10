package race;

public class Sponge extends Creature {

    public Sponge(String name, int water) {
        super(name, water, 20);
    }

    @Override
    protected void sunny() {
        if (isAlive()) {
            changeWater(-4);
        }
    }

    @Override
    protected void cloudy(){
        if (isAlive()) {
            changeWater(-1);
            walk(1);
        }
    };

    @Override
    protected void rainy(){
        if (isAlive()) {
            changeWater(6);
            walk(3);
        }
    };
}
