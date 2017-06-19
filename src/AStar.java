
import java.util.ArrayList;
import java.util.Collections;

/**
 * Algorithme de A*, pour chercher le plus court chemin entre deux communes
 */
public class AStar {

    private Commune startCommune;
    private Commune endCommune;
    private ArrayList<Commune> openList;
    private ArrayList<Commune> closedList;
    private ProachCommuneSearch graphe;

    public AStar(ProachCommuneSearch graphe, Commune startCommune, Commune endCommune) {
        this.graphe = graphe;
        this.startCommune = startCommune;
        this.endCommune = endCommune;
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
    }

    /**
     * a* algorithm to find the best path between two states
     *
     * @return
     */
    public ArrayList<Commune> getShortestWay() {

        // list of visited nodes
        closedList = new ArrayList<>();
        // list of nodes to evaluate
        openList = new ArrayList<>();
        openList.add(this.startCommune);
        // no cost to go from start to start
        this.startCommune.setG(0);
        this.startCommune.setH(evaluation(this.startCommune));
        this.startCommune.setF(this.startCommune.getH());
        Commune current;
        // while there is still a node to evaluate        
        while (!openList.isEmpty()) {
            // choose the node having a F minimal
            current = chooseBestNode();
            // stop if the node is the goal
            if (current == this.endCommune) {
                return rebuildPath(current);
            }
            openList.remove(current);
            closedList.add(current);
            // construct the list of neighbors
            ArrayList<Commune> neighbors = current.getProachCommunes();
            for (Commune ndn : neighbors) {
                // if the neighbors has been evaluated, skip it
                if (closedList.contains(ndn)) {
                    continue;
                }
                // cost to reach the neighbor is the cost to reach current + cost from current to the neighbourg
                int cost = (int) (current.getG() + costBetween(current, ndn));
                boolean bestCost = false;
                // if the neighbor has not been evaluated
                if (!openList.contains(ndn)) {
                    openList.add(ndn);
                    ndn.setH(evaluation(ndn));
                    bestCost = true;
                } else // if the neighbor has been evaluated to a more important cost, change its evaluation
                if (cost < ndn.getG()) {
                    bestCost = true;
                }
                if (bestCost) {
                    ndn.setParent(current);
                    ndn.setG(cost);
                    ndn.setF(ndn.getG() + ndn.getH());
                }
            }
        }
        return null;
    }

    private Commune chooseBestNode() {
        Collections.sort(openList);
        return openList.get(0);
    }

    // return the estimation of the distance from c to the goal
    private int evaluation(Commune c) {
        int retour = 10 * (int) distance(c, this.endCommune);
        return retour;
    }

    /**
     * Return the distance between the cells
     */
    double distance(Commune a, Commune b) {
        double retour = Math.sqrt(Math.pow((a.getLatitude() - b.getLatitude()), 2) + Math.pow((a.getLongitude() - b.getLongitude()), 2));
        return retour;
    }

    /**
     * Builds the arraylist of cummunes which represent the shortest way
     */
    private ArrayList<Commune> rebuildPath(Commune current) {
        if (current.getParent() != null) {
            ArrayList<Commune> p = rebuildPath(current.getParent());
            current.setVisited(true);
            p.add(current);
            return p;
        } else {
            return (new ArrayList<>());
        }
    }

    /**
     * return the cost from n to c : 10 for a lateral move, 14
     * (squareroot(2)*10) for a diagonal move
     *
     * @param n a node/cell
     * @param c a node/cel close to n
     * @return distance between the two adjacent nodes n and c
	 *
     */
    int costBetween(Commune n, Commune c) {
        int difx = (int) Math.abs(n.getLongitude() - c.getLongitude());
        int dify = (int) Math.abs(n.getLatitude() - c.getLatitude());
        assert difx <= 1 && dify <= 1 : "pb in costBetween, n and c are not adjacent !! ";
        int retour = 10 * (difx + dify);
        if (retour >= 20) {
            retour = 14;
        }
        return retour;
    }

    /**
     * return the cost from n to c : 10 for a longitudinal move, 14
     * (squareroot(2)*10) for a diagonal move
     *
     * @param n a node/cell
     * @param c a node/cel close to n
     * @return distance between the two adjacent nodes n and c
     */
    int costBetweenWithoutNuisance(Commune n, Commune c) {
        int difx = (int) Math.abs(n.getLongitude() - c.getLongitude());
        int dify = (int) Math.abs(n.getLatitude() - c.getLatitude());
        assert difx <= 1 && dify <= 1 : "pb in costBetween, n and c are not adjacent !! ";
        int retour = 10 * (difx + dify);
        if (retour >= 20) {
            retour = 14;
        }
        return retour;
    }

}
