
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by Brandon on 18/06/2017.
 */
public class AStar implements Runnable {

    private Commune startCommune;
    private Commune endCommune;
    private ArrayList<Commune> chemin;
    private ArrayList<Arc> cheminArc;
    private ArrayList<Commune> openList;
    private ArrayList<Commune> closedList;
    private Commune currentCommune;

    public AStar(Commune startCommune, Commune endCommune) {
        this.startCommune = startCommune;
        this.endCommune = endCommune;
        this.chemin = new ArrayList<>();
        this.cheminArc = new ArrayList<>();
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.currentCommune = startCommune;
    }

    public Arc compareArcs(ArrayList<Arc> arcArrayList, double endLatitude, double endLongitude) {
        Arc tmp = new Arc();
        double tmpDistance = Integer.MAX_VALUE;
        for (Arc arc : arcArrayList) {
            if (!closedList.contains(arc.arrivee) && arc.arrivee.getProachCommunesArcs().size() != 0) {
                double dist = (Math.abs(endLatitude - arc.getArrivee().getLatitude())
                        + Math.abs(endLongitude - arc.getArrivee().getLongitude()));
                if (dist < tmpDistance) {
                    tmp = arc;
                    tmpDistance = dist;
                }
            }
        }
        return tmp;
    }

    public Commune nextCommune(Commune currentCommune, Commune endCommune) {
        Arc arc = compareArcs(currentCommune.getProachCommunesArcs(), endCommune.getLatitude(), endCommune.getLongitude());
        if (chemin.size() > 2) {
            if (arc.arrivee == chemin.get(chemin.size() - 1)) {
                closedList.add(arc.depart);
            }
        }
        this.chemin.add(arc.arrivee);
        this.cheminArc.add(arc);
        System.out.println(arc.arrivee.getId() + "  ||  " + arc.arrivee.getProachCommunesArcs().size());
        return arc.arrivee;
    }

    /**
     * a* algorithm to find the best path between two states
     *
     * @param _start initial state
     * @param _goal final state
     */
    ArrayList<Commune> algoASTAR() {

        // list of visited nodes
        closedList = new ArrayList<Commune>();
        // list of nodes to evaluate
        openList = new ArrayList<>();
        openList.add(this.startCommune);
        // no cost to go from start to start
        this.startCommune.setG(0);
        this.startCommune.setH(evalution(this.startCommune));
        // TODO: h(start) <- evaluation(start)
        // TODO: f(start) <- h(start)
        Commune current;
        // while there is still a node to evaluate        
        while (!openList.isEmpty()) {
            // choose the node having a F minimal
            current = chooseBestNode();
            // stop if the node is the goal
            if (current == this.endCommune) {
                return rebuildPath(n);
            }
            openList.remove(current);
            closedList.add(current);
            // construct the list of neighbourgs
            ArrayList<Commune> nextDoorNeighbours = neighbours(n);
            for (Commune ndn : nextDoorNeighbours) {
                // if the neighbour has been visited, do not reevaluate it
                if (closedList.contains(ndn)) {
                    continue;
                }
                // cost to reach the neighbour is the cost to reach n + cost from n to the neighbourg
                //TODO: int cost = ...
                boolean bestCost = false;
                // if the neighbour has not been evaluated
                if (!openList.contains(ndn)) {
                    openList.add(ndn);
                    // TODO: h(ndn) -> evaluation(ndn)
                    bestCost = true;
                } else // if the neighbour has been evaluated to a more important cost, change its evaluation
                if (cost < ndn.getG()) {
                    bestCost = true;
                }
                if (bestCost) {
                    ndn.setParent(n);
                    //TODO : g(ndn) <- cost
                    //TODO : f(ndn) <- g(ndn) + h(ndn)
                }
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (currentCommune != endCommune) {
            Commune tmp = nextCommune(currentCommune, endCommune);
            currentCommune = tmp;
        }

        for (Commune c : chemin) {
            System.out.print(c.getId() + "  =>  ");
        }
    }

    private Commune chooseBestNode() {
    Collections.sort(openList);
    return openList.get(0);    }

    // return the estimation of the distance from c to the goal*/
	int evaluation(Commune c)
	{		
		int retour =  10* (int)distance(c, this.endCommune);
		//retour = (int)((double) retour * c.getValue());
		return retour;
	}
        /**return the  distance between the cells*/
	double distance(Commune a, Commune b)
	{
		double retour = 0.0;
		retour = Math.sqrt(Math.pow((a.getLatitude()-b.getLatitude()),2) + Math.pow((a.getLongitude()-b.getLongitude()),2));
		return retour;
	}
}
