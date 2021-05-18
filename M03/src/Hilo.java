public class Hilo extends Thread{

    public Hilo() {
    }

    public Hilo(String name) {
        super(name);
    }

    @Override
    public void run() {
        new Frame();
    }
}
