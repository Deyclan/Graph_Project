/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;

public class Dijkstra implements Runnable {

    ArrayList<Commune> communes;
    Commune depart;
    Commune arrivee;
    Commune currentCommune;
    HashMap<Commune, Double> lambda;

    public Dijkstra(ArrayList<Commune> communeArrayList, Commune depart, Commune arrivee) {
        this.communes = new ArrayList<>();
        for (Commune c : communeArrayList) {
            communes.add(c);
        }
        this.depart = depart;
        this.arrivee = arrivee;
    }

    @Override
    public void run() {
        currentCommune = depart;
        Arc tmp;
        lambda = new HashMap<>();
        for (Commune c : communes) {
            lambda.put(c, Double.POSITIVE_INFINITY);
        }
        lambda.replace(depart, (double) 0);

        for (Commune c : communes) {
            tmp = new Arc(c, currentCommune);
            if (c.getProachCommunesArcs().contains(tmp)) {
                lambda.replace(c, tmp.poids);
            } else {
                lambda.replace(c, Double.POSITIVE_INFINITY);
            }
        }

        double temp = Double.POSITIVE_INFINITY;
        Commune tempCommune = new Commune();
        while (communes.size() != 0) {
            for (Commune x : lambda.keySet()) {
                if (lambda.get(x) < temp) {
                    temp = lambda.get(x);
                    tempCommune = x;
                }
            }
            communes.remove(tempCommune);

            for (Commune i : communes) {
                Arc arc = new Arc(tempCommune, i);
                if (tempCommune.getProachCommunesArcs().contains(arc)) {
                    if ((lambda.get(tempCommune) + arc.poids) < lambda.get(i)) {
                        lambda.replace(i, lambda.get(tempCommune) + arc.poids);
                    }
                }
            }
        }

        printLambda(lambda);

    }

    public void printLambda(HashMap<Commune,Double> lambda){
        for (Commune c:lambda.keySet()){
            System.out.println(c.getId() + " => " + lambda.get(c));
        }
    }
}
