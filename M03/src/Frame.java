import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private int width=1920;
    private int height=1080;


    private JPanel cards = new JPanel(new CardLayout());
    private MainMenuPN mainMenuPanel= new MainMenuPN();
    private PlayPN playPN = new PlayPN();
    private ChangeCharacterPN changeCharacter= new ChangeCharacterPN();
    private ChangeWeaponPN changeWeapon= new ChangeWeaponPN();
    private OptionsPN options= new OptionsPN();
    private RankPN rankPN=new RankPN();

    public void newRankPN(){
        rankPN=new RankPN();
    }


    public Frame() {

        Main.musica("MainMenu");

        this.setSize(width,height);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("Juego");
        this.setIconImage(new ImageIcon("./media/mainMenuLogo.png").getImage());
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cards.add(mainMenuPanel,"MainMenu");
        cards.add(playPN,"PeleaPanel");
        cards.add(changeCharacter,"ChangeCharacter");
        cards.add(changeWeapon,"ChangeWeapon");
        cards.add(rankPN,"Ranking");
        //cards.add(options,"Options");



        this.add(cards);

        this.setVisible(true);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JPanel getCards() {
        return cards;
    }

    public ChangeWeaponPN getChangeWeapon() {
        return changeWeapon;
    }

    public PlayPN getPlayPN() {
        return playPN;
    }
}
