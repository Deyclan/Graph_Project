/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

// arc d'un graphe
public class Arc {

    Commune depart; // origine de l'arc
    Commune arrivee; // destination de l'arc

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
