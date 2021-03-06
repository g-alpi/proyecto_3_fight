import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MainMenuPN extends JPanel {
    //initializing variables
    Frame framePrincipal = (Frame) Frame.getFrames()[0];
    //image for the background
    private ImageIcon iconMainMenuIC = new ImageIcon("./media/mainMenuBakground_"+framePrincipal.getWidth()+"_"+framePrincipal.getHeight()+".jpg");
    private Image imageMainMenuIMG = iconMainMenuIC.getImage();

    private Image img = new ImageIcon("./media/mainMenuLogo.png").getImage().getScaledInstance(framePrincipal.getWidth()/6*2,framePrincipal.getHeight()/3,Image.SCALE_SMOOTH);
    private ImageIcon imgMenu = new ImageIcon(img);
    private JLabel mainMenuLB = new JLabel();


    /* Buttons creations */
    private JButton playMainMenuBT = new JButton(new ImageIcon("./media/button_play.png"));

    private JButton changeCharacterBT = new JButton(new ImageIcon("./media/button_change-character.png"));

    private JButton changeWeaponBT = new JButton(new ImageIcon("./media/button_change-weapon.png"));

    private JButton rankingBT = new JButton(new ImageIcon("./media/button_ranking.png"));

    private JButton exitBT = new JButton(new ImageIcon("./media/button_exit.png"));


    private JLabel characterIMG = new JLabel(new ImageIcon(new ImageIcon("./Warriors/portrait_def.png").getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH)));
    private JLabel characterName = new JLabel("?????");

    private JLabel weaponIMG = new JLabel(new ImageIcon(new ImageIcon("./Warriors/portrait_def.png").getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
    private JLabel weaponName = new JLabel("?????");



    public MainMenuPN(){
        //start the conexion with the database
        framePrincipal.setMySqlCon(new Connect());

        this.setLayout(null);

        MainMenuPN menuPanel = this;
        /* Ajust scale of things based on the resolution of the frame */

        this.addComponentListener(

                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        iconMainMenuIC = new ImageIcon("./media/mainMenuBakground_"+framePrincipal.getWidth()+"_"+framePrincipal.getHeight()+".jpg");
                        imageMainMenuIMG = iconMainMenuIC.getImage();

                        img = new ImageIcon("./media/mainMenuLogo.png").getImage().getScaledInstance(framePrincipal.getWidth()/6*2,framePrincipal.getHeight()/4,Image.SCALE_SMOOTH);
                        imgMenu=new ImageIcon(img);
                        mainMenuLB.setIcon(imgMenu);
                        mainMenuLB.setLocation(framePrincipal.getWidth()/2-imgMenu.getIconWidth()/2,framePrincipal.getHeight()/6-imgMenu.getIconHeight()/2);

                        playMainMenuBT.setIcon(playMainMenuBT.getIcon());
                        playMainMenuBT.setBounds(framePrincipal.getWidth()/2-500-playMainMenuBT.getIcon().getIconWidth()/2, framePrincipal.getHeight()/2+50,playMainMenuBT.getIcon().getIconWidth(),playMainMenuBT.getIcon().getIconHeight());

                        changeCharacterBT.setIcon(changeCharacterBT.getIcon());
                        changeCharacterBT.setBounds(framePrincipal.getWidth()/2-changeCharacterBT.getIcon().getIconWidth()/2, framePrincipal.getHeight()/2+50,changeCharacterBT.getIcon().getIconWidth(),changeCharacterBT.getIcon().getIconHeight());

                        changeWeaponBT.setIcon(changeWeaponBT.getIcon());
                        changeWeaponBT.setBounds(changeCharacterBT.getX(), changeCharacterBT.getY()+changeCharacterBT.getHeight()*2,changeCharacterBT.getIcon().getIconWidth(),changeCharacterBT.getIcon().getIconHeight());

                        rankingBT.setIcon(rankingBT.getIcon());
                        rankingBT.setBounds(framePrincipal.getWidth()/2+500-changeCharacterBT.getIcon().getIconWidth()/2, framePrincipal.getHeight()/2+50,rankingBT.getIcon().getIconWidth(),rankingBT.getIcon().getIconHeight());

                        exitBT.setIcon(exitBT.getIcon());
                        exitBT.setBounds(10,10,exitBT.getIcon().getIconWidth(),exitBT.getIcon().getIconHeight());



                        characterIMG.setBounds(framePrincipal.getWidth()-200,20,characterIMG.getIcon().getIconWidth(),characterIMG.getIcon().getIconHeight());
                        characterName.setFont(characterName.getFont().deriveFont(18f));
                        characterName.setBounds(characterIMG.getX()+characterIMG.getIcon().getIconWidth()+10,characterIMG.getY()+characterIMG.getIcon().getIconHeight()/4,100,30);
                        weaponIMG.setBounds(characterIMG.getX()+characterIMG.getIcon().getIconWidth()/6,120,weaponIMG.getIcon().getIconWidth(),weaponIMG.getIcon().getIconHeight());
                        weaponName.setFont(weaponName.getFont().deriveFont(15f));
                        weaponName.setBounds(weaponIMG.getX()+weaponIMG.getIcon().getIconWidth()+10,weaponIMG.getY()+weaponIMG.getIcon().getIconHeight()/4,100,30);

                    }
                }
        );
        //mouse listeners for changing the icon when hovering
        playMainMenuBT.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        playMainMenuBT.setIcon(new ImageIcon("./media/button_play-hover.png"));
                    }
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        playMainMenuBT.setIcon(new ImageIcon("./media/button_play.png"));
                    }
                }
        );

        changeCharacterBT.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        changeCharacterBT.setIcon(new ImageIcon("./media/button_change-character-hover.png"));
                    }
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        changeCharacterBT.setIcon(new ImageIcon("./media/button_change-character.png"));
                    }
                }
        );

        changeWeaponBT.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        changeWeaponBT.setIcon(new ImageIcon("./media/button_change-weapon-hover.png"));
                    }
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        changeWeaponBT.setIcon(new ImageIcon("./media/button_change-weapon.png"));
                    }
                }
        );

        rankingBT.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        rankingBT.setIcon(new ImageIcon("./media/button_ranking-hover.png"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        rankingBT.setIcon(new ImageIcon("./media/button_ranking.png"));
                    }
                }
        );

        exitBT.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        exitBT.setIcon(new ImageIcon("./media/button_exit-hover.png"));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        exitBT.setIcon(new ImageIcon("./media/button_exit.png"));
                    }
                }
        );

        /* Init of all components */
        mainMenuLB.setBounds(framePrincipal.getWidth()/2-imgMenu.getIconWidth()/2,50,630,350);
        mainMenuLB.setOpaque(false);
        this.add(mainMenuLB);

        playMainMenuBT.setBorder(null);
        playMainMenuBT.setContentAreaFilled(false);
        /* Adding function to change to Play panel */
        playMainMenuBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        PlayPN playPanel= framePrincipal.getPlayPN();

                        /* If character is not selected, it will not change the panel */
                        if (playPanel.getPlayerWarrior()==null){
                            System.out.println("Tienes que escojer un personaje antes");
                        }else {
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

                        }

                        playPanel.setHealthBars();

                        /* Set music for new panel */
                        Main.musica("Figth");

                    }
                }

        );
        this.add(playMainMenuBT);

        changeCharacterBT.setBorder(null);
        changeCharacterBT.setContentAreaFilled(false);
        /* Adding function to change to ChangeCharacter panel */
        changeCharacterBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"ChangeCharacter");

                        Main.musica("ChangeCharacter");
                      }
                }
        );
        this.add(changeCharacterBT);

        changeWeaponBT.setBorder(null);
        changeWeaponBT.setContentAreaFilled(false);
        /* Adding function to change to ChangeWeapon panel */
        changeWeaponBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        PlayPN playPanel= (PlayPN) framePrincipal.getCards().getComponents()[1];

                        /* If character is not selected, it will not change the panel */
                        if (playPanel.getPlayerWarrior()==null){
                            System.out.println("Tienes que escojer un personaje antes");
                        }else {
                            /* Change panel to figth */
                            CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                            cl.show(framePrincipal.getCards(),"ChangeWeapon");

                            Main.musica("ChangeCharacter");
                        }

                    }
                }
        );
        this.add(changeWeaponBT);

        rankingBT.setBorder(null);
        rankingBT.setContentAreaFilled(false);
        //making the ranking button work
        rankingBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        framePrincipal.newRankPN();
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"Ranking");
                        Main.musica("Ranking");
                    }
                }
        );
        this.add(rankingBT);

        exitBT.setBorder(null);
        exitBT.setContentAreaFilled(false);
        //making the exit button work
        exitBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.exit(0);
                    }
                }
        );
        this.add(exitBT);


        /* Adding Characters and Weapong preview */
        this.add(characterIMG);
        this.add(characterName);
        this.add(weaponIMG);
        this.add(weaponName);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        /* Show the Background image */
        g2d.drawImage(imageMainMenuIMG,0,0,this);
    }

    public void setCharacterImage(Warrior war){

        characterIMG.setIcon(new ImageIcon(new ImageIcon(war.getPortrait()).getImage().getScaledInstance(characterIMG.getIcon().getIconWidth(),characterIMG.getIcon().getIconHeight(),Image.SCALE_SMOOTH)));

        characterName.setText(war.getName());
    }

    public void setWeaponImage(Weapon wep){

        weaponIMG.setIcon(new ImageIcon(new ImageIcon(wep.getImage()).getImage().getScaledInstance(weaponIMG.getIcon().getIconWidth(),weaponIMG.getIcon().getIconHeight(),Image.SCALE_SMOOTH)));

        weaponName.setText(wep.getName());
    }

}
