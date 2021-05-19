import java.util.ArrayList;

public class Warriors {
    Frame framePrincipal = (Frame) Frame.getFrames()[0];
    ArrayList<Warrior> warriors = new ArrayList<Warrior>();
    Connect conection=framePrincipal.getMySqlCon();
    public Warriors(){

        conection.downloadWarriors(this);

    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }
}
