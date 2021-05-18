import java.util.ArrayList;

public class Musica {

    private ArrayList<String> paths = new ArrayList<String>();
    private ArrayList<String> entornos = new ArrayList<String>();


    public Musica() {
        entornos.add("MainMenu");
        paths.add("./media/MenuTheme.mp3");
        entornos.add("ChangeCharacter");
        paths.add("./media/ChangeCharacterTheme.mp3");
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public ArrayList<String> getEntornos() {
        return entornos;
    }
}
