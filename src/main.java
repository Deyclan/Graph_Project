import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Brandon on 17/06/2017.
 */
public class main {

    public static void main(String[] args) throws IOException {
        File f = new File("CommunesFrance.csv");
        String s;

        CSVReader reader = new CSVReader(f,1000);

        ArrayList<Commune> arrayList = new ArrayList<>();
        arrayList = reader.getCommuneList();
        int i = 0;
        for (Commune c:arrayList) {
            System.out.println(c.id +" ; " + c.nom +" ; "+ c.population+ " ; " + c.latitude+" ; " + c.longitude);
            i++;
        }
        System.out.println(i);

        

    }

}
