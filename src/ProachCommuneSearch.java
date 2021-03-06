import java.util.ArrayList;

/**
 * Classe recherchant les communes voisines et créant les arcs entre communes.
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

            System.out.print(ConsoleColors.ANSI_RED + commune.getId() +" "+ ConsoleColors.ANSI_RESET);
            System.out.print(ConsoleColors.ANSI_BLUE);

            searchProach(commune,1);

            if(commune.getProachCommunes().size() < 2){
                searchProach(commune,2);
            }
            if(commune.getProachCommunes().size() < 2){
                searchProach(commune,4);
            }

            System.out.print(ConsoleColors.ANSI_RESET);
            System.out.println();
        }
    }

    public void searchProach(Commune commune, double coefMultiplicateurSearchRay){
        for (Commune c:communes) {
            if (commune == c){break;}
            if (Math.sqrt(Math.pow((commune.getLatitude()-c.getLatitude()),2) + Math.pow((commune.getLongitude()-c.getLongitude()),2))<= searchRay*coefMultiplicateurSearchRay){
                commune.getProachCommunes().add(c);
                commune.getProachCommunesArcs().add(new Arc(commune,c));
                System.out.print(c.getId()+" ; ");
            }
        }
    }
}
