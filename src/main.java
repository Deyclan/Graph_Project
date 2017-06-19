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
        Commune start = (Commune) arrayList.get(8);
        System.out.println("Commune départ"+start.getNom());
        Commune end = (Commune) arrayList.get(2);
        System.out.println("Commune arrivée"+end.getNom());
        ProachCommuneSearch p = new ProachCommuneSearch(arrayList,0.1);
        p.run();

        AStar aStar = new AStar(p,start,end);
        ArrayList<Commune> arrayList2;
        arrayList2 = aStar.getShortestWay();
        
        
        

    }

}
