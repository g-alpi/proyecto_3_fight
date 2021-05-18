import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;


public class ChangeCharacterPN extends JPanel {
    Frame framePrincipal = (Frame) Frame.getFrames()[0];

    ImageIcon img = new ImageIcon("./media/changeCharacterBackground.png");
    Image imgI = img.getImage().getScaledInstance(framePrincipal.getWidth(),framePrincipal.getHeight(),Image.SCALE_SMOOTH);

    int num=1;
    Warriors warriors = new Warriors();
    Warrior warrior;
    JButton confirm=new JButton();

    Icon icon;
    JLabel gif;

    JPanel gifP = new JPanel();

    JPanel gif_info = new JPanel();

    JPanel war = new JPanel();
    JPanel stats = new JPanel();

    JPanel hp = new JPanel();
    JProgressBar hpBar = new JProgressBar(0,60);

    JPanel str = new JPanel();
    JProgressBar strBar = new JProgressBar(0,7);

    JPanel df = new JPanel();
    JProgressBar dfBar = new JProgressBar(0,7);

    JPanel aglty = new JPanel();
    JProgressBar agltyBar = new JProgressBar(0,7);

    JPanel spd = new JPanel();
    JProgressBar spdBar = new JProgressBar(0,7);

    JPanel info = new JPanel();

    JPanel info_ = new JPanel();

    JLabel text = new JLabel("RACE:");

    JLabel text2;

    JLabel text3 = new JLabel(" ");

    JLabel text4 = new JLabel("NAME:");

    JLabel text5;

    public ChangeCharacterPN(){

        this.setLayout(new BorderLayout());
        warrior=warriors.warriors.get(num);
        icon = new ImageIcon(new ImageIcon(warrior.getPortraitGif()).getImage().getScaledInstance((int) (framePrincipal.getWidth()/1.5f),(int) (framePrincipal.getHeight()/1.5f),Image.SCALE_DEFAULT));

        JButton rigth=new JButton();
        JButton left=new JButton();

        left.setBorder(null);
        left.setContentAreaFilled(false);
        left.setIcon(new ImageIcon(new ImageIcon("./media/arrowL.png").getImage().getScaledInstance(framePrincipal.getWidth()/10,framePrincipal.getHeight()/10,Image.SCALE_DEFAULT)));
        left.setSize(left.getIcon().getIconWidth(),left.getIcon().getIconHeight());

        rigth.setBorder(null);
        rigth.setContentAreaFilled(false);
        rigth.setIcon(new ImageIcon(new ImageIcon("./media/arrowR.png").getImage().getScaledInstance(framePrincipal.getWidth()/10,framePrincipal.getHeight()/10,Image.SCALE_DEFAULT)));
        rigth.setSize(rigth.getIcon().getIconWidth(),rigth.getIcon().getIconHeight());

        confirm.setBorder(null);
        confirm.setContentAreaFilled(false);
        confirm.setIcon(new ImageIcon(new ImageIcon("./media/continue.png").getImage().getScaledInstance(framePrincipal.getWidth()/7,framePrincipal.getHeight()/10,Image.SCALE_DEFAULT)));
        confirm.setSize(rigth.getIcon().getIconWidth(),rigth.getIcon().getIconHeight());
        confirm.setName(warrior.getName());

        confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JButton buttonTMP = (JButton) actionEvent.getSource();

                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"ChangeWeapon");

                        PlayPN playPanel= (PlayPN) framePrincipal.getCards().getComponents()[1];
                        MainMenuPN mainMenuPanel= (MainMenuPN) framePrincipal.getCards().getComponents()[0];
                        Warrior warr=null;
                        for (Warrior warri: warriors.getWarriors()){
                            if (warri.getName().equalsIgnoreCase(buttonTMP.getName())){

                                /* Set Warrior to player */
                                warr=warri;
                                playPanel.setPlayerWarrior(warri);
                                playPanel.getCharacterImage().setIcon(new ImageIcon(new ImageIcon(playPanel.getPlayerWarrior().getBstandLoop()).getImage().getScaledInstance(framePrincipal.getWidth()-500,framePrincipal.getHeight()-300,Image.SCALE_DEFAULT)));

                                /* Change starting weapon to show in change weapon */
                                framePrincipal.getChangeWeapon().initWeapon();

                                /* Gets the usable weapons for selected warrior */
                                mainMenuPanel.setCharacterImage(warri);
                                ChangeWeaponPN.usableWeapons = new ArrayList<Weapon>();
                                for (Weapon w: ChangeWeaponPN.weapons.weapons){
                                    for (Race r:w.getWielders()){
                                        if (r.getName().equalsIgnoreCase(warri.getRace().getName())){
                                            ChangeWeaponPN.usableWeapons.add(w);
                                        }
                                    }
                                }
                                break;
                            }
                        }

                        int con =0;
                        while (playPanel.getPlayerWarrior().getName().equalsIgnoreCase(playPanel.getEnemyWarrior().getName())){
                            playPanel.setEnemyWarrior(playPanel.randomEnemy());
                            System.out.println(con);
                            System.out.println(playPanel.getEnemyWarrior().getName());
                            con++;
                        }

                        Main.musica("MainMenu");

