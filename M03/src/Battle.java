import java.util.ArrayList;
import java.util.Random;

public class Battle {

    public ArrayList<User> firstOrder(User u1,User u2){  //orders the users by speed
        ArrayList<User> order= new ArrayList<User>();
        u1.setInjuriesCaused(0);
        u1.setInjuriesSuffered(0);  //resets injuries so when you play more than one time they dont add up (firstorder just happens at the start of the fight)
        u2.setInjuriesCaused(0);
        u2.setInjuriesSuffered(0);
        if (u1.getName().equalsIgnoreCase("hero")){  //checking for cheatcodes
            u1.getWarrior().getRace().strenght=9999;
        }else if (u1.getName().equalsIgnoreCase("villain")){
            u2.getWarrior().getRace().strenght=9999;
        }

        if (u1.getWarrior().getTrueSpeed()>u2.getWarrior().getTrueSpeed()){
            order.add(u1);
            order.add(u2);
            return order;   //returns the one that will attack on position 0 and the defender on position 1
        }
        order.add(u2);
        order.add(u1);
        return order;
    }

    public int attack(User attacker, User defender){
        int Str_a;
        int Sp_a;
        int rand=new Random().nextInt(101);
        if (attacker.getWarrior().getRace().getAgility()*10>rand){ //if the agility of the attacker*10 doesnt surpass the random number the atack fails
            rand=new Random().nextInt(51);
            if (defender.getWarrior().getRace().getAgility()>rand){ //if the agility of the defender surpases the random number he dodges the attack
                return 3; // 3 dodged attack
            }
        }
        else{
            return 2; // 2 missed attack
        }
        return 1; // 1 succesfull attack
    }

    public String resultAtack(User attacker, User defender){ //calculates the damage and returns the string that will be shown
        int dmg=attacker.getWarrior().getTrueStrength()-defender.getWarrior().getRace().getDefence();
        attacker.setInjuriesCaused(attacker.getInjuriesCaused()+dmg); //adds the injuries caused and suffered to the players
        defender.setInjuriesSuffered(defender.getInjuriesSuffered()+dmg);
        defender.getWarrior().getRace().setHealth(defender.getWarrior().getRace().getHealth()-dmg);
        return attacker.getWarrior().getName()+" attacks "+defender.getWarrior().getName()+" dealing "+dmg+" damage";
    }

    public ArrayList<User> order(User attacker, User defender){ //reorders the users for the next turn
        ArrayList<User> order = new ArrayList<>();
        if (attacker.getWarrior().getTrueSpeed()<defender.getWarrior().getTrueSpeed()){
            order.add(defender);
            order.add(attacker);
            return order;
        }
        int rand=new Random().nextInt(101);
        if ((attacker.getWarrior().getTrueSpeed()-defender.getWarrior().getTrueSpeed())*10>rand){
            order.add(attacker);
            order.add(defender);
            return order;       //returns the one that will attack on position 0 and the defender on position 1
        }
        order.add(defender);
        order.add(attacker);
        return order;
    }

    public boolean isBot(User user){

        if (user.getName().equalsIgnoreCase("bot")){
            return true;
        }else{
            return false;
        }


    }

}
