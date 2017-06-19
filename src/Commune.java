
import java.util.ArrayList;

/**
 * Classe represantant une Commune.
 */
public class Commune {

    private String id;
    private String nom;
    private int population;
    private float longitude;
    private float latitude;
    private ArrayList<Commune> proachCommunes;
    private ArrayList<Arc> proachCommunesArcs;

    
    private int f; // evaluation of the distance from start to goal, through this cell
    private int g; // evaluation of the distance from start to this cell
    private int h; // evaluation of the distance from this cell to the goal

    public Commune(String id, String nom, int population, float longitude, float latitude) {
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
        this.proachCommunes = new ArrayList<>();
        this.proachCommunesArcs = new ArrayList<>();
    }

    // GETTERS
    public String getId() { return id; }
    public String getNom() { return nom; }
    public int getPopulation() { return population;}
    public float getLongitude() { return longitude; }
    public float getLatitude() { return latitude; }
    public ArrayList<Commune> getProachCommunes() { return proachCommunes; }
    public ArrayList<Arc> getProachCommunesArcs() { return proachCommunesArcs; }
    public int getF() { return f; }
    public int getG() { return g; }
    public int getH() { return h; }

    // SETTERS
    public void setProachCommunes(ArrayList<Commune> proachCommunes) {
        this.proachCommunes = proachCommunes;
    }
    public void setProachCommunesArcs(ArrayList<Arc> proachCommunesArcs) {
        this.proachCommunesArcs = proachCommunesArcs;
    }
    public void setF(int f) { this.f = f; }
    public void setG(int g) { this.g = g; }
    public void setH(int h) { this.h = h; }

}
