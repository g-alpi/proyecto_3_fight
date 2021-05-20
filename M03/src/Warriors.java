import java.util.ArrayList;

public class Warriors {
    private Frame framePrincipal = (Frame) Frame.getFrames()[0];
    private ArrayList<Warrior> warriors = new ArrayList<Warrior>();

    public void setWarriors(ArrayList<Warrior> warriors) {
        this.warriors = warriors;
    }

    Connect conection=framePrincipal.getMySqlCon();
    public Warriors(){

        conection.downloadWarriors(this);

    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }
}
