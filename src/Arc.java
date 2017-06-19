import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Created by Brandon on 17/06/2017.
 */
public class Arc {

    Commune depart;
    Commune arrivee;

    public Arc(Commune depart, Commune arrivee){
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public Arc(){}

    public void drawArc(Group group, Color color){
        Line line = new Line();
        line.setStroke(color);
        line.setStartX(CommuneMap.scaleLongitude(depart.longitude));
        line.setStartY(CommuneMap.scaleLatitude(depart.latitude));
        line.setEndX(CommuneMap.scaleLongitude(arrivee.longitude));
        line.setEndY(CommuneMap.scaleLatitude(arrivee.latitude));
        group.getChildren().add(line);
    }

    public Commune getArrivee() {
        return arrivee;
    }

    public Commune getDepart() {
        return depart;
    }
}
