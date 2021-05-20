public class Warrior {
    //declaring variables
    private String name;
    private Race race;
    private Weapon weapon;
    private String portrait;
    private String FstandLoop;
    private String BstandLoop;
    private String Fattack;
    private String Battack;
    private String Fattack_bow;
    private String Battack_bow;
    private String Fdie;
    private String Bdie;
    private String Fwound;
    private String Bwound;
    private String Fdodge;
    private String Bdodge;
    private String dance;
    private String cry;
    private String portraitGif;

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getFstandLoop() {
        return FstandLoop;
    }

    public String getBstandLoop() {
        return BstandLoop;
    }

    public String getFattack() {
        return Fattack;
    }

    public String getBattack() {
        return Battack;
    }

    public String getFattack_bow() {
        return Fattack_bow;
    }

    public String getBattack_bow() {
        return Battack_bow;
    }

    public String getFdie() {
        return Fdie;
    }

    public String getBdie() {
        return Bdie;
    }

    public String getFwound() {
        return Fwound;
    }

    public String getBwound() {
        return Bwound;
    }

    public String getFdodge() {
        return Fdodge;
    }

    public String getBdodge() {
        return Bdodge;
    }

    public String getDance() {
        return dance;
    }

    public String getCry() {
        return cry;
    }

    public String getPortraitGif() {
        return portraitGif;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Warrior(String name, Race race, Weapon weapon, String portrait, String portraitGif, String fstandLoop, String bstandLoop, String fattack, String battack, String fattack_bow, String battack_bow, String fdie, String bdie, String fwound, String bwound, String fdodge, String bdodge, String dance, String cry) {
        this.name = name;
        this.race = race;
        this.weapon = weapon;
        this.portrait = portrait;
        this.portraitGif = portraitGif;
        FstandLoop = fstandLoop;
        BstandLoop = bstandLoop;
        Fattack = fattack;
        Battack = battack;
        Fattack_bow = fattack_bow;
        Battack_bow = battack_bow;
        Fdie = fdie;
        Bdie = bdie;
        Fwound = fwound;
        Bwound = bwound;
        Fdodge = fdodge;
        Bdodge = bdodge;
        this.dance = dance;
        this.cry = cry;
    }
    //to get the speed and strenght of the warrior plus its weapon
    public int getTrueSpeed(){
        return this.race.getSpeed()+this.weapon.getSpeedBonus();
    }
    public int getTrueStrength(){
        return this.race.getStrenght()+this.weapon.getStrenghtBonus();
    }

}
