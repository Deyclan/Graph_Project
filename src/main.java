import java.io.*;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 * Main de l'application.
 */
public class main {

    public static void main(String[] args) throws IOException, Exception {
        /*
        File f = new File("CommunesFrance.csv");
        CSVReader reader = new CSVReader(f);
        ArrayList<Commune> arrayList;
        arrayList = reader.getCommuneList(1000);

        System.out.println(arrayList.size());

        System.out.println("Enter to continue");
        System.in.read();

        ProachCommuneSearch p = new ProachCommuneSearch(arrayList);
        p.run();

        System.out.println("Enter to continue");
        System.in.read();


        for (Commune c:arrayList) {
            if (c.proachCommunes == null) {
                System.out.println(c.id + " | " + null);
            } else if (c.proachCommunes.size() != 0){
                System.out.println(c.id + " | " + c.proachCommunes.get(0).id);
            }
        }
        */

        /*
        for (Commune c:arrayList){
            System.out.println(c.id);
        }
        */

        /*
        reader.readLine();
        s = reader.readLine();
        String[] tmp = s.split(";");
        System.out.println(tmp[0] + "\n" + tmp[1]  + "\n" + tmp[2]  + "\n" + tmp[3]  + "\n" + tmp[4]);

        String[] a = tmp[3].split(",");
        System.out.println(a[0]+"."+a[1]);
        */


        CSVReader csvReader = new CSVReader("CommunesFrance.csv");
        ArrayList<Commune> arrayList;
        arrayList = csvReader.getCommuneList(1000);
        Commune start = (Commune) arrayList.get(10);
        Commune end = (Commune) arrayList.get(20);
        
        System.out.println(start.getNom()+"\n");
        
        System.out.println(start.getNom()+"\n");
        
        ProachCommuneSearch p = new ProachCommuneSearch(arrayList,0.1);
        p.run();

        

        AStar aStar = new AStar(start,end);
        ArrayList<Commune> arrayList2;
        arrayList2 = aStar.getShortestWay();
        
        for (Commune c : arrayList2){
            System.out.println("\nPlus court chemin trouvé:"+c.getNom());
        }
        

    }

}
