package farm.objects;
import java.io.*;

public class LoadGame {
    private Farmer farmer;
    private Season season;

    public void fileIn(File f) {
        try {
            FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                Object o = in.readObject();
                if (o != null) {
                    if (o instanceof Farmer) {
                        farmer = (Farmer) o;
                    } else if (o instanceof Season) {
                        season = (Season) o;
                    }
                } else {
                    break;
                }
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public Season getSeason() {
        return season;
    }

}
