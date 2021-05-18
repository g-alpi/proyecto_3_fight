import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class PlayPN extends JPanel {

    Frame framePrincipal = (Frame) Frame.getFrames()[0];

    /* Background IMG */
    private Image imgBackground = new ImageIcon("./media/mapElf.png").getImage().getScaledInstance(framePrincipal.getWidth(),framePrincipal.getHeight(),Image.SCALE_SMOOTH);

    /* Battle OBJ */
    private Battle battle= new Battle();

    /* Pannel components */
    private JTextField log = new JTextField(25);
    private JTextArea console = new JTextArea(3,25);
    private JScrollPane sp = new JScrollPane(console);
    private JProgressBar healthPlayer = new JProgressBar();
    private JProgressBar healthEnemy = new JProgressBar();
    private JButton boton = new JButton("Empezar pelea"); //Boton Temporal
    private JButton boyon = new JButton("atras"); //Boton Temporal

    /* Player & Enemy info */
    private Warrior playerWarrior;
    private User playerUser = new User(null,null);

    private Warrior enemyWarrior;
    private User enemyUser;

    private JLabel characterImage = new JLabel(new ImageIcon(new ImageIcon("./Warriors/BstandLoop_Pepe.gif").getImage().getScaledInstance(framePrincipal.getWidth()-500,framePrincipal.getHeight()-300,Image.SCALE_DEFAULT)));
    private JLabel enemiImage = new JLabel(new ImageIcon(new ImageIcon("./Warriors/FstandLoop_Pepe.gif").getImage().getScaledInstance(characterImage.getIcon().getIconWidth()-500,characterImage.getIcon().getIconHeight()-300,Image.SCALE_DEFAULT)));

    private JLabel characterWeapon = new JLabel();
    private JLabel enemiWeapon = new JLabel();

    public PlayPN(){

        /* Making a variable this, to use it later in action liseners */
        PlayPN panel = this;
        this.setLayout(null);

        /* Generate random enemy warrior */
        enemyWarrior=randomEnemy();


        /* Making all the components in the panel, resizable */
        this.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        imgBackground = new ImageIcon("./media/map"+enemyWarrior.getRace().getName()+".png").getImage().getScaledInstance(panel.getWidth(),panel.getHeight(),Image.SCALE_SMOOTH);
                        sp.setBounds(framePrincipal.getWidth()/2-250,framePrincipal.getHeight()-150,framePrincipal.getWidth()/2+250,300);
                        log.setBounds(sp.getX(),sp.getY()-20,sp.getWidth(),20);

                        characterImage.setBounds(0-characterImage.getIcon().getIconWidth()/3+40,framePrincipal.getHeight()-characterImage.getIcon().getIconHeight()+characterImage.getIcon().getIconHeight()/6,characterImage.getIcon().getIconWidth(),characterImage.getIcon().getIconHeight());
                        enemiImage.setBounds(framePrincipal.getWidth()-enemiImage.getIcon().getIconWidth()/3*2,0,enemiImage.getIcon().getIconWidth(),enemiImage.getIcon().getIconHeight());

                        healthPlayer.setBounds(200,500,500,20);
                        characterWeapon.setBounds(healthPlayer.getX()-105,healthPlayer.getY(),100,100);

                        healthEnemy.setBounds(framePrincipal.getWidth()-enemiImage.getWidth()-40,30,500,20);
                        enemiWeapon.setBounds(healthEnemy.getX()-105,healthEnemy.getY(),100,100);
                    }
                }
        );

        /* Set custom font to console and log */
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("./media/PixelFont.TTF")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./media/PixelFont.TTF")));
            console.setFont(font);
            log.setFont(font);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Adding the console to the panel */
        console.setEditable(false);
        this.add(sp);

        log.setEditable(true);
        this.add(log);

        // Boton Temporal
        boton.setBounds(100,100,100,100);
        boton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        new Thread(){

                            @Override
                            public void run() {
                                gameLoop();
                            }
                        }.start();
                    }
                }
        );
        this.add(boton);

        // Boton Temporal
        boyon.setBounds(400,100,100,100);
        boyon.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"MainMenu");
                    }
                }
        );
        this.add(boyon);

        this.add(characterImage);
        this.add(healthPlayer);
        this.add(characterWeapon);

        this.add(enemiImage);
        this.add(healthEnemy);
        this.add(enemiWeapon);

    }


    /* Change animation for specific time */
    public void makeAnimation(String animation){
        Warrior war= null;
        JLabel label=new JLabel();
        try{
            if (animation.charAt(0)=='B') {
                label=characterImage;
                war=getPlayerWarrior();
            }else{
                label=enemiImage;
                war=getEnemyWarrior();
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("The animation could not be loaded");
        }

        ImageIcon auxICO = (ImageIcon) label.getIcon();
        Image backUP = auxICO.getImage();

        Image img = backUP;

        String url="";
        int tiempo=0;

        switch (animation){
            case "Bwound":
                url=war.getBwound();
                tiempo=2500;
                break;
            case "Battack":
                if (war.getWeapon().name.equalsIgnoreCase("Bow")){
                    if (war.getRace().getName().equalsIgnoreCase("Elf")){
                        url=war.getBattack_bow();
                        tiempo=860;
                    }else{
                        url=war.getBattack();
                        tiempo=1300;
                    }
                }else{
                    url=war.getBattack();
                    tiempo=1300;
                }
                break;
            case "Bdodge":
                url=war.getBdodge();
                tiempo=1300;
                break;
            case "Bdie":
                url=war.getBdie();
                tiempo=3700;
                break;
            case "Fwound":
                url=war.getFwound();
                tiempo=2500;
                break;
            case "Fattack":
                if (war.getWeapon().name.equalsIgnoreCase("Bow")){
                    if (war.getRace().getName().equalsIgnoreCase("Elf")){
                        url=war.getFattack_bow();
                        tiempo=860;
                    }else{
                        url=war.getFattack();
                        tiempo=1300;
                    }
                }else{
                    url=war.getFattack();
                    tiempo=1300;
                }
                break;
            case "Fdodge":
                url=war.getFdodge();
                tiempo=1300;
                break;
            case "Fdie":
                url=war.getFdie();
                tiempo=2200;
                break;
            case "Fcry":
                url=war.getCry();
                tiempo=5000;
                break;
            case "Fdance":
                url=war.getDance();
                tiempo=5000;
                break;
            case "Bcry":
                url=war.getCry();
                tiempo=5000;
                break;
            case "Bdance":
                url=war.getDance();
                tiempo=5000;
                break;
            default:
                try{
                    if (animation.charAt(0)=='B') {
                        url=war.getBstandLoop();
                    }else{
                        url=war.getFstandLoop();
                    }
                }catch (StringIndexOutOfBoundsException e){
                    System.out.println("The animation could not be loaded");
                }
        }

        try{
            if (animation.charAt(0)=='B') {
                img = new ImageIcon(url).getImage().getScaledInstance(framePrincipal.getWidth() - 500, framePrincipal.getHeight() - 300, Image.SCALE_DEFAULT);
            }else{
                img = new ImageIcon(url).getImage().getScaledInstance(characterImage.getIcon().getIconWidth()-500,characterImage.getIcon().getIconHeight()-300,Image.SCALE_DEFAULT);
            }
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("The animation could not be loaded");
        }

        label.setIcon(new ImageIcon(img));

        int finalTiempo = tiempo;
        final Image[] finalImg = {img};
        JLabel finalLabel = label;
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(finalTiempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finalLabel.setIcon(new ImageIcon(backUP));
            }
        }.start();

    }

    /* Logging strings in the console */
    public void logText(String text){
        new Thread(){
            public void run(){
                log.setText("");
                /* Move scroll to view last line */
                Dimension tamanhoTextArea = console.getSize();
                Point p = new Point(0,0);
                sp.getViewport().setViewPosition(p);

                /* Insert new line in the console whith a undertale like style */
                for (int i=0;i<text.length();i++){
                    log.setText(log.getText()+String.valueOf(text.charAt(i)));
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /* Loggin things from TextField to TextArea */
                console.setText("\n"+text+console.getText());


                /* Cleaning the TextField  after 1 second of displaying it */
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.setText("");
            }
        }.start();

    }



    /* Generate a random Warrior */
    public Warrior randomEnemy(){
        Weapons weapons = new Weapons();
        ArrayList<Weapon> usableWeapons= new ArrayList<>();
        Warriors warriors = new Warriors();
        Warrior war = warriors.getWarriors().get(new Random().nextInt(warriors.getWarriors().size()-1));


        /* Save usable weapons for random generated Warrior */
        for (Weapon w: weapons.weapons){
            for (Race r:w.getWielders()){
                if (r.getName().equalsIgnoreCase(war.getRace().getName())){
                    usableWeapons.add(w);
                }
            }
        }

        /* Randomly selects a weapon */
        war.setWeapon(usableWeapons.get(new Random().nextInt(usableWeapons.size()-1)));

        enemiImage.setIcon(new ImageIcon(new ImageIcon(war.getFstandLoop()).getImage().getScaledInstance(characterImage.getIcon().getIconWidth()-500,characterImage.getIcon().getIconHeight()-300,Image.SCALE_DEFAULT)));
        enemyUser= new User("bot",war);
        return war;
    }


    public void gameLoop(){

        characterWeapon.setIcon(new ImageIcon(new ImageIcon(playerUser.warrior.getWeapon().getImage()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
        healthPlayer.setMaximum(playerUser.warrior.getRace().getHealth());
        healthPlayer.setValue(playerUser.warrior.getRace().getHealth());
        healthPlayer.setForeground(Color.GREEN);

        enemiWeapon.setIcon(new ImageIcon(new ImageIcon(enemyUser.warrior.getWeapon().getImage()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
        healthEnemy.setMaximum(enemyUser.warrior.getRace().getHealth());
        healthEnemy.setValue(enemyUser.warrior.getRace().getHealth());
        healthEnemy.setForeground(Color.GREEN);

        ArrayList<User> order;
        int statusAttack;

        order=battle.firstOrder(playerUser,enemyUser);
        statusAttack=battle.attack(order.get(0),order.get(1));

        switch (statusAttack){
            case 1:
                logText(battle.resultAtack(order.get(0),order.get(1)));
                if (battle.isBot(order.get(0))){
                    makeAnimation("Fattack");
                    makeAnimation("Bwound");
                    healthPlayer.setValue(order.get(1).warrior.getRace().getHealth());

                    /* Set color to Health Bar */
                    if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75 && healthEnemy.getForeground()!=Color.YELLOW){
                        healthPlayer.setForeground(Color.YELLOW);
                    }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                        healthPlayer.setForeground(Color.RED);
                    }
                }else{
                    makeAnimation("Battack");
                    makeAnimation("Fwound");
                    healthEnemy.setValue(order.get(1).warrior.getRace().getHealth());

                    if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75 && healthEnemy.getForeground()!=Color.YELLOW){
                        healthEnemy.setForeground(Color.YELLOW);
                    }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                        healthEnemy.setForeground(Color.RED);
                    }
                }
                break;
            case 2:
                logText(order.get(0).getName()+" miss");
                if (battle.isBot(order.get(0))){
                    makeAnimation("Fattack");
                }else{
                    makeAnimation("Battack");
                }
                break;
            case 3:
                logText(order.get(1).getName()+" dodges "+order.get(0).getName()+"'s attack");
                if (battle.isBot(order.get(0))){
                    makeAnimation("Fattack");
                    makeAnimation("Bdodge");
                }else{
                    makeAnimation("Battack");
                    makeAnimation("Fdodge");
                }
                break;
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        do{

            order=battle.order(order.get(0),order.get(1));
            statusAttack=battle.attack(order.get(0),order.get(1));

            switch (statusAttack){
                case 1:
                    logText(battle.resultAtack(order.get(0),order.get(1)));
                    if (order.get(1).warrior.getRace().getHealth()<=0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        logText(order.get(1).warrior.getName()+" a muerto :(");
                        if (battle.isBot(order.get(0))){
                            makeAnimation("Fattack");
                            makeAnimation("Bdie");
                            playerUser.setPoints(0);

                            healthPlayer.setValue(order.get(1).warrior.getRace().getHealth());

                            if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75 && healthEnemy.getForeground()!=Color.YELLOW){
                                healthPlayer.setForeground(Color.YELLOW);
                            }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                                healthPlayer.setForeground(Color.RED);
                            }
                            new Connect().uploadBattle("root1","root1",new Connect().uploadUser("root1","root1"));


                        }else{
                            makeAnimation("Battack");
                            makeAnimation("Fdie");

                            playerUser.setPoints(enemyUser.getPoints()+enemyUser.warrior.getWeapon().getPoints()+enemyUser.getWarrior().getRace().getPoints());
                            healthEnemy.setValue(order.get(1).warrior.getRace().getHealth());

                            if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75 && healthEnemy.getForeground()!=Color.YELLOW){
                                healthEnemy.setForeground(Color.YELLOW);
                            }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                                healthEnemy.setForeground(Color.RED);
                            }

                            new Connect().uploadBattle("root1","root1",new Connect().uploadUser("root1","root1"));

                        }

                    }else{

                        if (battle.isBot(order.get(0))){
                            makeAnimation("Fattack");
                            makeAnimation("Bwound");
                            healthPlayer.setValue(order.get(1).warrior.getRace().getHealth());

                            if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75 && healthEnemy.getForeground()!=Color.YELLOW){
                                healthPlayer.setForeground(Color.YELLOW);
                            }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                                healthPlayer.setForeground(Color.RED);
                            }
                        }else{
                            makeAnimation("Battack");
                            makeAnimation("Fwound");
                            healthEnemy.setValue(order.get(1).warrior.getRace().getHealth());

                            if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75 && healthEnemy.getForeground()!=Color.YELLOW){
                                healthEnemy.setForeground(Color.YELLOW);
                            }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                                healthEnemy.setForeground(Color.RED);
                            }
                        }
                    }
                    break;
                case 2:
                    logText(order.get(0).getName()+" miss");
                    if (battle.isBot(order.get(0))){
                        makeAnimation("Fattack");
                    }else{
                        makeAnimation("Battack");
                    }
                    break;
                case 3:
                    logText(order.get(1).getName()+" dodges "+order.get(1).getName()+"'s attack");
                    if (battle.isBot(order.get(0))){
                        makeAnimation("Fattack");
                        makeAnimation("Bdodge");
                    }else{
                        makeAnimation("Battack");
                        makeAnimation("Fdodge");
                    }
                    break;
            }

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while(playerUser.warrior.getRace().getHealth()>0 && enemyUser.warrior.getRace().getHealth()>0);

        /* Displays Winner panel */
        framePrincipal.getCards().add(new WinnerPN(),"Winner");
        CardLayout cl = (CardLayout) framePrincipal.getCards().getLayout();
        cl.show(framePrincipal.getCards(),"Winner");

    }

    public User getPlayerUser() {
        return playerUser;
    }

    public User getEnemyUser() {
        return enemyUser;
    }

    public void setPlayerWarrior(Warrior playerWarrior) {
        this.playerWarrior = playerWarrior;
    }

    public void setEnemyWarrior(Warrior enemyWarrior) {
        this.enemyWarrior = enemyWarrior;
    }

    public void setPlayerUser(User playerUser) {
        this.playerUser = playerUser;
    }

    public void setEnemyUser(User enemyUser) {
        this.enemyUser = enemyUser;
    }

    public void setImgBackground() {
        this.imgBackground = new ImageIcon("./media/map"+enemyWarrior.getRace().getName()+".png").getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
    }

    public Warrior getPlayerWarrior() {
        return playerWarrior;
    }

    public Warrior getEnemyWarrior() {
        return enemyWarrior;
    }

    public JLabel getCharacterImage() {
        return characterImage;
    }

    public JLabel getEnemiImage() {
        return enemiImage;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imgBackground,0,0,this);
    }

}