package graphes;

import java.util.ArrayList;

/**
 * Classe represantant une Commune.
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

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
