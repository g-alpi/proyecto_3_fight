import java.util.ArrayList;

public class Warriors {
    //declare variables
    private Frame framePrincipal = (Frame) Frame.getFrames()[0];
    private ArrayList<Warrior> warriors = new ArrayList<Warrior>();

    public void setWarriors(ArrayList<Warrior> warriors) {
        this.warriors = warriors;
    }

    Connect conection=framePrincipal.getMySqlCon();
    public Warriors(){
        //getting the warriors from the database
        conection.downloadWarriors(this);

    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }
}
