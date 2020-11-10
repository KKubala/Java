package race;

public abstract class Creature {

    protected String name;
    protected int water;
    protected int distance;
    protected int maxWater;


    public Creature(String name, int water, int maxWater) {
        this.name = name;
        this.water = water;
        this.distance = 0;
        this.maxWater = maxWater;
    }

    protected void walk(int dis) {
        if (isAlive()) {
            distance += dis;
        }
    }

    protected void sunny(){};

    protected void cloudy(){};

    protected void rainy(){};

    protected boolean isAlive() {
        return water > 0;
    }

    protected void changeWater(int w) {
        water += w;
        if(water>maxWater)
        {
            water = maxWater;
        }
    }

    public String getName() {
        return name;
    }

    public int getWater() {
        return water;
    }

    public int getDistance() {
        return distance;
    }

}
