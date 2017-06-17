/*
 * 
OPEN 
CLOSED 
add the start node to OPEN

loop
    current = node in OPEN with the lowest f_cost
    remove current from OPEN
    add current to CLOSED

    if current is the target node //path has been found
        return

    foreach neighbour of the current node
        if neighbour is not traversable or neighbour is in CLOSED
            skip to the next neighbour

        if new path to neighbour is shorter OR neighbour is not in OPEN
            set f_cost of neighbour
            set parent of neighbour to current 
            if neighbour is not in OPEN
                add neighbour to OPEN

 */
package graphes.algorithmes;

import graphes.Graphe;
import java.util.ArrayList;
import graphes.Sommet;
import graphes.uinterface.UI;

/**
 *
 * @author Nejko
 */
public class AEtoile {

    final Graphe g; // le graphe de travail
    final int n; // nombre de sommets de g
    final int source; // source du plus court chemin recherche
    final int dest; // destination du plus court chemin recherche
    UI f; // fenetre pour la visualisation
    private ArrayList<Integer> openList; 
    private ArrayList<Integer> closedList; 
    private boolean done;

    AEtoile(Graphe g, int depart, int arrivee) {
        this.g = g;
        n = g.getN();
        this.source = depart;
        this.dest = arrivee;
    }

    public ArrayList<Integer> findAStarPath() {
        openList = new ArrayList<>(); //the set of nodes to be evaluated
        closedList = new ArrayList<>(); //the set of nodes already evaluated
        openList.add(this.source); // add starting node to open list
        done = false;
        int current;
        while (!done) {
            current = lowestFInOpen(); // get node with lowest fCosts from openList
            closedList.add(current); // add current node to closed list
            openList.remove(current); // delete current node from open list

            if (current == this.dest) { // found goal
                return ;
            }

            // for all adjacent nodes:
            List<T> adjacentNodes = getAdjacent(current);
            for (int i = 0; i < adjacentNodes.size(); i++) {
                T currentAdj = adjacentNodes.get(i);
                if (!openList.contains(currentAdj)) { // node is not in openList
                    currentAdj.setPrevious(current); // set current node as previous for this node
                    currentAdj.sethCosts(nodes[newX][newY]); // set h costs of this node (estimated costs to goal)
                    currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
                    openList.add(currentAdj); // add node to openList
                } else { // node is in openList
                    if (currentAdj.getgCosts() > currentAdj.calculategCosts(current)) { // costs from current node are cheaper than previous costs
                        currentAdj.setPrevious(current); // set current node as previous for this node
                        currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
                    }
                }
            }

            if (openList.isEmpty()) { // no path exists
                return new ArrayList<>(); // return empty list
            }
        }
        return null; // unreachable
    }
}
