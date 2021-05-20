public class Hilo extends Thread{

    public Hilo() {
    }

    public Hilo(String name) {
        super(name);
    }

    @Override
    public void run() {
        /* Creates a new Frame, Iniciating the game */
        new Frame();
    }
}
