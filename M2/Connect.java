package proyecto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Connect {

    //SELECTS
    public static void seeWeapons(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from weapons");
            ResultSetMetaData obj= rs.getMetaData();
            System.out.println("Weapons");
            System.out.println("--------------------------");
            while (rs.next()){

                System.out.println(obj.getColumnName(1)+": "+rs.getInt(1));
                System.out.println(obj.getColumnName(2)+": "+rs.getString(2));
                System.out.println(obj.getColumnName(3)+": "+rs.getString(3));
                System.out.println(obj.getColumnName(4)+": "+rs.getInt(4));
                System.out.println(obj.getColumnName(5)+": "+rs.getInt(5));
                System.out.println(obj.getColumnName(6)+": "+rs.getString(6));
                System.out.println(obj.getColumnName(7)+": "+rs.getInt(7));
                System.out.println("--------------------------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void seeRace(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from race");
            ResultSetMetaData obj= rs.getMetaData();
            System.out.println("Races");
            System.out.println("--------------------------");
            while (rs.next()){

                System.out.println(obj.getColumnName(1)+": "+rs.getString(1));
                System.out.println(obj.getColumnName(2)+": "+rs.getInt(2));
                System.out.println(obj.getColumnName(3)+": "+rs.getInt(3));
                System.out.println(obj.getColumnName(4)+": "+rs.getInt(4));
                System.out.println(obj.getColumnName(5)+": "+rs.getInt(5));
                System.out.println(obj.getColumnName(6)+": "+rs.getInt(6));
                System.out.println(obj.getColumnName(7)+": "+rs.getInt(7));
                System.out.println("--------------------------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void seeBattle(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from battle");
            ResultSetMetaData obj= rs.getMetaData();
            System.out.println("Battles");
            System.out.println("--------------------------");
            while (rs.next()){

                System.out.println(obj.getColumnName(1)+": "+rs.getInt(1));
                System.out.println(obj.getColumnName(2)+": "+rs.getInt(2));
                System.out.println(obj.getColumnName(3)+": "+rs.getString(3));
                System.out.println(obj.getColumnName(4)+": "+rs.getString(4));
                System.out.println(obj.getColumnName(5)+": "+rs.getString(5));
                System.out.println(obj.getColumnName(6)+": "+rs.getString(6));
                System.out.println(obj.getColumnName(7)+": "+rs.getString(7));
                System.out.println(obj.getColumnName(7)+": "+rs.getInt(7));
                System.out.println(obj.getColumnName(8)+": "+rs.getInt(8));
                System.out.println(obj.getColumnName(9)+": "+rs.getInt(9));

                System.out.println("--------------------------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void seeWarriors(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from warriors");
            ResultSetMetaData obj= rs.getMetaData();
            System.out.println("Warriors");
            System.out.println("--------------------------");
            while (rs.next()){

                System.out.println(obj.getColumnName(1)+": "+rs.getString(1));
                System.out.println(obj.getColumnName(2)+": "+rs.getString(2));
                System.out.println(obj.getColumnName(3)+": "+rs.getString(3));
                System.out.println(obj.getColumnName(4)+": "+rs.getString(4));
                System.out.println(obj.getColumnName(5)+": "+rs.getString(5));
                System.out.println(obj.getColumnName(6)+": "+rs.getString(6));
                System.out.println(obj.getColumnName(7)+": "+rs.getString(7));
                System.out.println(obj.getColumnName(8)+": "+rs.getString(8));
                System.out.println(obj.getColumnName(9)+": "+rs.getString(9));
                System.out.println(obj.getColumnName(10)+": "+rs.getString(10));
                System.out.println(obj.getColumnName(11)+": "+rs.getString(11));
                System.out.println(obj.getColumnName(12)+": "+rs.getString(12));
                System.out.println(obj.getColumnName(13)+": "+rs.getString(13));
                System.out.println(obj.getColumnName(14)+": "+rs.getString(14));
                System.out.println(obj.getColumnName(15)+": "+rs.getString(15));
                System.out.println(obj.getColumnName(16)+": "+rs.getString(16));
                System.out.println(obj.getColumnName(17)+": "+rs.getString(17));

                System.out.println("--------------------------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void seePlayers(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from players");
            ResultSetMetaData obj= rs.getMetaData();
            System.out.println("Players");
            System.out.println("--------------------------");
            while (rs.next()){

                System.out.println(obj.getColumnName(1)+": "+rs.getInt(1));
                System.out.println(obj.getColumnName(2)+": "+rs.getString(2));

                System.out.println("--------------------------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void seeRanking(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("select * from ranking");
            ResultSetMetaData obj= rs.getMetaData();
            System.out.println("Ranking");
            System.out.println("--------------------------");
            while (rs.next()){

                System.out.println(obj.getColumnName(1)+": "+rs.getInt(1));
                System.out.println(obj.getColumnName(2)+": "+rs.getInt(2));
                System.out.println(obj.getColumnName(3)+": "+rs.getInt(3));

                System.out.println("--------------------------");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //INSERTS
    public static void insertWarriors(String user,String passwd) {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            Statement stm=con.createStatement();
            ResultSet rs=null;

            Warriors w= new Warriors();
            Scanner s = new Scanner(System.in);

//            rs=stm.executeQuery("Select warrior_id from warriors order by warrior_id desc limit 1");
//            rs.next();
//            int id;
//            try {
//                id= rs.getInt(1)+1;
//            }
//            catch (Exception e){
//                 id=1;
//            }
            System.out.println("Que personaje quieres insertar?");
            int contador=0;
            for (Warrior a:w.warriors){
                contador++;
                System.out.println(contador+".- "+a.getName());
            }
            int opcion=0;
            opcion=s.nextInt();

            PreparedStatement pstm = con.prepareStatement("insert into warriors values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1,w.warriors.get(opcion).getName());
            pstm.setString(2,w.warriors.get(opcion).getPortrait());
            pstm.setString(3,w.warriors.get(opcion).getRace().name);
            pstm.setString(4,w.warriors.get(opcion).getFstandLoop());
            pstm.setString(5,w.warriors.get(opcion).getBstandLoop());
            pstm.setString(6,w.warriors.get(opcion).getFattack());
            pstm.setString(7,w.warriors.get(opcion).getBattack());
            pstm.setString(8,w.warriors.get(opcion).getFattack_bow());
            pstm.setString(9,w.warriors.get(opcion).getFattack_bow());
            pstm.setString(10,w.warriors.get(opcion).getFdie());
            pstm.setString(11,w.warriors.get(opcion).getBdie());
            pstm.setString(12,w.warriors.get(opcion).getFwound());
            pstm.setString(13,w.warriors.get(opcion).getBwound());
            pstm.setString(14,w.warriors.get(opcion).getFdodge());
            pstm.setString(15,w.warriors.get(opcion).getBdodge());
            pstm.setString(16,w.warriors.get(opcion).getDance());
            pstm.setString(17,w.warriors.get(opcion).getCry());
            pstm.setString(18,w.warriors.get(opcion).getFstandLoop());

            pstm.executeUpdate();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void downloadWarriors(Warriors w,String user,String passwd){
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
                    w.warriors.add(new Warrior(rs.getString(1),r.getHuman(),null,rs.getString(2),rs.getString(4),
                            rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                            rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                            rs.getString(15),rs.getString(16),rs.getString(17)));
                }
                else if (rs.getString(3).equalsIgnoreCase("dwarf")){
                    w.warriors.add(new Warrior(rs.getString(1),r.getDwarf(),null,rs.getString(2),rs.getString(4),
                            rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                            rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                            rs.getString(15),rs.getString(16),rs.getString(17)));
                }
                else if (rs.getString(3).equalsIgnoreCase("elf")){
                    w.warriors.add(new Warrior(rs.getString(1),r.getElf(),null,rs.getString(2),rs.getString(4),
                            rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                            rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                            rs.getString(15),rs.getString(16),rs.getString(17)));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void showWarriors(){
        Warriors w = new Warriors();
        Connect.downloadWarriors(w,"root","alumne");
        for (Warrior wep:w.warriors){
            System.out.println(wep.getName() + " " + wep.getRace().getName());

        }

        System.out.println();
    }


    public static void weaponDownload(Weapons w,String user,String passwd){
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
                    raza.add(r.getHuman());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("dwarf")){
                    raza.add(r.getDwarf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("elf")){
                    raza.add(r.getElf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,elf")){
                    raza.add(r.getHuman());
                    raza.add(r.getElf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,dwarf")){
                    raza.add(r.getHuman());
                    raza.add(r.getDwarf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
                else if (rs.getString(5).equalsIgnoreCase("human,elf,dwarf")){
                    raza.add(r.getHuman());
                    raza.add(r.getElf());
                    raza.add(r.getDwarf());
                    w.weapons.add(new Weapon(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2),raza,rs.getInt(6)));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void showWeapons(){
        Weapons w = new Weapons();
        Connect.weaponDownload(w,"root","alumne");
        for (Weapon wep:w.weapons){
            System.out.print(wep.getName() + " " + wep.getImage() + " " + wep.getSpeedBonus() + " " + wep.getStrenghtBonus()+" ");
            for (Race r:wep.getWielders()){
                System.out.print(r.getName()+" ");
            }
            System.out.println(wep.getPoints());
        }

        System.out.println();
    }

    public static void newParty(int player_id, String warrior_name,String warrior_weapon_name,String opponent_name,String opponent_weapon_name,String user,String passwd){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            Statement stm=con.createStatement();
            ResultSet rs=null;

            rs=stm.executeQuery("Select battle_id from battle order by battle_id desc limit 1");
            rs.next();
            int id;
            try {
                id= rs.getInt(1)+1;
            }
            catch (Exception e){
                 id=1;
            }
            PreparedStatement pstm = con.prepareStatement("insert into battle values (?,?,?,?,?,?,?,?,?)");
            pstm.setInt(1,id);
            pstm.setInt(2,player_id);
            pstm.setString(3,warrior_name);
            pstm.setString(4,warrior_weapon_name);
            pstm.setString(5,opponent_name);
            pstm.setString(6,opponent_weapon_name);
            pstm.setInt(7,0);
            pstm.setInt(8,0);
            pstm.setInt(9,0);
            pstm.executeUpdate();

            Warriors w= new Warriors();
            Scanner s = new Scanner(System.in);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void updateInjuriesCaused(int player_id,int num,String user,String passwd){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            PreparedStatement pstm = con.prepareStatement("update battle set injuries_caused=injuries_caused+"+num+" where player_id="+player_id);
            pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateInjuriesSuffered(int player_id,int num,String user,String passwd){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            PreparedStatement pstm = con.prepareStatement("update battle set injuries_sufered=injuries_sufered+"+num+" where player_id="+player_id);
            pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updatePoints(int player_id,int num,String user,String passwd){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);
            PreparedStatement pstm = con.prepareStatement("update battle set battle_points=battle_points+"+num+" where player_id="+player_id);
            pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void setRanking(int player_id,int total_points,String warrior_name,String user,String passwd){
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/battles?serverTimezone=UTC",user,passwd);

            PreparedStatement pstm = con.prepareStatement("insert into ranking values (?,?,?)");
            pstm.setInt(1,player_id);
            pstm.setInt(2,total_points);
            pstm.setString(3,warrior_name);
            pstm.executeUpdate();

            Warriors w= new Warriors();
            Scanner s = new Scanner(System.in);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
