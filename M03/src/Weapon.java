import java.util.ArrayList;

public class Weapon {
    String name;
    int speedBonus;
    int strenghtBonus;
    String image;
    ArrayList<Race> wielders = new ArrayList<Race>();
    int points;

    public String getName() {
        return name;
    }

    public int getSpeedBonus() {
        return speedBonus;
    }

    public int getStrenghtBonus() {
        return strenghtBonus;
    }

    public String getImage() {
        return image;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Race> getWielders() {
        return wielders;
    }

    public Weapon(String name, int speedBonus, int strenghtBonus, String image, ArrayList<Race> wielders, int points) {
        this.name = name;
        this.speedBonus = speedBonus;
        this.strenghtBonus = strenghtBonus;
        this.image = image;
        this.wielders = wielders;
        this.points = points;
    }
}
