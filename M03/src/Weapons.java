import java.util.ArrayList;

public class Weapons {
    ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    public Weapons(){
        Connect.downloadWeapons(this,"root1","root1");

        /*
        Race race=new Race();
        ArrayList<Race> wielders = new ArrayList<>();

        wielders.add(race.getHuman());
        wielders.add(race.getElf());
        weapons.add(new Weapon("Dagger",3,0,"./weapons/Dagger.png",wielders,10));

        wielders= new ArrayList<>();
        wielders.add(race.getHuman());
        wielders.add(race.getElf());
        wielders.add(race.getDwarf());
        weapons.add(new Weapon("Sword",1,1,"./weapons/Sword.png",wielders,10));

        wielders= new ArrayList<>();
        wielders.add(race.getHuman());
        wielders.add(race.getDwarf());
        weapons.add(new Weapon("Axe",0,3,"./weapons/Axe.png",wielders,10));

        wielders= new ArrayList<>();
        wielders.add(race.getHuman());
        wielders.add(race.getElf());
        weapons.add(new Weapon("DualSwords",2,2,"./weapons/DualSwords.png",wielders,14));

        wielders= new ArrayList<>();
        wielders.add(race.getHuman());
        wielders.add(race.getElf());
        weapons.add(new Weapon("Scimitar",2,1,"./weapons/Scimitar.png",wielders,14));

        wielders= new ArrayList<>();
        wielders.add(race.getElf());
        weapons.add(new Weapon("Bow",5,1,"./weapons/Bow.png",wielders,15));

        wielders= new ArrayList<>();
        wielders.add(race.getHuman());
        weapons.add(new Weapon("Katana",3,2,"./weapons/Katana.png",wielders,18));

        wielders= new ArrayList<>();
        wielders.add(race.getHuman());
        wielders.add(race.getElf());
        wielders.add(race.getDwarf());
        weapons.add(new Weapon("Poniard",4,0,"./weapons/Poniard.png",wielders,12));

        wielders= new ArrayList<>();
        wielders.add(race.getDwarf());
        weapons.add(new Weapon("Greataxe",0,4,"./weapons/Greataxe.png",wielders,20));
         */
    }

}
