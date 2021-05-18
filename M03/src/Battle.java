import java.util.ArrayList;
import java.util.Random;

public class Battle {

    public ArrayList<User> firstOrder(User u1,User u2){  //orders the users by speed
        ArrayList<User> order= new ArrayList<User>();
        if (u1.getName().equalsIgnoreCase("carloslachupa")){
            u1.warrior.getRace().strenght=9999;
        }

        if (u1.warrior.getTrueSpeed()>u2.warrior.getTrueSpeed()){
            order.add(u1);
            order.add(u2);
            return order;
        }
        order.add(u2);
        order.add(u1);
        return order;
    }

    public int attack(User attacker, User defender){
        int Str_a;
        int Sp_a;
        int rand=new Random().nextInt(101);
        if (attacker.warrior.getRace().getAgility()*10>rand){ //if the agility of the attacker*10 doesnt surpass the random number the atack fails
            rand=new Random().nextInt(51);
            if (defender.warrior.getRace().getAgility()>rand){ //if the agility of the defender surpases the random number he dodges the attack
                return 3; // 3 dodged attack
            }
        }
        else{
            return 2; // 2 missed attack
        }
        return 1; // 1 succesfull attack
    }

    public String resultAtack(User attacker, User defender){ //calculates the damage and returns the string that will be shown
        int dmg=attacker.warrior.getTrueStrength()-defender.warrior.race.getDefence();
        attacker.setInjuriesCaused(attacker.getInjuriesCaused()+dmg);
        defender.setInjuriesSuffered(defender.getInjuriesSuffered()+dmg);
        defender.warrior.getRace().setHealth(defender.warrior.getRace().getHealth()-dmg);
        return attacker.warrior.getName()+" attacks "+defender.warrior.getName()+" dealing "+dmg+" damage";
    }

    public ArrayList<User> order(User attacker, User defender){ //reorders the users for the next turn
        ArrayList<User> order = new ArrayList<>();
        if (attacker.warrior.getTrueSpeed()<defender.warrior.getTrueSpeed()){
            order.add(defender);
            order.add(attacker);
            return order;
        }
        int rand=new Random().nextInt(101);
        if ((attacker.warrior.getTrueSpeed()-defender.warrior.getTrueSpeed())*10>rand){
            order.add(attacker);
            order.add(defender);
            return order;
        }
        order.add(defender);
        order.add(attacker);
        return order;
    }

    public boolean isBot(User user){

        if (user.name.equalsIgnoreCase("bot")){
            return true;
        }else{
            return false;
        }


    }

}
