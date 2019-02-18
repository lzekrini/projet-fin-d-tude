package MVC;
import jbotsim.*;

import java.awt.geom.Point2D;
import java.util.*;


public class Model {
    int i = 0;
    int tab[] = new int[2];


/////////////////////////////Methode pour ajout des liens///////////////:

    public void addlink(Topology tp, Node node) {

        tab[i] = node.getID();
        i++;

        if (i > 1) {
            Link l = new Link(tp.getNodes().get(tab[0]), tp.getNodes().get(tab[1]));
            l.setWidth(4);
            tp.addLink(l);
            i = 0;

        }
    }
    ///////////////////////////////////Methode pour supprimer les liens ///////////////////



}
