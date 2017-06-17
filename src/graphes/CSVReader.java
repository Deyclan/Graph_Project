package graphes;

import java.io.*;
import java.util.ArrayList;

/**
 * La classe nous permettant de lire les fichiers CSV.
 */
public class CSVReader {

    File file;
    BufferedReader bufferedReader;
    ArrayList<Commune> list;

    public CSVReader(File file) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = file;
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),"ASCII"));
    }

    public CSVReader(String url) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = new File(url);
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),"ASCII"));

    }

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

           list.add(new Commune(splited[0],
                   splited[1]
                   ,Integer.parseInt(splited[2]),
                   Float.parseFloat(formatFloatFRtoEN(splited[3]))
                   ,Float.parseFloat(formatFloatFRtoEN(splited[4]))));
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

}
