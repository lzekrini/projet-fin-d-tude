package MVC;
import jbotsim.*;
import jbotsim.Color;

import java.awt.*;
import java.util.*;


public class Model {
    int x = 0;
    int index[] = {0,0};

/////////////////////////////Methode pour ajout des liens///////////////:

    public void counter(Topology tp,Node node) {
        index[x] = node.getID();
        x++;
        if (x > 1) {
           addlink(tp);
        }
    }
    public void addlink(Topology tp) {

        Link l = new Link(tp.getNodes().get(index[0]), tp.getNodes().get(index[1]));
        l.setWidth(2);
        l.setColor(Color.GREEN);
        tp.addLink(l);
        x = 0;

    }


    /////////////////////////////Methode pour supprimer des liens///////////////:


    public void countdown(Topology tp,Node node) {
        index[x] = node.getID();
        x++;
        if (x > 1) {
            rmlink(tp);

        }
    }
    public void rmlink(Topology tp) {


        Link l = new Link(tp.getNodes().get(index[0]), tp.getNodes().get(index[1]));
        tp.removeLink(l);
        x = 0;
    }

}
