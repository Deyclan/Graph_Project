import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Brandon on 18/06/2017.
 */
public class AStar implements Runnable {

    private Commune startCommune;
    private Commune endCommune;
    private ArrayList<Commune> chemin;
    private ArrayList<Arc> cheminArc;
    private ArrayList<Commune> doNotGo;
    private Commune currentCommune;

    public AStar(Commune startCommune, Commune endCommune){
        this.startCommune = startCommune;
        this.endCommune = endCommune;
        this.chemin = new ArrayList<>();
        this.cheminArc = new ArrayList<>();
        this.doNotGo = new ArrayList<>();
        this.doNotGo.add(startCommune);
        this.currentCommune = startCommune;
    }

    public Arc compareArcs(ArrayList<Arc> arcArrayList, double endLatitude, double endLongitude){
        Arc tmp = new Arc();
        double tmpDistance = Integer.MAX_VALUE;
        for (Arc arc: arcArrayList){
            if (!doNotGo.contains(arc.arrivee) && arc.arrivee.proachCommunesArcs.size() != 0) {
                double dist = (Math.abs(endLatitude - arc.getArrivee().latitude) +
                        Math.abs(endLongitude - arc.getArrivee().longitude));
                if (dist < tmpDistance) {
                    tmp = arc;
                    tmpDistance = dist;
                }
            }
        }
        return tmp;
    }

    public Commune nextCommune(Commune currentCommune, Commune endCommune){
        Arc arc = compareArcs(currentCommune.proachCommunesArcs,endCommune.latitude,endCommune.longitude);
        if (chemin.size() > 2) {
            if (arc.arrivee == chemin.get(chemin.size() - 1)) {
                doNotGo.add(arc.depart);
            }
        }
        this.chemin.add(arc.arrivee);
        this.cheminArc.add(arc);
        System.out.println(arc.arrivee.id + "  ||  " + arc.arrivee.proachCommunesArcs.size() );
        return arc.arrivee;
    }

    @Override
    public void run() {
        while (currentCommune != endCommune){
            Commune tmp = nextCommune(currentCommune,endCommune);
            currentCommune = tmp;
        }

        for (Commune c:chemin){
            System.out.print(c.id + "  =>  ");
        }
    }
}
