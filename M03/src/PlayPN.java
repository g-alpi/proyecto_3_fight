import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class PlayPN extends JPanel {

    private Frame framePrincipal = (Frame) Frame.getFrames()[0];

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
    private JButton figthBT = new JButton(new ImageIcon("./media/button_figth.png"));

    /* Player & Enemy info */
    private Warrior playerWarrior;
    private User playerUser = new User(null,null);

    private Warrior enemyWarrior;
    private User enemyUser;

    private JLabel characterImage = new JLabel(new ImageIcon(new ImageIcon("./Warriors/BstandLoop_Pepe.gif").getImage().getScaledInstance(framePrincipal.getWidth()-500,framePrincipal.getHeight()-300,Image.SCALE_DEFAULT)));
    private JLabel enemiImage = new JLabel(new ImageIcon(new ImageIcon("./Warriors/FstandLoop_Pepe.gif").getImage().getScaledInstance(characterImage.getIcon().getIconWidth()-500,characterImage.getIcon().getIconHeight()-300,Image.SCALE_DEFAULT)));

    private JLabel characterWeapon = new JLabel();
    private JLabel enemiWeapon = new JLabel();

    private Thread gameThread=null;

    private int tiempo;

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

                        figthBT.setIcon(figthBT.getIcon());
                        figthBT.setBounds(figthBT.getX(),figthBT.getY(),figthBT.getIcon().getIconWidth(),figthBT.getIcon().getIconHeight());

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

        log.setEditable(false);
        this.add(log);

        figthBT.setBorder(null);
        figthBT.setContentAreaFilled(false);
        figthBT.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        figthBT.setIcon(new ImageIcon("./media/button_figth-hover.png"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        figthBT.setIcon(new ImageIcon("./media/button_figth.png"));
                    }
                }
        );

        figthBT.setBounds(100,100,100,100);
        figthBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        gameThread = new Thread(){
                            @Override
                            public void run() {
                                gameLoop();
                            }
                        };
                        gameThread.start();
                        figthBT.setVisible(false);
                    }
                }
        );
        this.add(figthBT);

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

        switch (animation){
            case "Bwound":
                url=war.getBwound();
                switch (war.getName()){
                    case "Epifania":
                        tiempo=2000;
                        break;
                    case "Maria":
                        tiempo=2000;
                        break;
                    case "Pepe":
                        tiempo=2000;
                        break;
                    case "Torbjina":
                        tiempo=2000;
                        break;
                    case "Torbjorn":
                        tiempo=2000;
                        break;
                    case "Eduardo":
                        tiempo=1600;
                        break;
                }

                break;
            case "Battack":
                if (war.getWeapon().name.equalsIgnoreCase("Bow")){
                    if (war.getRace().getName().equalsIgnoreCase("Elf")){
                        url=war.getBattack_bow();
                        switch (war.getName()){
                            case "Epifania":
                                tiempo=600;
                                break;
                            case "Eduardo":
                                tiempo=600;
                                break;
                        }
                    }else{
                        url=war.getBattack();
                        switch (war.getName()){
                            case "Maria":
                                tiempo=1000;
                                break;
                            case "Pepe":
                                tiempo=1000;
                                break;
                            case "Torbjina":
                                tiempo=1000;
                                break;
                            case "Torbjorn":
                                tiempo=1000;
                                break;
                        }
                    }
                }else{
                    url=war.getBattack();
                    switch (war.getName()){
                        case "Epifania":
                            tiempo=1100;
                            break;
                        case "Maria":
                            tiempo=1000;
                            break;
                        case "Pepe":
                            tiempo=1000;
                            break;
                        case "Torbjina":
                            tiempo=1000;
                            break;
                        case "Torbjorn":
                            tiempo=1000;
                            break;
                        case "Eduardo":
                            tiempo=1100;
                            break;
                    }
                }
                break;
            case "Bdodge":
                url=war.getBdodge();
                switch (war.getName()){
                    case "Epifania":
                        tiempo=1000;
                        break;
                    case "Maria":
                        tiempo=1000;
                        break;
                    case "Pepe":
                        tiempo=1000;
                        break;
                    case "Torbjina":
                        tiempo=1000;
                        break;
                    case "Torbjorn":
                        tiempo=1000;
                        break;
                    case "Eduardo":
                        tiempo=1100;
                        break;
                }
                break;
            case "Bdie":
                url=war.getBdie();
                switch (war.getName()){
                    case "Epifania":
                        tiempo=1950;
                        break;
                    case "Maria":
                        tiempo=2000;
                        break;
                    case "Pepe":
                        tiempo=2200;
                        break;
                    case "Torbjina":
                        tiempo=1500;
                        break;
                    case "Torbjorn":
                        tiempo=1800;
                        break;
                    case "Eduardo":
                        tiempo=4150;
                        break;
                }
                break;
            case "Fwound":
                url=war.getFwound();
                switch (war.getName()){
                    case "Epifania":
                        tiempo=2000;
                        break;
                    case "Maria":
                        tiempo=2000;
                        break;
                    case "Pepe":
                        tiempo=2000;
                        break;
                    case "Torbjina":
                        tiempo=2000;
                        break;
                    case "Torbjorn":
                        tiempo=2000;
                        break;
                    case "Eduardo":
                        tiempo=1600;
                        break;
                }
                break;
            case "Fattack":
                if (war.getWeapon().name.equalsIgnoreCase("Bow")){
                    if (war.getRace().getName().equalsIgnoreCase("Elf")){
                        url=war.getFattack_bow();
                        switch (war.getName()){
                            case "Epifania":
                                tiempo=600;
                                break;
                            case "Eduardo":
                                tiempo=600;
                                break;
                        }
                    }else{
                        url=war.getFattack();
                        switch (war.getName()){
                            case "Maria":
                                tiempo=1000;
                                break;
                            case "Pepe":
                                tiempo=1000;
                                break;
                            case "Torbjina":
                                tiempo=1000;
                                break;
                            case "Torbjorn":
                                tiempo=1000;
                                break;
                        }
                    }
                }else{
                    url=war.getFattack();
                    switch (war.getName()){
                        case "Epifania":
                            tiempo=1100;
                            break;
                        case "Maria":
                            tiempo=1000;
                            break;
                        case "Pepe":
                            tiempo=1000;
                            break;
                        case "Torbjina":
                            tiempo=1000;
                            break;
                        case "Torbjorn":
                            tiempo=1000;
                            break;
                        case "Eduardo":
                            tiempo=1100;
                            break;
                    }
                }
                break;
            case "Fdodge":
                url=war.getFdodge();
                switch (war.getName()){
                    case "Epifania":
                        tiempo=1000;
                        break;
                    case "Maria":
                        tiempo=1000;
                        break;
                    case "Pepe":
                        tiempo=1000;
                        break;
                    case "Torbjina":
                        tiempo=1000;
                        break;
                    case "Torbjorn":
                        tiempo=1000;
                        break;
                    case "Eduardo":
                        tiempo=1100;
                        break;
                }
                break;
            case "Fdie":
                url=war.getFdie();
                switch (war.getName()){
                    case "Epifania":
                        tiempo=1950;
                        break;
                    case "Maria":
                        tiempo=2000;
                        break;
                    case "Pepe":
                        tiempo=2200;
                        break;
                    case "Torbjina":
                        tiempo=1500;
                        break;
                    case "Torbjorn":
                        tiempo=1800;
                        break;
                    case "Eduardo":
                        tiempo=4150;
                        break;
                }
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

        tiempo= (int) (tiempo*1.2f);
        int finalTiempo = tiempo;
        final Image[] finalImg = {img};
        JLabel finalLabel = label;
        Warrior finalWar = war;
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
        for (Weapon w: weapons.getWeapons()){
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


    public void gameLoop(){   /* Game Loop */

        int gameSpeed=4000;

        this.setHealthBars();

        ArrayList<User> order;
        int statusAttack;

        order=battle.firstOrder(playerUser,enemyUser);
        statusAttack=battle.attack(order.get(0),order.get(1));

        switch (statusAttack){
            case 1:
                logText(battle.resultAtack(order.get(0),order.get(1)));
                if (order.get(1).getWarrior().getRace().getHealth()<=0){

                    try {
                        gameThread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    logText(order.get(1).getWarrior().getName()+" died :(");
                    if (battle.isBot(order.get(0))){
                        makeAnimation("Fattack");
                        makeAnimation("Bdie");

                        gameSpeed=(int) (tiempo*0.8)-200;

                        playerUser.setPoints(0);

                        healthPlayer.setValue(order.get(1).getWarrior().getRace().getHealth());
                        if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                            healthPlayer.setForeground(Color.RED);
                        }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75){
                            healthPlayer.setForeground(Color.YELLOW);
                        }


                    }else{
                        makeAnimation("Battack");
                        makeAnimation("Fdie");

                        gameSpeed=(int) (tiempo*0.8)-200;

                        playerUser.setPoints(enemyUser.getPoints()+enemyUser.getWarrior().getWeapon().getPoints()+enemyUser.getWarrior().getRace().getPoints());
                        healthEnemy.setValue(order.get(1).getWarrior().getRace().getHealth());
                        if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                            healthEnemy.setForeground(Color.RED);
                        }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75){
                            healthEnemy.setForeground(Color.YELLOW);
                        }


                    }

                }else{

                    if (battle.isBot(order.get(0))){
                        makeAnimation("Fattack");
                        makeAnimation("Bwound");
                        healthPlayer.setValue(order.get(1).getWarrior().getRace().getHealth());
                        if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                            healthPlayer.setForeground(Color.RED);
                        }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75){
                            healthPlayer.setForeground(Color.YELLOW);
                        }
                    }else{
                        makeAnimation("Battack");
                        makeAnimation("Fwound");
                        healthEnemy.setValue(order.get(1).getWarrior().getRace().getHealth());

                        if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                            healthEnemy.setForeground(Color.RED);
                        }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75){
                            healthEnemy.setForeground(Color.YELLOW);
                        }
                    }
                }
                break;
            case 2:
                logText(order.get(0).getWarrior().getName()+" misses");
                if (battle.isBot(order.get(0))){
                    makeAnimation("Fattack");
                }else{
                    makeAnimation("Battack");
                }
                break;
            case 3:
                logText(order.get(1).getWarrior().getName()+" dodges "+order.get(0).getWarrior().getName()+"'s attack");
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
            Thread.sleep(gameSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (playerUser.getWarrior().getRace().getHealth()>0 && enemyUser.getWarrior().getRace().getHealth()>0){
            do{

                order=battle.order(order.get(0),order.get(1));
                statusAttack=battle.attack(order.get(0),order.get(1));

                switch (statusAttack){
                    case 1:
                        logText(battle.resultAtack(order.get(0),order.get(1)));
                        if (order.get(1).getWarrior().getRace().getHealth()<=0){

                            try {
                                gameThread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            logText(order.get(1).getWarrior().getName()+" died :(");
                            if (battle.isBot(order.get(0))){
                                makeAnimation("Fattack");
                                makeAnimation("Bdie");

                                gameSpeed=(int) (tiempo*0.8)-200;
                                playerUser.setPoints(0);

                                healthPlayer.setValue(order.get(1).getWarrior().getRace().getHealth());
                                if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                                    healthPlayer.setForeground(Color.RED);
                                }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75){
                                    healthPlayer.setForeground(Color.YELLOW);
                                }


                            }else{
                                makeAnimation("Battack");
                                makeAnimation("Fdie");

                                gameSpeed=(int) (tiempo*0.8)-200;

                                playerUser.setPoints(enemyUser.getPoints()+enemyUser.getWarrior().getWeapon().getPoints()+enemyUser.getWarrior().getRace().getPoints());
                                healthEnemy.setValue(order.get(1).getWarrior().getRace().getHealth());
                                if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                                    healthEnemy.setForeground(Color.RED);
                                }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75){
                                    healthEnemy.setForeground(Color.YELLOW);
                                }


                            }

                        }else{

                            if (battle.isBot(order.get(0))){
                                makeAnimation("Fattack");
                                makeAnimation("Bwound");
                                healthPlayer.setValue(order.get(1).getWarrior().getRace().getHealth());
                                if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*25){
                                    healthPlayer.setForeground(Color.RED);
                                }else if (healthPlayer.getValue()<((float)healthPlayer.getMaximum()/100)*75){
                                    healthPlayer.setForeground(Color.YELLOW);
                                }
                            }else{
                                makeAnimation("Battack");
                                makeAnimation("Fwound");
                                healthEnemy.setValue(order.get(1).getWarrior().getRace().getHealth());

                                if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*25){
                                    healthEnemy.setForeground(Color.RED);
                                }else if (healthEnemy.getValue()<((float)healthEnemy.getMaximum()/100)*75){
                                    healthEnemy.setForeground(Color.YELLOW);
                                }
                            }
                        }
                        break;
                    case 2:
                        logText(order.get(0).getWarrior().getName()+" misses");
                        if (battle.isBot(order.get(0))){
                            makeAnimation("Fattack");
                        }else{
                            makeAnimation("Battack");
                        }
                        break;
                    case 3:
                        logText(order.get(1).getWarrior().getName()+" dodges "+order.get(0).getWarrior().getName()+"'s attack");
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
                    Thread.sleep(gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }while(playerUser.getWarrior().getRace().getHealth()>0 && enemyUser.getWarrior().getRace().getHealth()>0);
        }
        framePrincipal.getMySqlCon().uploadBattle(framePrincipal.getMySqlCon().uploadUser());
        playerUser.setPoints(0);

        Main.musica("Ranking");

        /* Displays Winner panel */
        framePrincipal.getCards().add(new WinnerPN(),"Winner");
        CardLayout cl = (CardLayout) framePrincipal.getCards().getLayout();
        cl.show(framePrincipal.getCards(),"Winner");

    }

    public void setHealthBars(){  /* Sets the Health Bars */

        characterWeapon.setIcon(new ImageIcon(new ImageIcon(playerUser.getWarrior().getWeapon().getImage()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
        healthPlayer.setMaximum(playerUser.getWarrior().getRace().getHealth());
        healthPlayer.setValue(playerUser.getWarrior().getRace().getHealth());
        healthPlayer.setForeground(Color.GREEN);

        enemiWeapon.setIcon(new ImageIcon(new ImageIcon(enemyUser.getWarrior().getWeapon().getImage()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
        healthEnemy.setMaximum(enemyUser.getWarrior().getRace().getHealth());
        healthEnemy.setValue(enemyUser.getWarrior().getRace().getHealth());
        healthEnemy.setForeground(Color.GREEN);

    }

    public JTextArea getConsole() {
        return console;
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

    public JButton getFigthBT() {
        return figthBT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imgBackground,0,0,this);
    }

}