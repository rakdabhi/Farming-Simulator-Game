package farm.objects;

import java.io.*;

public class SaveGame {
    Farmer farmer;
    Season season;

    public SaveGame(Farmer f, Season s) {
        farmer = f;
        season = s;
    }

    public void fileOut(File f) {
        try {
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(farmer);
            out.writeObject(season);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

}
