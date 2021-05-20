import java.util.ArrayList;

public class Weapons {
    private Frame framePrincipal = (Frame) Frame.getFrames()[0];
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    Connect conection = framePrincipal.getMySqlCon();
    public Weapons(){
        conection.downloadWeapons(this);
    }

}
