import java.util.ArrayList;

/**
 * Created by Brandon on 17/06/2017.
 */
public class Commune {

    String id;
    String nom;
    int population;
    float longitude;
    float latitude;
    public ArrayList<Commune> proachCommunes;
    public ArrayList<Arc> proachCommunesArcs;

    public Commune(String id, String nom, int population, float longitude, float latitude){
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
        this.proachCommunes = new ArrayList<>();
        this.proachCommunesArcs= new ArrayList<>();
    }

}
