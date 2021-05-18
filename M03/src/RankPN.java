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
    ArrayList ranks;
    Image backgroundImg;
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
        ImageIcon icon = new ImageIcon("./media/mainMenuBakground_1920_1080.jpg");
        backgroundImg = icon.getImage().getScaledInstance(framePrincipal.getWidth(),framePrincipal.getHeight(),Image.SCALE_SMOOTH);

        ranks=Connect.getRanking("root1","root1");

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
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        tit.add(title);

        JPanel ranking = new JPanel();
        ranking.setLayout(new GridLayout(0,6));

        int cont=0;
        String[] pos = {"1ST","2ND","3RD","4TH","5TH"};
        for (Object rank:ranks){
            if (cont>4){
                break;
            }
            rank= (ArrayList) rank;
            JLabel position = new JLabel(pos[cont]);
            position.setHorizontalAlignment(SwingConstants.CENTER);
            position.setFont(font);
            position.setForeground(Color.WHITE);
            JLabel name = new JLabel((String) ((ArrayList<?>) rank).get(0));
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setFont(font);
            name.setForeground(Color.WHITE);
            JLabel points = new JLabel((String) ((ArrayList<?>) rank).get(1));
            points.setHorizontalAlignment(SwingConstants.CENTER);
            points.setFont(font);
            points.setForeground(Color.WHITE);
            JLabel warrior = new JLabel((String) ((ArrayList<?>) rank).get(2));
            warrior.setHorizontalAlignment(SwingConstants.CENTER);
            warrior.setFont(font);
            warrior.setForeground(Color.WHITE);

            ranking.add(new JLabel());
            ranking.add(position);
            ranking.add(name);
            ranking.add(points);
            ranking.add(warrior);
            ranking.add(new JLabel());
            cont++;
        }
        for (int i=0; i < 18; ++i){
            ranking.add(new JLabel());
        }

        tit.setOpaque(false);
        ranking.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(tit,BorderLayout.NORTH);
        this.add(ranking,BorderLayout.CENTER);

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
