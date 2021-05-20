import java.util.ArrayList;

public class Musica {

    /* Create two arraylist, one with the paths for the mp3 file and the other whit the names of the song */
    private ArrayList<String> paths = new ArrayList<String>();
    private ArrayList<String> entornos = new ArrayList<String>();


    public Musica() {
        /* Adding songs */
        entornos.add("MainMenu");
        paths.add("./media/MenuTheme.mp3");
        entornos.add("ChangeCharacter");
        paths.add("./media/ChangeCharacterTheme.mp3");
        entornos.add("Figth");
        paths.add("./media/FigthTheme.mp3");
        entornos.add("Ranking");
        paths.add("./media/RankingTheme.mp3");
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public ArrayList<String> getEntornos() {
        return entornos;
    }
}
