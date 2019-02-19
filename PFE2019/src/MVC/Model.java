package MVC;
import jbotsim.*;
import jbotsim.Color;
import jbotsim.Point;
import java.awt.*;
import java.util.*;


public class Model {
    int x = 0;
    int index[] = {0, 0};

/////////////////////////////Methode pour ajout des liens///////////////:

    public void counter(Topology tp, Node node) {
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


        public void countdown(Topology tp, Node node) {
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

        public void Chrouteur(Topology tp, Node node) {


        }

        //////////////////////////////////Methode pour changer le routeur//////////////:
        public void modify(Topology topo, Node node) {
            if (node instanceof Ipv4) {
                Point p= node.getLocation();
                Ipv6 ip6 = new Ipv6();
                ip6.setLocation(p);
                ip6.setID(node.getID());
                topo.addNode(ip6);
                for (Node n : node.getNeighbors()) {
                    Link l = new Link(ip6, n);
                    l.setWidth(4);
                    topo.addLink(l);
                }
                for (Node n : node.getNeighbors()) {
                    topo.removeLink(topo.getLink(node, n));
                }
                topo.removeNode(node);
            } else if (node instanceof Ipv6) {
                Point p= node.getLocation();
                Ipv4 ip4 = new Ipv4();
                ip4.setLocation(p);
                ip4.setID(node.getID());
                topo.addNode(ip4);
                for (Node n : node.getNeighbors()) {
                    Link l = new Link(ip4, n);
                    l.setWidth(4);
                    topo.addLink(l);
                }
                for (Node n : node.getNeighbors()) {
                    topo.removeLink(topo.getLink(node,n));
                }
                topo.removeNode(node);
            }
        }
    }










