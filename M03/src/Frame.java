import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class Frame extends JFrame {

    private int width = 1920;
    private int height = 1080;

    private String username;

    private JPanel cards;
    private MainMenuPN mainMenuPanel;
    private PlayPN playPN;
    private ChangeCharacterPN changeCharacter;
    private ChangeWeaponPN changeWeapon;
    private RankPN rankPN;
    private String player_id;
    private static Connect mySqlCon;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static void setMySqlCon(Connect mySqlCon) {
        Frame.mySqlCon = mySqlCon;
    }

    public static Connect getMySqlCon() {
        return mySqlCon;
    }

    public void newRankPN() {
        cards.remove(rankPN);
        rankPN = new RankPN();
        cards.add(rankPN, "Ranking");
    }

    public Frame() {
        Main.musica("MainMenu");

        this.setSize(width, height);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("Juego");
        this.setIconImage(new ImageIcon("./media/mainMenuLogo.png").getImage());
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        cards = new JPanel(new CardLayout());
        mainMenuPanel = new MainMenuPN();
        playPN = new PlayPN();
        changeWeapon = new ChangeWeaponPN();
        rankPN = new RankPN();
        changeCharacter = new ChangeCharacterPN();

        cards.add(mainMenuPanel, "MainMenu");
        cards.add(playPN, "PeleaPanel");
        cards.add(changeCharacter, "ChangeCharacter");
        cards.add(changeWeapon, "ChangeWeapon");
        cards.add(rankPN, "Ranking");

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
