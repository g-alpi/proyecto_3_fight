public class Warrior {
    String name;
    Race race;
    Weapon weapon;
    String portrait;
    String FstandLoop;
    String BstandLoop;
    String Fattack;
    String Battack;
    String Fattack_bow;
    String Battack_bow;
    String Fdie;
    String Bdie;
    String Fwound;
    String Bwound;
    String Fdodge;
    String Bdodge;
    String dance;
    String cry;
    String portraitGif;

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

    public int getTrueSpeed(){
        return this.race.getSpeed()+this.weapon.getSpeedBonus();
    }
    public int getTrueStrength(){
        return this.race.getStrenght()+this.weapon.getStrenghtBonus();
    }

}
