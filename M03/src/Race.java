public class Race {
    String name;
    int health;
    int strenght;
    int defence;
    int agility;
    int speed;

    public void setHealth(int health) {
        this.health = health;
    }

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

    public Race(){};
    public Race(String name, int health, int strenght, int defence, int agility, int speed) {
        this.name = name;
        this.health = health;
        this.strenght = strenght;
        this.defence = defence;
        this.agility = agility;
        this.speed = speed;
    }

    public Race getDwarf(){
        return new Race("Dwarf",60,6,4,5,3);
    };
    public Race getHuman(){
        return new Race("Human",50,5,3,6,5);
    };
    public Race getElf(){
        return new Race("Elf",40,4,2,7,7);
    };



}
