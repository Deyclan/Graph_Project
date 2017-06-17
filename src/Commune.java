/**
 * Created by Brandon on 17/06/2017.
 */
public class Commune {

    String id;
    String nom;
    int population;
    float longitude;
    float latitude;

    public Commune(String id, String nom, int population, float longitude, float latitude){
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
