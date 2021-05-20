public class User {
    //declaring variables
    private String name;
    private Warrior warrior;
    private int points;
    private int injuriesCaused;
    private int injuriesSuffered;

    public int getInjuriesCaused() {
        return injuriesCaused;
    }

    public void setInjuriesCaused(int injuriesCaused) {
        this.injuriesCaused = injuriesCaused;
    }

    public int getInjuriesSuffered() {
        return injuriesSuffered;
    }

    public void setInjuriesSuffered(int injuriesSuffered) {
        this.injuriesSuffered = injuriesSuffered;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public void setWarrior(Warrior warrior) {
        this.warrior = warrior;
    }

    public User(String name, Warrior warrior) {
        this.name = name;
        this.warrior = warrior;
        points=0;
    }
}
