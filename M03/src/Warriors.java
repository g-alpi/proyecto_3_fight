import java.util.ArrayList;

public class Warriors {
    ArrayList<Warrior> warriors = new ArrayList<Warrior>();
    public Warriors(){
        Connect.downloadWarriors(this,"root1","root1");
        /*
        Race race = new Race();
        warriors.add(new Warrior("Pepe",race.getHuman(),null,"./warriors/portrait_Pepe.png","./warriors/FstandLoop_Pepe.gif","./warriors/BstandLoop_Pepe.gif","./warriors/Fattack_Pepe.gif","./warriors/Battack_Pepe.gif","./warriors/Fattack_bow_Pepe.gif","./warriors/Battack_bow_Pepe.gif","./warriors/Fdie_Pepe.gif","./warriors/Bdie_Pepe.gif","./warriors/Fwound_Pepe.gif","./warriors/Bwound_Pepe.gif","./warriors/Fdodge_Pepe.gif","./warriors/Bdodge_Pepe.gif","./warriors/dance_Pepe.gif","./warriors/cry_Pepe.gif"));
        warriors.add(new Warrior("Maria",race.getHuman(),null,"./warriors/portrait_Maria.png","./warriors/FstandLoop_Maria.gif","./warriors/BstandLoop_Maria.gif","./warriors/Fattack_Maria.gif","./warriors/Battack_Maria.gif","./warriors/Fattack_bow_Maria.gif","./warriors/Battack_bow_Maria.gif","./warriors/Fdie_Maria.gif","./warriors/Bdie_Maria.gif","./warriors/Fwound_Maria.gif","./warriors/Bwound_Maria.gif","./warriors/Fdodge_Maria.gif","./warriors/Bdodge_Maria.gif","./warriors/dance_Maria.gif","./warriors/cry_Maria.gif"));
        warriors.add(new Warrior("Eduardo",race.getElf(),null,"./warriors/portrait_Eduardo.png","./warriors/FstandLoop_Eduardo.gif","./warriors/BstandLoop_Eduardo.gif","./warriors/Fattack_Eduardo.gif","./warriors/Battack_Eduardo.gif","./warriors/Fattack_bow_Eduardo.gif","./warriors/Battack_bow_Eduardo.gif","./warriors/Fdie_Eduardo.gif","./warriors/Bdie_Eduardo.gif","./warriors/Fwound_Eduardo.gif","./warriors/Bwound_Eduardo.gif","./warriors/Fdodge_Eduardo.gif","./warriors/Bdodge_Eduardo.gif","./warriors/dance_Eduardo.gif","./warriors/cry_Eduardo.gif"));
        warriors.add(new Warrior("Epifania",race.getElf(),null,"./warriors/portrait_Epifania.png","./warriors/FstandLoop_Epifania.gif","./warriors/BstandLoop_Epifania.gif","./warriors/Fattack_Epifania.gif","./warriors/Battack_Epifania.gif","./warriors/Fattack_bow_Epifania.gif","./warriors/Battack_bow_Epifania.gif","./warriors/Fdie_Epifania.gif","./warriors/Bdie_Epifania.gif","./warriors/Fwound_Epifania.gif","./warriors/Bwound_Epifania.gif","./warriors/Fdodge_Epifania.gif","./warriors/Bdodge_Epifania.gif","./warriors/dance_Epifania.gif","./warriors/cry_Epifania.gif"));
        warriors.add(new Warrior("Torbjorn",race.getDwarf(),null,"./warriors/portrait_Torbjorn.png","./warriors/FstandLoop_Torbjorn.gif","./warriors/BstandLoop_Torbjorn.gif","./warriors/Fattack_Torbjorn.gif","./warriors/Battack_Torbjorn.gif","./warriors/Fattack_bow_Torbjorn.gif","./warriors/Battack_bow_Torbjorn.gif","./warriors/Fdie_Torbjorn.gif","./warriors/Bdie_Torbjorn.gif","./warriors/Fwound_Torbjorn.gif","./warriors/Bwound_Torbjorn.gif","./warriors/Fdodge_Torbjorn.gif","./warriors/Bdodge_Torbjorn.gif","./warriors/dance_Torbjorn.gif","./warriors/cry_Torbjorn.gif"));
        warriors.add(new Warrior("Torbjina",race.getDwarf(),null,"./warriors/portrait_Torbjina.png","./warriors/FstandLoop_Torbjina.gif","./warriors/BstandLoop_Torbjina.gif","./warriors/Fattack_Torbjina.gif","./warriors/Battack_Torbjina.gif","./warriors/Fattack_bow_Torbjina.gif","./warriors/Battack_bow_Torbjina.gif","./warriors/Fdie_Torbjina.gif","./warriors/Bdie_Torbjina.gif","./warriors/Fwound_Torbjina.gif","./warriors/Bwound_Torbjina.gif","./warriors/Fdodge_Torbjina.gif","./warriors/Bdodge_Torbjina.gif","./warriors/dance_Torbjina.gif","./warriors/cry_Torbjina.gif"));
         */
    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }
}
