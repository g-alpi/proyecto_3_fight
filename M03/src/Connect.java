import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Connect {
    //declaring variables
    private static Frame framePrincipal= (Frame) Frame.getFrames()[0]; //getting the principal frame so we can access the username
    private ArrayList<String> credentials;
    private static String user;
    private static String passwd;

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Connect.user = user;
    }

    public static String getPasswd() {
        return passwd;
    }

    public static void setPasswd(String passwd) {
        Connect.passwd = passwd;
    }

    public Connect(){
        //create the connect panel to make the user input his credentials
        ConnectPN cred = new ConnectPN();
        while (!cred.getConfirmed()){
            System.out.print("");   //untill the user has inputed the credentials the program wont continue
        }

        framePrincipal.setUsername(cred.getUser());
        String tempUser = cred.getUser();
        tempUser.replace("\"","a");     //controling the input chars
        tempUser.replace("'","a");
        tempUser.replace("\\","a");
        tempUser.replace("=","a");
        tempUser.replace("\"","a");
        tempUser.replace(";","a");
        cred.setUser(tempUser);
        
        user=cred.getMySqlUser();
        user.replace("\"","a");
        user.replace("'","a");
        user.replace("\\","a");
        user.replace("=","a");
        user.replace("\"","a");
        user.replace(";","a");
        
        passwd=cred.getMySqlPwd();
        passwd.replace("\""," ");
        passwd.replace("'"," ");
        passwd.replace("\\"," ");
        passwd.replace("="," ");
        passwd.replace("\""," ");
        passwd.replace(";"," ");

        if (tempUser.length()>7){
            JOptionPane.showMessageDialog(null, "Username can't be longer than 7", "Error", JOptionPane.ERROR_MESSAGE);
            new Connect();
        }else {     //controling that the credentials are ok
            try {
                Connection con = null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC", user, passwd);
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Wrong credentials", "Error", JOptionPane.ERROR_MESSAGE);
                new Connect();
            }
        }

    }

    public static ArrayList getMostDmgDealt(){
        ArrayList mostDmg=null;
        try {
            mostDmg=new ArrayList();
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            //checking if theres has been no battles yet
            Statement stm_p=con.createStatement();
            ResultSet rs_p=stm_p.executeQuery("select count(*) from battle");
            rs_p.next();
            if (rs_p.getInt(1)==0){
                mostDmg.add(" NULL ");
                mostDmg.add("_");
                return mostDmg;
            }
            //select the values we want to show
            Statement stm=con.createStatement();
            ResultSet rs=null;

            Statement stm_n=con.createStatement();
            ResultSet rs_n=stm_n.executeQuery("select players.player_name,battle.injuries_caused from battle Inner join players on players.player_id=battle.player_id where injuries_caused=(select max(injuries_caused) from battle) limit 1;");
            rs_n.next();

            mostDmg.add(rs_n.getString(1));
            mostDmg.add(rs_n.getInt(2));

            return mostDmg;

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            System.out.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mostDmg;
    }
    public static ArrayList getMostDmgTaken(){
        ArrayList mostDmg=null;
        try {
            mostDmg=new ArrayList();
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            //checking if theres has been no battles yet
            Statement stm_p=con.createStatement();
            ResultSet rs_p=stm_p.executeQuery("select count(*) from battle");
            rs_p.next();
            if (rs_p.getInt(1)==0){
                mostDmg.add(" NULL ");
                mostDmg.add("_");
                return mostDmg;
            }

            //select the values we want to show

            Statement stm_n=con.createStatement();
            ResultSet rs_n=stm_n.executeQuery("select players.player_name,battle.injuries_suffered from battle Inner join players on players.player_id=battle.player_id where injuries_suffered=(select max(injuries_suffered) from battle) limit 1;");
            rs_n.next();

            mostDmg.add(rs_n.getString(1));
            mostDmg.add(rs_n.getInt(2));

            return mostDmg;

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            System.out.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mostDmg;
    }

    public static ArrayList<ArrayList> getRanking() {
        ArrayList<ArrayList> rank = new ArrayList();
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;
            //selecting the ranks ordered
            rs=stm.executeQuery("select * from ranking order by total_points DESC");
            ResultSetMetaData obj= rs.getMetaData();
            //showing the first ten
            for (int i = 1; i < 11; ++i){
                ArrayList<String> campo = new ArrayList();
                if (rs.next()){
                    Statement stm2=con.createStatement();
                    ResultSet rs2=stm2.executeQuery("select player_name from players where player_id="+rs.getInt(1));
                    rs2.next();
                    campo.add(rs2.getString(1));
                    campo.add(rs.getString(2));
                    campo.add(rs.getString(3));
                    rank.add(campo);
                }
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rank;
    }

    public static Race getHuman(){
        Race human = null;
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC", user, passwd);
            //getting every aspect of the race human so when we want to create one e can just call this function
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from race where race_name='human';");
            rs.next();
            human= new Race(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return human;
        }
    }
    public static Race getElf(){
        Race elf = null;
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC", user, passwd);
            //getting every aspect of the race elf so when we want to create one e can just call this function
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from race where race_name='elf';");
            rs.next();
            elf= new Race(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return elf;
        }
    }
    public static Race getDwarf(){
        Race dwarf = null;
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC", user, passwd);
            //getting every aspect of the race dwarf so when we want to create one e can just call this function
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from race where race_name='dwarf';");
            rs.next();
            dwarf= new Race(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return dwarf;
        }
    }


    public static void downloadWarriors(Warriors w){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            Statement stm=con.createStatement();
            ResultSet rs=null;
            //selecting every warrior and creating a new warrior for each one
            rs=stm.executeQuery("select * from warriors");
            while (rs.next()){

                if (rs.getString(3).equalsIgnoreCase("human")){
                    w.getWarriors().add(new Human(rs.getString(1),getHuman(),
                            null,
                            rs.getString(2),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getString(13),
                            rs.getString(14),
                            rs.getString(15),
                            rs.getString(16),
                            rs.getString(17),
                            rs.getString(18)));
                }
                else if (rs.getString(3).equalsIgnoreCase("dwarf")){
                    w.getWarriors().add(new Dwarf(
                            rs.getString(1),
                            getDwarf(),
                            null,
                            rs.getString(2),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getString(13),
                            rs.getString(14),
                            rs.getString(15),
                            rs.getString(16),
                            rs.getString(17),
                            rs.getString(18)));
                }
                else if (rs.getString(3).equalsIgnoreCase("elf")){
                    w.getWarriors().add(new Elf(rs.getString(1),getElf(),
                            null,
                            rs.getString(2),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getString(13),
                            rs.getString(14),
                            rs.getString(15),
                            rs.getString(16),
                            rs.getString(17),
                            rs.getString(18)));
                }
            }

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            framePrincipal.setMySqlCon(new Connect());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void downloadWeapons(Weapons w){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            Statement stm=con.createStatement();
            ResultSet rs=null;
            //selecting every weapon and creating a new weapon for each one
            rs=stm.executeQuery("select * from weapons");

            while (rs.next()){
                ArrayList<Race>raza= new ArrayList<>();
                if (rs.getString(5).equalsIgnoreCase("human")){
                    raza.add(getHuman());
                    w.getWeapons().add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("dwarf")){
                    raza.add(getDwarf());
                    w.getWeapons().add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("elf")){
                    raza.add(getElf());
                    w.getWeapons().add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,elf")){
                    raza.add(getHuman());
                    raza.add(getElf());
                    w.getWeapons().add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,dwarf")){
                    raza.add(getHuman());
                    raza.add(getDwarf());
                    w.getWeapons().add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,elf,dwarf")){
                    raza.add(getHuman());
                    raza.add(getElf());
                    raza.add(getDwarf());
                    w.getWeapons().add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
            }

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            framePrincipal.setMySqlCon(new Connect());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void uploadBattle(int idP){
        //getting the user and his enemy
        User player=framePrincipal.getPlayPN().getPlayerUser();
        User enemy=framePrincipal.getPlayPN().getEnemyUser();

        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            int idB;
            //checking the last id inserted
            Statement stm_idB=con.createStatement();
            ResultSet rs_idB=stm_idB.executeQuery("Select count(*),max(battle_id) from battle");
            rs_idB.next();
            if (rs_idB.getInt(1)==0){
                idB= 1;
            } else {
                idB=rs_idB.getInt(2)+1;
            }
            //inserting the values
            PreparedStatement pstm = con.prepareStatement("insert into battle values (?,?,?,?,?,?,?,?,?)");
            pstm.setInt(1,idB);
            pstm.setInt(2,idP);
            pstm.setString(3,player.getWarrior().getName());
            pstm.setString(4,player.getWarrior().getWeapon().getName());
            pstm.setString(5,enemy.getWarrior().getName());
            pstm.setString(6,player.getWarrior().getWeapon().getName());
            pstm.setInt(7,player.getInjuriesCaused());
            pstm.setInt(8,player.getInjuriesSuffered());
            pstm.setInt(9,player.getPoints());
            pstm.executeUpdate();

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public int uploadUser() {
        int id = 0;
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC", user, passwd);
            //getting the user
            User player = framePrincipal.getPlayPN().getPlayerUser();
            //checking the past id inserted
            Statement stm_id = con.createStatement();
            ResultSet rs_id = stm_id.executeQuery("select count(*),max(player_id) from players;");
            rs_id.next();
            if (rs_id.getInt(1) == 0) {
                id = 1;
            } else {
                id = rs_id.getInt(2) + 1;
            }
            //inserting the player in players and ranking
            Statement stm_pl = con.createStatement();
            stm_pl.executeUpdate("insert into players values(" + id + ",\"" + player.getName() + "\")");

            Statement stm_rk = con.createStatement();
            stm_rk.executeUpdate("insert into ranking values(" + id + "," + player.getPoints() + ",\"" + player.getWarrior().getName() + "\")");

        } catch (SQLSyntaxErrorException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("el username peta, no se añadira a la db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }
}