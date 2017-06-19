import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Classe representant l'arc d'un graphe.
 */
public class Arc {

    Commune depart;
    Commune arrivee;
    double poids;

    public Arc(Commune depart, Commune arrivee){
        this.depart = depart;
        this.arrivee = arrivee;
        this.poids = Math.sqrt(Math.pow((depart.getLatitude()-arrivee.getLatitude()),2) + Math.pow((depart.getLongitude()-arrivee.getLongitude()),2));
    }

    public Arc(){}

    public void drawArc(Group group, Color color){
        Line line = new Line();
        line.setStroke(color);
        line.setStartX(CommuneMap.scaleLongitude(depart.getLongitude()));
        line.setStartY(CommuneMap.scaleLatitude(depart.getLatitude()));
        line.setEndX(CommuneMap.scaleLongitude(arrivee.getLongitude()));
        line.setEndY(CommuneMap.scaleLatitude(arrivee.getLatitude()));
        group.getChildren().add(line);
    }

    public Commune getArrivee() { return arrivee; }
    public Commune getDepart() { return depart; }
    public double getPoids() { return poids; }
}
