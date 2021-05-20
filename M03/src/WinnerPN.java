import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class WinnerPN extends JPanel {
    private User winner;
    private User loser;
    private Image backgroundImg;
    private Font font;
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

        if (player.getWarrior().getRace().getHealth()<=0){
            winner=enemy;
            loser=player;
        } else{
            winner=player;
            loser=enemy;
        }

        JPanel textPN = new JPanel();
        textPN.setLayout(new GridLayout(3,0));

        String wins = winner.getWarrior().getName()+" wins";
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

        ImageIcon winnerGif = new ImageIcon("./warriors/dance_"+winner.getWarrior().getName()+".gif");
        ImageIcon loserGif = new ImageIcon("./warriors/cry_"+loser.getWarrior().getName()+".gif");

        JLabel winL = new JLabel(winnerGif);
        warriors.add(winL);

        JLabel losL = new JLabel(loserGif);
        warriors.add(losL);

        JPanel blank2 = new JPanel();
        blank2.setOpaque(false);
        warriors.add(blank2);


        JPanel buttons = new JPanel();

        JButton goAgain = new JButton();
        JButton rank=new JButton();

        buttons.add(goAgain);
        buttons.add(rank);
        buttons.setOpaque(false);

        this.add(text,BorderLayout.NORTH);
        this.add(warriors,BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

        warriors.setOpaque(false);



        goAgain.setIcon(new ImageIcon("./media/button_replay.png"));
        goAgain.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        goAgain.setIcon(new ImageIcon("./media/button_replay-hover.png"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        goAgain.setIcon(new ImageIcon("./media/button_replay.png"));
                    }
                }
        );

        goAgain.setBorder(null);
        goAgain.setContentAreaFilled(false);
        goAgain.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PlayPN playPanel= framePrincipal.getPlayPN();
                        /* Change panel to figth */
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(), "PeleaPanel");


                        /* Reset enemy warrior */
                        playPanel.setEnemyWarrior(playPanel.randomEnemy());
                        while (playPanel.getPlayerWarrior().getName().equalsIgnoreCase(playPanel.getEnemyWarrior().getName())){
                            playPanel.setEnemyWarrior(playPanel.randomEnemy());
                        }



                        /* Clean the console */
                        playPanel.getConsole().setText("");

                        /* Reset player health */
                        switch (playPanel.getPlayerUser().getWarrior().getRace().getName()){
                            case "human":
                                playPanel.getPlayerUser().getWarrior().setRace(framePrincipal.getMySqlCon().getHuman());
                                break;
                            case "dwarf":
                                playPanel.getPlayerUser().getWarrior().setRace(framePrincipal.getMySqlCon().getDwarf());
                                break;
                            case "elf":
                                playPanel.getPlayerUser().getWarrior().setRace(framePrincipal.getMySqlCon().getElf());
                                break;
                        }

                        playPanel.getCharacterImage().setIcon(new ImageIcon(playPanel.getPlayerUser().getWarrior().getBstandLoop()));

                        /* Reset health bars */
                        playPanel.setHealthBars();

                        playPanel.getFigthBT().setVisible(true);

                        playPanel.setImgBackground();

                        /* Set music for new panel */
                        Main.musica("Figth");
                    }
                }
        );



        rank.setIcon(new ImageIcon("./media/button_ranking.png"));

        rank.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        rank.setIcon(new ImageIcon("./media/button_ranking-hover.png"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        rank.setIcon(new ImageIcon("./media/button_ranking.png"));
                    }
                }
        );

        rank.setBorder(null);
        rank.setContentAreaFilled(false);
        rank.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        framePrincipal.newRankPN();
                        PlayPN playPanel= (PlayPN) framePrincipal.getCards().getComponents()[1];
                        /* Change panel to figth */
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(), "Ranking");
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
