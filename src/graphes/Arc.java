/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes;

// arc d'un graphe
public class Arc {

    int origin; // origine de l'arc
    int destination; // destination de l'arc

    // constructeur
    public Arc(int o, int d) {
        this.origin = o;
        this.destination = d;
    }

    // redefinition de la fonction equals
    public boolean equals(Object o) {
        Arc a = (Arc) o;
        return this.origin == a.origin && this.destination == a.destination;
    }

    // redefinition du hashCode
    public int hashCode() {
        return Graphe.c * this.origin + this.destination;
    }

}
