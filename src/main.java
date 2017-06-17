import java.io.*;
import java.util.ArrayList;

/**
 * Created by Brandon on 17/06/2017.
 */
public class main {

    public static void main(String[] args) throws IOException {
        File f = new File("CommunesFrance.csv");
        String s;

        CSVReader reader = new CSVReader(f);
        /*
        while ((s=reader.readLine()) != null){
            System.out.println(s);
        }
        */


        ArrayList<Commune> arrayList = new ArrayList<>();
        arrayList = reader.getCommuneList();

        for (Commune c:arrayList) {
            System.out.println(c.longitude);
        }


        /*
        reader.readLine();
        s = reader.readLine();
        String[] tmp = s.split(";");
        System.out.println(tmp[0] + "\n" + tmp[1]  + "\n" + tmp[2]  + "\n" + tmp[3]  + "\n" + tmp[4]);

        String[] a = tmp[3].split(",");
        System.out.println(a[0]+"."+a[1]);
        */
    }

}
