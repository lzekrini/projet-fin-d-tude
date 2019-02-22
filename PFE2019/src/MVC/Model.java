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
        l.setWidth(4);
        l.setColor(Color.BLACK);
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



       public void modify (Topology topo, Node node )
       {
           if ( node instanceof Ipv4) {
               if ( ((Ipv4) node).getType().equals("IPV4"))
               {
               ((Ipv4) node).setType("IPV6");

               node.setIcon("./src/img/ipv6.png");
               System.out.println(((Ipv4) node).getType());
               }
               else
               {
                   ((Ipv4) node).setType("IPV4");

                   node.setIcon("./src/img/ipv4.png");
                   System.out.println(((Ipv4) node).getType());
               }
           }


           if ( node instanceof Ipv6)
           { if ( ((Ipv6) node).getType().equals("IPV6"))
           {
               ((Ipv6) node).setType("IPV4");
               node.setIcon("./src/img/ipv4.png");
               System.out.println(((Ipv6) node).getType());
           }
           else
           {
               ((Ipv6) node).setType("IPV6");

               node.setIcon("./src/img/ipv6.png");
               System.out.println(((Ipv6) node).getType());}


       }
}}

















