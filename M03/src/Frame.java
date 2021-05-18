import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Frame extends JFrame {

    private int width=1920;
    private int height=1080;


    private JPanel cards = new JPanel(new CardLayout());
    private MainMenuPN mainMenuPanel= new MainMenuPN();
    private PlayPN playPN= new PlayPN();
    private ChangeCharacterPN changeCharacter;
    private ChangeWeaponPN changeWeapon= new ChangeWeaponPN();
    private OptionsPN options= new OptionsPN();
    private RankPN rankPN=new RankPN();
    private String player_id;

    public void newRankPN(){
        cards.remove(rankPN);
        rankPN=new RankPN();
        cards.add(rankPN,"Ranking");
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



        changeCharacter= new ChangeCharacterPN();
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

    public String getUserName() {

        UIManager UI=new UIManager();
        UI.put("OptionPane.background",Color.black );
        UI.put("OptionPane.messageForeground", Color.white);
        UI.put("Panel.background", Color.black);
        return JOptionPane.showInputDialog(rootPane,"What's your name?","EnterName",JOptionPane.QUESTION_MESSAGE).toUpperCase(Locale.ROOT);
    }
}
