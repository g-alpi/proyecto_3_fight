import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RankPN extends JPanel {
    Frame framePrincipal = (Frame) Frame.getFrames()[0];
    ArrayList ranks;
    Image backgroundImg;
    Image backgroundImgRank;
    Font font;

    public RankPN(){

        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("./media/CloisterBlack.ttf")).deriveFont(45f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./media/CloisterBlack.ttf")));
        }
        catch(IOException | FontFormatException e){
        }

        Frame framePrincipal = (Frame) Frame.getFrames()[0];
        ImageIcon icon = new ImageIcon("./media/backRank.png");
        backgroundImg = icon.getImage().getScaledInstance(framePrincipal.getWidth(),framePrincipal.getHeight(),Image.SCALE_SMOOTH);

        ranks=framePrincipal.getMySqlCon().getRanking();

        JPanel tit = new JPanel();
        tit.setLayout(new GridLayout(3,1));
        JPanel blank = new JPanel();
        JPanel blank2 = new JPanel();
        blank.setOpaque(false);
        blank2.setOpaque(false);
        tit.add(blank);
        tit.add(blank2);
        JLabel title = new JLabel("RANKING");
        title.setFont(font);
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        tit.add(title);

        JPanel ranking = new JPanel();

        ranking.setLayout(new GridLayout(0,6));

        int cont=0;
        String[] pos = {"1ST","2ND","3RD","4TH","5TH","6TH","7TH","8TH","9TH","10TH"};
        for (Object rank:ranks){
            rank= (ArrayList) rank;
            JLabel position = new JLabel(pos[cont]);
            position.setHorizontalAlignment(SwingConstants.CENTER);
            position.setFont(font);
            position.setForeground(Color.BLACK);
            JLabel name = new JLabel((String) ((ArrayList<?>) rank).get(0));
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(font);
            name.setForeground(Color.BLACK);
            JLabel points = new JLabel((String) ((ArrayList<?>) rank).get(1));
            points.setHorizontalAlignment(SwingConstants.CENTER);
            points.setFont(font);
            points.setForeground(Color.BLACK);
            JLabel warrior = new JLabel((String) ((ArrayList<?>) rank).get(2));
            warrior.setHorizontalAlignment(SwingConstants.CENTER);
            warrior.setFont(font);
            warrior.setForeground(Color.BLACK);

            ranking.add(new JLabel());
            ranking.add(position);
            ranking.add(name);
            ranking.add(points);
            ranking.add(warrior);
            ranking.add(new JLabel());
            cont++;
        }

        tit.setOpaque(false);
        ranking.setOpaque(false);
        this.setLayout(new BorderLayout());


        JPanel tables = new JPanel();
        tables.setLayout(new BorderLayout());

        ArrayList dmgTaken = framePrincipal.getMySqlCon().getMostDmgTaken();
        ArrayList dmgDealt = framePrincipal.getMySqlCon().getMostDmgDealt();

        JPanel damages = new JPanel();
        damages.setLayout(new GridLayout(0,2));
        JPanel damageTaken = new JPanel();
        damageTaken.setLayout(new GridLayout(2,0));

        JPanel damageDealt = new JPanel();
        damageDealt.setLayout(new GridLayout(2,0));

        damageTaken.setOpaque(false);
        damageDealt.setOpaque(false);

        JLabel titT=new JLabel("Most damage taken");
        titT.setFont(font);
        titT.setForeground(Color.BLACK);
        titT.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel udT=new JLabel(dmgTaken.get(0)+" "+dmgTaken.get(1));
        udT.setFont(font);
        udT.setForeground(Color.BLACK);
        udT.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel titD=new JLabel("Most damage dealt");
        titD.setFont(font);
        titD.setForeground(Color.BLACK);
        titD.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel udD=new JLabel(dmgDealt.get(0)+" "+dmgDealt.get(1));
        udD.setFont(font);
        udD.setForeground(Color.BLACK);
        udD.setHorizontalAlignment(SwingConstants.CENTER);
        titT.setOpaque(false);
        udT.setOpaque(false);
        titD.setOpaque(false);
        udD.setOpaque(false);
        damageTaken.add(titT);
        damageDealt.add(titD);
        damageTaken.add(udT);
        damageDealt.add(udD);
        damages.add(damageDealt);
        damages.add(damageTaken);

        damages.setOpaque(false);
        tables.setOpaque(false);

        //tables.setOpaque(true);
        tables.add(tit,BorderLayout.NORTH);
        tables.add(ranking,BorderLayout.CENTER);
        tables.add(damages,BorderLayout.SOUTH);

        this.add(tables,BorderLayout.CENTER);

        JButton mainMenu = new JButton();

        mainMenu.setBorder(null);
        mainMenu.setContentAreaFilled(false);
        mainMenu.setIcon(new ImageIcon("./media/button_main-menu.png"));

        mainMenu.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mainMenu.setIcon(new ImageIcon("./media/button_main-menu-hover.png"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        mainMenu.setIcon(new ImageIcon("./media/button_main-menu.png"));
                    }
                }
        );

        mainMenu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"MainMenu");
                        Main.musica("MainMenu");
                    }
                }
        );
        this.add(mainMenu,BorderLayout.SOUTH);


        this.setVisible(true);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImg,0,0,this);
    }
}
