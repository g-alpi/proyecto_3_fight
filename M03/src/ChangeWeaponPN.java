import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;


public class ChangeWeaponPN extends JPanel {
    Frame framePrincipal = (Frame) Frame.getFrames()[0];

    ImageIcon img = new ImageIcon("./media/changeCharacterBackground.png");
    Image imgI = img.getImage().getScaledInstance(framePrincipal.getWidth(),framePrincipal.getHeight(),Image.SCALE_SMOOTH);

    int num=1;

    static Weapons weapons;

    PlayPN playpn = new PlayPN();
    Warrior warrior = playpn.getPlayerWarrior();

    static ArrayList<Weapon> usableWeapons = new ArrayList<>();

    Weapon weapon;

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

    JLabel text;

    JLabel text2;

    public ChangeWeaponPN(){

        weapons= new Weapons();

        this.setLayout(new BorderLayout());


        weapon=weapons.weapons.get(8);

        icon = new ImageIcon(new ImageIcon(weapon.getImage()).getImage().getScaledInstance((int) (framePrincipal.getWidth()/1.5f),(int) (framePrincipal.getHeight()/1.5f),Image.SCALE_DEFAULT));
        System.out.println(weapon.getName());
        JButton rigth=new JButton();
        JButton left=new JButton();

        confirm.setName(weapon.getName());


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


        confirm.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JButton buttonTMP = (JButton) actionEvent.getSource();

                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"MainMenu");

                        PlayPN playPanel= (PlayPN) framePrincipal.getCards().getComponents()[1];
                        MainMenuPN mainMenuPanel= (MainMenuPN) framePrincipal.getCards().getComponents()[0];
                        mainMenuPanel.setWeaponImage(weapon);

                        Warrior warri=playPanel.getPlayerWarrior();
                        warri.setWeapon(weapon);

                        Main.musica("MainMenu");


                    }
                }

        );

        int width = this.getWidth();
        int height = this.getHeight();


        gif = new JLabel(icon);
        gifP.setSize((int) (gif.getIcon().getIconWidth()/1.5),gif.getIcon().getIconHeight());
        gifP.setLayout(new BorderLayout());
        gifP.add(new JLabel());
        gifP.add(gif);
        gif.add(new JLabel());

        gif_info.setLayout(new GridLayout(0,4));

        war.setLayout(new BorderLayout());
        //war.setLayout(new GridLayout(1,2));


        //stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        stats.setLayout(new FlowLayout());


        strBar.setValue(weapon.getStrenghtBonus());
        strBar.setStringPainted(true);
        strBar.setString("STRENGTH");
        strBar.setForeground(Color.ORANGE);
        str.add(strBar);

        spdBar.setValue(weapon.getSpeedBonus());
        spdBar.setStringPainted(true);
        spdBar.setString("SPEED");
        spdBar.setForeground(Color.GREEN);
        spd.add(spdBar);

        stats.add(str);
        stats.add(spd);

        info.setLayout(null);

        info_.setLayout(new BoxLayout(info_, BoxLayout.Y_AXIS));
        JLabel text = new JLabel("WEAPON:");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Agency FB",Font.BOLD, 30));
        info_.add(text);
        text2 = new JLabel(weapon.getName());
        text2.setFont(new Font("Agency FB",Font.BOLD, 40));
        text2.setForeground(Color.WHITE);
        info_.add(text2);

        info_.setBounds((int) (info.getWidth()/2-info_.getWidth()/2)+100,gifP.getHeight()/2-info_.getHeight()/2,999999999,999999999);

        info.add(info_,BorderLayout.CENTER);


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
                        if (num>=(usableWeapons.size())){
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
                            num=usableWeapons.size()-1;
                        }
                        init();
                    }
                }
        );


        this.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        icon = new ImageIcon(new ImageIcon(weapon.getImage()).getImage());
                        gif.setIcon(icon);
                    }
                }

        );

        gif.setOpaque(false);
        gifP.setOpaque(false);
        gif_info.setOpaque(false);
        war.setOpaque(false);

        stats.setOpaque(false);
        info.setOpaque(false);
        info_.setOpaque(false);
        text.setOpaque(false);

        str.setOpaque(false);
        spd.setOpaque(false);

    }

    public void init(){

        weapon=usableWeapons.get(num);
        text2.setText(weapon.getName());

        icon = new ImageIcon(new ImageIcon(weapon.getImage()).getImage());

        gif.setIcon(icon);
        gifP.setSize((int) (gif.getIcon().getIconWidth()/1.5),gif.getIcon().getIconHeight());
        confirm.setName(weapon.getName());

        gif_info.setLayout(new GridLayout(0,4));

        war.setLayout(new BorderLayout());

        stats.setLayout(new FlowLayout());

        strBar.setValue(weapon.getStrenghtBonus());
        strBar.setStringPainted(true);
        strBar.setString("STRENGTH");
        strBar.setForeground(Color.ORANGE);

        spdBar.setValue(weapon.getSpeedBonus());
        spdBar.setStringPainted(true);
        spdBar.setString("SPEED");
        spdBar.setForeground(Color.GREEN);

        info_.setLayout(new BoxLayout(info_, BoxLayout.Y_AXIS));



    }


    public void initWeapon(){

        weapon=weapons.weapons.get(8);
        text2.setText(weapon.getName());

        icon = new ImageIcon(new ImageIcon(weapon.getImage()).getImage());
        gif.setIcon(icon);
        gifP.setSize((int) (gif.getIcon().getIconWidth()/1.5),gif.getIcon().getIconHeight());
        confirm.setName(weapon.getName());

        gif_info.setLayout(new GridLayout(0,4));

        war.setLayout(new BorderLayout());

        stats.setLayout(new FlowLayout());

        strBar.setValue(weapon.getStrenghtBonus());
        strBar.setStringPainted(true);
        strBar.setString("STRENGTH");
        strBar.setForeground(Color.ORANGE);

        spdBar.setValue(weapon.getSpeedBonus());
        spdBar.setStringPainted(true);
        spdBar.setString("SPEED");
        spdBar.setForeground(Color.GREEN);

        info_.setLayout(new BoxLayout(info_, BoxLayout.Y_AXIS));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imgI,0,0,this);
    }



}