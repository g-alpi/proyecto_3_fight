public class Race {
    //declaring variables
    String name;
    int health;
    int strenght;
    int defence;
    int agility;
    int speed;
    int points;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getDefence() {
        return defence;
    }

    public int getAgility() {
        return agility;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Race(){};
    public Race(String name, int health, int strenght, int defence, int agility, int speed, int points) {
        this.name = name;
        this.health = health;
        this.strenght = strenght;
        this.defence = defence;
        this.agility = agility;
        this.speed = speed;
        this.points = points;
    }
}