                        framePrincipal.getPlayPN().getPlayerUser().setWarrior(warr);
                    }
                }

        );

        PlayPN playPanel= framePrincipal.getPlayPN();

        playPanel.getPlayerUser().setName(framePrincipal.getUserName());



        int width = this.getWidth();
        int height = this.getHeight();


        gif = new JLabel(icon);
        gifP.setSize((int) (gif.getIcon().getIconWidth()/1.5),gif.getIcon().getIconHeight());
        gifP.add(gif);

        gif_info.setLayout(new GridLayout(0,4));

        war.setLayout(new BorderLayout());
        stats.setLayout(new FlowLayout());

        hpBar.setValue(warrior.getRace().getHealth());
        hpBar.setStringPainted(true);
        hpBar.setString("HEALTH POINTS");
        hpBar.setForeground(Color.RED);
        hp.add(hpBar);


        strBar.setValue(warrior.getRace().getStrenght());
        strBar.setStringPainted(true);
        strBar.setString("STRENGTH");
        strBar.setForeground(Color.ORANGE);
        str.add(strBar);


        dfBar.setValue(warrior.getRace().getDefence());
        dfBar.setStringPainted(true);
        dfBar.setString("DEFENCE");
        dfBar.setForeground(Color.BLUE);
        df.add(dfBar);


        agltyBar.setValue(warrior.getRace().getAgility());
        agltyBar.setStringPainted(true);
        agltyBar.setString("AGILITY");
        agltyBar.setForeground(Color.cyan);
        aglty.add(agltyBar);


        spdBar.setValue(warrior.getRace().getSpeed());
        spdBar.setStringPainted(true);
        spdBar.setString("SPEED");
        spdBar.setForeground(Color.GREEN);
        spd.add(spdBar);

        stats.add(hp);
        stats.add(str);
        stats.add(df);
        stats.add(aglty);
        stats.add(spd);


        info.setLayout(null);



        info_.setLayout(new BoxLayout(info_, BoxLayout.Y_AXIS));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Agency FB",Font.BOLD, 30));
        info_.add(text);
        text2 = new JLabel(warrior.getRace().getName());
        text2.setForeground(Color.WHITE);
        text2.setFont(new Font("Agency FB",Font.BOLD, 40));
        info_.add(text2);
        text3 = new JLabel(" ");
        text3.setFont(new Font("Agency FB",Font.BOLD, 40));
        info_.add(text3);
        text4 = new JLabel("NAME:");
        text4.setForeground(Color.WHITE);
        text4.setFont(new Font("Agency FB",Font.BOLD, 30));
        info_.add(text4);
        text5 = new JLabel(warrior.getName());
        text5.setForeground(Color.WHITE);
        text5.setFont(new Font("Agency FB",Font.BOLD, 40));
        info_.add(text5);

        info_.setBounds((int) (info.getWidth()/2-info_.getWidth()/2)+100,gifP.getHeight()/2-info_.getHeight()/2,999999999,999999999);

        info.add(info_,BorderLayout.CENTER);

        this.setOpaque(false);

        info_.setOpaque(false);
        info.setOpaque(false);
        gifP.setOpaque(false);
        gif_info.setOpaque(false);
        war.setOpaque(false);
        this.setOpaque(false);
        stats.setOpaque(false);
        hp.setOpaque(false);
        str.setOpaque(false);
        df.setOpaque(false);
        aglty.setOpaque(false);
        spd.setOpaque(false);





        gif_info.add(new JLabel());
        gif_info.add(gifP);
        gif_info.add(info);
        gif_info.add(new JLabel());

        war.add(gif_info,BorderLayout.CENTER);
        war.add(stats,BorderLayout.SOUTH);

        this.add(rigth,BorderLayout.EAST);
        this.add(left,BorderLayout.WEST);
        this.add(confirm,BorderLayout.SOUTH);
        this.add(war,BorderLayout.CENTER);

        rigth.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        num++;
                        if (num>=6){
                            num=0;
                        }
                        init();
                    }
                }
        );
        left.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        num--;
                        if (num<0){
                            num=5;
                        }
                        init();
                    }
                }
        );


        this.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        icon = new ImageIcon(new ImageIcon(warrior.getPortraitGif()).getImage().getScaledInstance((int) (framePrincipal.getWidth()/1.5f),(int) (framePrincipal.getHeight()/1.5f),Image.SCALE_DEFAULT));
                        gif.setIcon(icon);
                    }
                }
        );


    }

    public void init(){

        warrior=warriors.warriors.get(num);
        icon = new ImageIcon(new ImageIcon(warrior.getPortraitGif()).getImage().getScaledInstance((int) (framePrincipal.getWidth()/1.5f),(int) (framePrincipal.getHeight()/1.5f),Image.SCALE_DEFAULT));

        gif.setIcon(icon);
        gifP.setSize((int) (gif.getIcon().getIconWidth()/1.5),gif.getIcon().getIconHeight());
        confirm.setName(warrior.getName());





        gif_info.setLayout(new GridLayout(0,4));

        war.setLayout(new BorderLayout());

        stats.setLayout(new FlowLayout());

        hpBar.setValue(warrior.getRace().getHealth());
        hpBar.setStringPainted(true);
        hpBar.setString("HEALTH POINTS");
        hpBar.setForeground(Color.RED);



        strBar.setValue(warrior.getRace().getStrenght());
        strBar.setStringPainted(true);
        strBar.setString("STRENGTH");
        strBar.setForeground(Color.ORANGE);



        dfBar.setValue(warrior.getRace().getDefence());
        dfBar.setStringPainted(true);
        dfBar.setString("DEFENCE");
        dfBar.setForeground(Color.BLUE);



        agltyBar.setValue(warrior.getRace().getAgility());
        agltyBar.setStringPainted(true);
        agltyBar.setString("AGILITY");
        agltyBar.setForeground(Color.cyan);



        spdBar.setValue(warrior.getRace().getSpeed());
        spdBar.setStringPainted(true);
        spdBar.setString("SPEED");
        spdBar.setForeground(Color.GREEN);

        info_.setLayout(new BoxLayout(info_, BoxLayout.Y_AXIS));


        text.setFont(new Font("Agency FB",Font.BOLD, 30));

        text2.setText(warrior.getRace().getName());

        text5.setText(warrior.getName());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imgI,0,0,this);

    }



}
