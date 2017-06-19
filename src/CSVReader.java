import java.io.*;
import java.util.ArrayList;

/**
 * Created by Brandon on 17/06/2017.
 */
public class CSVReader {

    File file;
    BufferedReader bufferedReader;
    ArrayList<Commune> list;
    int minPopulation=0;


    public CSVReader(File file) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = file;
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),"ASCII"));
    }

    public CSVReader(String url) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = new File(url);
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),"ASCII"));

    }

    /*
    public CSVReader(File file, int minPopulation) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = file;
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),"ASCII"));
        this.minPopulation = minPopulation;
    }

    public CSVReader(String url, int minPopulation) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = new File(url);
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),"ASCII"));
        this.minPopulation = minPopulation;
    }
    */

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    public ArrayList<Commune> getCommuneList() throws IOException {
       this.list = new ArrayList<>();
       String tmp;
       String[] splited ;
       this.readLine();
       while ( (tmp = this.readLine()) != null){
           splited = tmp.split(";");

           Commune commune = new Commune(splited[0],
                   splited[1],
                   Integer.parseInt(splited[2]),
                   Float.parseFloat(formatFloatFRtoEN(splited[3])),
                   Float.parseFloat(formatFloatFRtoEN(splited[4])));
           if (filterMetropolitanFrance(commune)){
               list.add(commune);
           }
       }
       return list;
    }

    public ArrayList<Commune> getCommuneList(int minPopulation) throws IOException {
        this.list = new ArrayList<>();
        String tmp;
        String[] splited ;
        this.readLine();
        while ( (tmp = this.readLine()) != null){
            splited = tmp.split(";");

            Commune commune = new Commune(splited[0],
                    splited[1],
                    Integer.parseInt(splited[2]),
                    Float.parseFloat(formatFloatFRtoEN(splited[3])),
                    Float.parseFloat(formatFloatFRtoEN(splited[4])));
            if (filterMetropolitanFrance(commune) && filterPopulation(commune,minPopulation)){
                list.add(commune);
            }
        }
        return list;
    }

    public String formatFloatFRtoEN(String s){
        String[] tmp = s.split(",");
        if (tmp.length != 2){
            return s;
        }
        else {
            String output = tmp[0] + "." + tmp[1];
            return output;
        }
    }

    public boolean filterPopulation(Commune commune, int minPopulation){
        if (commune.population >= minPopulation){
            return true;
        }
        else return false;
    }

    public boolean filterMetropolitanFrance(Commune commune){
        if ((commune.longitude > -4.9 && commune.longitude < 9.55) &&
                (commune.latitude > 41.30 && commune.latitude < 51.1)){
            return true;
        }
        else return false;
    }


}
