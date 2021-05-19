import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Connect {
    Frame framePrincipal = (Frame) Frame.getFrames()[0];
    ArrayList<String> credentials;
    static String user;
    static String passwd;

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
        credentials=framePrincipal.getMysqlCredentials();
        user=credentials.get(0);
        passwd=credentials.get(1);
    }

    public static ArrayList<ArrayList> getRanking() {
        ArrayList<ArrayList> rank = new ArrayList();
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from ranking order by total_points DESC");
            ResultSetMetaData obj= rs.getMetaData();

            for (int i = 1; i < 6; ++i){
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

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from race where race_name='human';");
            rs.next();
            human= new Race(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));

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

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from race where race_name='elf';");
            rs.next();
            elf= new Race(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));

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

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from race where race_name='dwarf';");
            rs.next();
            dwarf= new Race(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));

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

            rs=stm.executeQuery("select * from weapons");

            Race r = new Race();
            Scanner s = new Scanner(System.in);
            rs=stm.executeQuery("select * from warriors");
            while (rs.next()){

                Race race= new Race();
                if (rs.getString(3).equalsIgnoreCase("human")){
                    w.warriors.add(new Human(rs.getString(1),getHuman(),
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
                    w.warriors.add(new Dwarf(
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
                    w.warriors.add(new Elf(rs.getString(1),getElf(),
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

            rs=stm.executeQuery("select * from weapons");

            Race r = new Race();
            Scanner s = new Scanner(System.in);
            while (rs.next()){
                ArrayList<Race>raza= new ArrayList<>();
                if (rs.getString(5).equalsIgnoreCase("human")){
                    raza.add(getHuman());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("dwarf")){
                    raza.add(getDwarf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("elf")){
                    raza.add(getElf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,elf")){
                    raza.add(getHuman());
                    raza.add(getElf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,dwarf")){
                    raza.add(getHuman());
                    raza.add(getDwarf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,elf,dwarf")){
                    raza.add(getHuman());
                    raza.add(getElf());
                    raza.add(getDwarf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void uploadBattle(int idP){
        User player=framePrincipal.getPlayPN().getPlayerUser();
        User enemy=framePrincipal.getPlayPN().getEnemyUser();

        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            int idB;

            Statement stm_idB=con.createStatement();
            ResultSet rs_idB=stm_idB.executeQuery("Select count(*),max(battle_id) from battle");
            rs_idB.next();
            if (rs_idB.getInt(1)==0){
                idB= 1;
            } else {
                idB=rs_idB.getInt(2)+1;
            }

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

            User player = framePrincipal.getPlayPN().getPlayerUser();

            Statement stm_id = con.createStatement();
            ResultSet rs_id = stm_id.executeQuery("select count(*),max(player_id) from players;");
            rs_id.next();
            if (rs_id.getInt(1) == 0) {
                id = 1;
            } else {
                id = rs_id.getInt(2) + 1;
            }
            Statement stm_pl = con.createStatement();
            stm_pl.executeUpdate("insert into players values(" + id + ",\"" + player.getName() + "\")");

            Statement stm_rk = con.createStatement();
            stm_rk.executeUpdate("insert into ranking values(" + id + "," + player.getPoints() + ",\"" + player.getWarrior().getName() + "\")");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }
}