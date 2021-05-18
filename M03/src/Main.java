
public class Main {

    static HiloTwo musicaTH = new HiloTwo();

    public static void main(String[] args) {

        Thread hilo = new Hilo("Juego");
        hilo.start();


    }

    public static void musica(String songName){
        musicaTH.stop();
        musicaTH = new HiloTwo();
        musicaTH.setCancion(songName);
        musicaTH.start();
    }

}
