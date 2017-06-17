/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphes;

// Implementation d'un graphe
import graphes.uinterface.UI;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Graphe {

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    final static int c = 8; // constante utilisee dans la fonction de hachage
    private final int n; // nombre de sommets
    final int m; // nombre d'arcs
    final protected ArrayList<Integer>[] succ; // successeurs de chaque sommet
    final protected ArrayList<Integer>[] pred; // predecesseurs de chaque sommet
    final HashMap<Arc, Integer> weights; // poids des aretes	
    double[][] coordinates = null; // coordonnees des sommets pour affichage

    // constructeurs
    @SuppressWarnings("unchecked")
    public Graphe(int n, int m) {
        this.n = n;
        this.m = m;
        this.succ = new ArrayList[n];
        this.pred = new ArrayList[n];
        this.weights = new HashMap<Arc, Integer>(c * n);

        for (int k = 0; k < n; k++) {
            succ[k] = new ArrayList<Integer>();
            pred[k] = new ArrayList<Integer>();
        }
    }

    public Graphe(int n, int m, ArrayList<Integer>[] succ, ArrayList<Integer>[] pred, HashMap<Arc, Integer> weights) {
        this.n = n;
        this.m = m;
        this.succ = succ;
        this.pred = pred;
        this.weights = weights;
    }

    @SuppressWarnings("unchecked")
    public Graphe(int n, double p, int max) {
        this.n = n;
        int mtmp = 0;
        succ = new ArrayList[n];
        pred = new ArrayList[n];
        this.weights = new HashMap<Arc, Integer>(c * n);

        for (int k = 0; k < n; k++) {
            succ[k] = new ArrayList<Integer>();
            pred[k] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.random() < p) {
                    mtmp++;
                    int val = (int) (max * Math.random());
                    succ[i].add(j);
                    pred[j].add(i);
                    this.addWeightedArc(i, j, val);
                } else {
                }
            }
        }

        this.m = mtmp;
    }

    @SuppressWarnings("unchecked")
    public Graphe(String file) throws Exception {
        System.out.print("Loading road networks from file " + file + " ... ");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String dataLine = br.readLine();
        while (dataLine.charAt(0) != 'p') {
            dataLine = br.readLine();
        }

        String[] tokens = dataLine.split("\\s");
        this.n = Integer.parseInt(tokens[2]);
        this.m = Integer.parseInt(tokens[3]);

        succ = new ArrayList[n];
        pred = new ArrayList[n];
        this.weights = new HashMap<Arc, Integer>(c * getN());

        for (int k = 0; k < n; k++) {
            succ[k] = new ArrayList<Integer>();
            pred[k] = new ArrayList<Integer>();
        }

        while ((dataLine = br.readLine()) != null) {
            tokens = dataLine.split("\\s");
            if (tokens[0].equals("a")) {
                int i = Integer.parseInt(tokens[1]);
                int j = Integer.parseInt(tokens[2]);
                int v = Integer.parseInt(tokens[3]);
                succ[i - 1].add(j - 1);
                pred[j - 1].add(i - 1);
                this.addWeightedArc(i - 1, j - 1, v);
            }
        }
        br.close();
        System.out.println("done");
    }

    // mise en place des coordonnees des sommets
    public void setCoordinates(String file) throws Exception {
        System.out.print("Loading geometric coordinates from file " + file + " ... ");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String dataLine = br.readLine();
        while (dataLine.charAt(0) != 'p') {
            dataLine = br.readLine();
        }

        String[] tokens = dataLine.split("\\s");
        int nPoints = Integer.parseInt(tokens[4]);
        if (nPoints != this.getN()) {
            br.close();
            throw new Error("The number of points does not match the number of nodes in the graph");
        }

        this.coordinates = new double[this.getN()][2];

        while ((dataLine = br.readLine()) != null) {
            tokens = dataLine.split("\\s");
            if (tokens[0].equals("v")) {
                int node = Integer.parseInt(tokens[1]);
                double x = Double.parseDouble(tokens[2]);
                double y = Double.parseDouble(tokens[3]);
                this.coordinates[node - 1][0] = x / 1000000.;
                this.coordinates[node - 1][1] = y / 1000000.;
            }
        }
        br.close();
        System.out.println("done");
    }

    public ArrayList<Integer> successors(int i) {
        return succ[i];
    }

    public ArrayList<Integer> predecessors(int i) {
        return pred[i];
    }

    // ajout d'un nouvel arc pondere
    public void addWeightedArc(int i, int j, int v) {
        this.weights.put(new Arc(i, j), v);
    }

    // poids de l'arc (i,j) s'il existe, 0 sinon
    public int weight(int i, int j) {
        if (!this.weights.containsKey(new Arc(i, j))) {
            return 0;
        }
        return this.weights.get(new Arc(i, j));
    }

    // renvoie le graphe ou toutes les orientations ont ete inversees
    public Graphe reverse() {
        HashMap<Arc, Integer> map = new HashMap<Arc, Integer>(c * getN());
        for (int i = 0; i < getN(); i++) {
            for (Integer j : this.succ[i]) {
                int val = this.weight(i, j);
                map.put(new Arc(j, i), val);
            }
        }
        Graphe rg = new Graphe(getN(), m, pred, succ, map);
        rg.coordinates = coordinates;
        return rg;
    }

    // affichage d'un graphe en chaine
    public String toString() {
        String s = "n=" + getN() + '\n' + "m=" + m + '\n';
        for (int i = 0; i < getN(); i++) {
            s = s + "Node " + i + " :" + '\n';
            for (int k : succ[i]) {
                s = s + "   " + i + " - " + k + " (" + this.weight(i, k) + ")"
                        + '\n';
            }
        }
        return s;
    }

    // fonctions d'affichage
    // dessin du graphe
    public void drawGraph(UI f) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            for (int i = 0; i < this.getN(); i++) {
                double x1 = this.coordinates[i][0];
                double y1 = this.coordinates[i][1];
                for (Integer j : this.pred[i]) {
                    double x2 = this.coordinates[j][0];
                    double y2 = this.coordinates[j][1];
                    f.addSegment(x1, y1, x2, y2, 1, Color.BLACK);
                }
                f.addPoint(x1, y1, 1, Color.BLACK);
            }
        }
    }

    // dessiner un point traite (rouge)
    public void drawSettledPoint(UI f, int p) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            f.addPoint(this.coordinates[p][0], this.coordinates[p][1], 3, Color.RED);
        }
    }

    // dessiner un point visitÃ© et a traiter (vert)
    public void drawUnsettledPoint(UI f, int p) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            f.addPoint(this.coordinates[p][0], this.coordinates[p][1], 3, Color.GREEN);
        }
    }

    // dessiner la source et la destination
    public void drawSourceDestination(UI f, int origin, int destination) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            f.addPoint(this.coordinates[origin][0], this.coordinates[origin][1], 6, Color.BLUE);
            f.addPoint(this.coordinates[destination][0], this.coordinates[destination][1], 6, Color.BLUE);
        }
    }

    // dessiner un point special
    public void drawSpecialPoint(UI f, int p) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            f.addPoint(this.coordinates[p][0], this.coordinates[p][1], 6, Color.BLUE);
        }
    }

    // dessiner le chemin en utilisant l'information contenue dans pred
    public void drawPath(UI f, int[] pred, int i) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            double x1 = this.coordinates[i][0];
            double y1 = this.coordinates[i][1];
            while (pred[i] != -1 && pred[i] != i) {
                double x2 = this.coordinates[pred[i]][0];
                double y2 = this.coordinates[pred[i]][1];
                f.addSegment(x1, y1, x2, y2, 10, Color.BLUE);
                x1 = x2;
                y1 = y2;
                i = pred[i];
            }
        }
    }

    // dessiner le chemin forme de deux morceaux se rejoignant
    public void drawPath(UI f, int[] predF, int[] predB, int x, int i, int j) {
        if (f != null && this.coordinates != null) { // verification donnees geometriques
            if (i == -1) {
                drawPath(f, predF, x);
                drawPath(f, predB, x);
                drawSpecialPoint(f, x);
            } else {
                f.addSegment(this.coordinates[i][0], this.coordinates[i][1], this.coordinates[j][0], this.coordinates[j][1], 10, Color.BLUE);
                drawPath(f, predF, i);
                drawPath(f, predB, j);
                drawSpecialPoint(f, i);
                drawSpecialPoint(f, j);
            }
        }
    }
}
