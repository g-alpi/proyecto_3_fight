import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HiloTwo extends Thread{

    private static String cancion = "";

    public HiloTwo() {
    }

    public HiloTwo(String name) {
        super(name);
    }


    public void run() {
        while (true) {
            try {
                String path = "";
                Musica musicas = new Musica();

                for (int i = 0; i < musicas.getEntornos().size(); i++) {
                    if (musicas.getEntornos().get(i).equalsIgnoreCase(this.cancion)) {
                        path = musicas.getPaths().get(i);
                        break;
                    }
                }
                Player apl = new Player(new FileInputStream(path));
                apl.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

}
