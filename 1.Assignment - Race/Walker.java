package race;

public class Walker extends Creature {

    public Walker(String name, int water) {
        super(name, water, 12);
    }

    @Override
    public void sunny() {
        if (isAlive()) {
            changeWater(-2);
            walk(1);
        }
    }

    @Override
    protected void cloudy(){
        if (isAlive()) {
            changeWater(-1);
            walk(2);
        }
    };

    @Override
    protected void rainy(){
        if (isAlive()) {
            changeWater(3);
            walk(1);
        }
    };
}
