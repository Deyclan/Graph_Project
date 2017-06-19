package graphes;

import .*;
import java.util.ArrayList;

/**
 * Created by Brandon on 17/06/2017.
 */
public class ProachCommuneSearch implements Runnable {

    double searchRay = 0.1;
    ArrayList<Commune> communes;

    public ProachCommuneSearch(ArrayList<Commune> communeArrayList){
        communes = communeArrayList;
    }

    public ProachCommuneSearch(ArrayList<Commune> communeArrayList, double searchRay){
        communes = communeArrayList;
        this.searchRay = searchRay;
    }

    @Override
    public void run() {
        for (Commune commune:communes){

            System.out.print(ConsoleColors.ANSI_RED + commune.id +" "+ ConsoleColors.ANSI_RESET);
            System.out.print(ConsoleColors.ANSI_BLUE);

            searchProach(commune,1);

            if(commune.proachCommunes.size() < 2){
                searchProach(commune,2);
            }
            if(commune.proachCommunes.size() < 2){
                searchProach(commune,4);
            }

            System.out.print(ConsoleColors.ANSI_RESET);
            System.out.println();
        }
    }

    public void searchProach(Commune commune, double coefMultiplicateurSearchRay){
        for (Commune c:communes) {
            if (commune == c){break;}
            if (Math.sqrt(Math.pow((commune.latitude-c.latitude),2) + Math.pow((commune.longitude-c.longitude),2))<= searchRay*coefMultiplicateurSearchRay){
                commune.proachCommunes.add(c);
                commune.proachCommunesArcs.add(new Arc(commune,c));
                System.out.print(c.id+" ; ");
            }
        }
    }
}
