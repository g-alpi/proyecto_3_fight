import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class WinnerPN extends JPanel {
    User winner;
    User loser;
    Image backgroundImg;
    Font font;
    public WinnerPN(){

        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("./media/CloisterBlack.ttf")).deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./media/CloisterBlack.ttf")));
        }
        catch(IOException | FontFormatException e){
        }


        this.setLayout(new BorderLayout());

        Frame framePrincipal = (Frame) Frame.getFrames()[0];
        ImageIcon icon = new ImageIcon("./media/WinnerBackground.jpg");
        backgroundImg = icon.getImage().getScaledInstance(framePrincipal.getWidth(),framePrincipal.getHeight(),Image.SCALE_SMOOTH);

        Warriors warri = new Warriors();
        //User player=new User("juan",warri.warriors.get(1));
        //User enemy=new User("juan",warri.warriors.get(2));
        //enemy.warrior.race.setHealth(0);
        User player=framePrincipal.getPlayPN().getPlayerUser();
        User enemy=framePrincipal.getPlayPN().getEnemyUser();

        if (player.warrior.race.getHealth()==0){
            winner=enemy;
            loser=player;
        } else{
            winner=player;
            loser=enemy;
        }

        JPanel textPN = new JPanel();
        textPN.setLayout(new GridLayout(3,0));

        String wins = winner.warrior.getName()+" wins";
        wins=wins.toLowerCase(Locale.ROOT);
        JLabel text = new JLabel(wins);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(font);
        text.setForeground(Color.BLACK);

        JLabel blank3 = new JLabel();
        blank3.setOpaque(false);
        textPN.add(blank3);
        JLabel blank4 = new JLabel();
        blank4.setOpaque(false);
        textPN.add(blank4);
        textPN.add(text);
        textPN.setOpaque(false);

        JPanel warriors = new JPanel();
        warriors.setLayout(new GridLayout(1,4));

        JPanel blank = new JPanel();
        blank.setOpaque(false);
        warriors.add(blank);

        ImageIcon winnerGif = new ImageIcon("./warriors/dance_"+winner.warrior.getName()+".gif");
        ImageIcon loserGif = new ImageIcon("./warriors/cry_"+loser.warrior.getName()+".gif");

        JLabel winL = new JLabel(winnerGif);
        warriors.add(winL);

        JLabel losL = new JLabel(loserGif);
        warriors.add(losL);

        JPanel blank2 = new JPanel();
        blank2.setOpaque(false);
        warriors.add(blank2);


        JPanel buttons = new JPanel();

        JButton goAgain = new JButton("go Again");
        JButton rank=new JButton("Ranking");

        buttons.add(goAgain);
        buttons.add(rank);
        buttons.setOpaque(false);

        this.add(text,BorderLayout.NORTH);
        this.add(warriors,BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

        warriors.setOpaque(false);

        goAgain.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PlayPN playPanel= (PlayPN) framePrincipal.getCards().getComponents()[1];
                        /* Change panel to figth */
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(), "PeleaPanel");

                        /* Set music for new panel */
                        Main.musica("ChangeCharacter");
                    }
                }
        );
        rank.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PlayPN playPanel= (PlayPN) framePrincipal.getCards().getComponents()[1];
                        /* Change panel to figth */
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(), "RankPN");
                    }
                }
        );

        this.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImg,0,0,this);
    }
}
