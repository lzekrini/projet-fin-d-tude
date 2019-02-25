package MVC;
import jbotsim.*;
import jbotsim.Color;
import jbotsim.Point;
import java.awt.*;
import java.util.*;
import java.util.List;


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


    public void modify(Topology topo, Node node) {
        if (node instanceof Ipv4) {
            if (((Ipv4) node).getType().equals("IPV4")) {
                ((Ipv4) node).setType("IPV6");

                node.setIcon("./src/img/ipv6.png");
                System.out.println(((Ipv4) node).getType());
            } else {
                ((Ipv4) node).setType("IPV4");


                node.setIcon("./src/img/ipv4.png");
                System.out.println(((Ipv4) node).getType());
            }
        }


        if (node instanceof Ipv6) {
            if (((Ipv6) node).getType().equals("IPV6")) {
                ((Ipv6) node).setType("IPV4");
                node.setIcon("./src/img/ipv4.png");
                System.out.println(((Ipv6) node).getType());
            } else {
                ((Ipv6) node).setType("IPV6");

                node.setIcon("./src/img/ipv6.png");
                System.out.println(((Ipv6) node).getType());
            }


        }
    }

/////////////////////////////////////////////// Methode pour les composantes connexes ///////////////////////////////////


    public void color ( Topology topo){

        Node root= topo.getNodes().get(0);

        for ( int i=0; i< root.getNeighbors().size();i++){
            Node n= root.getNeighbors().get(i);
            Boolean c= compareType(root,n);
            if ( c==true )
            {
                root.setColor(Color.BLACK);
                n.setColor(Color.BLACK);
            }
            else
            {
                root.setColor(Color.BLACK);
                n.setColor(Color.green);
            }


        }//for
        System.out.println(root.getID());
    }

/////////////////////////////////// try composantes /////////////////////////////////






    public boolean compareType(Node n1, Node n2) {
        boolean compare= false;
        if (n1 instanceof Ipv4 && n2 instanceof Ipv4) {
            if (((Ipv4) n1).getType().equals(((Ipv4) n2).getType()))
                compare= true;
        }//if1
        else if (n1 instanceof Ipv4 && n2 instanceof Ipv6) {
            if (((Ipv4) n1).getType().equals(((Ipv6) n2).getType()))
                compare= true;
        }//if2 ModifyType
        else if (n1 instanceof Ipv6 && n2 instanceof Ipv4) {
            if (((Ipv6) n1).getType().equals(((Ipv4) n2).getType()))
                compare= true;

        }//if3 ModifyType
        else if (n1 instanceof Ipv6 && n2 instanceof Ipv6) {
            if (((Ipv6) n1).getType().equals(((Ipv6) n2).getType()))
                compare= true;
        }//if4

        return compare;
    }

    ArrayList <Node> visitedlist= new ArrayList<>();
    ArrayList<ArrayList> composante = new ArrayList<>();
    ArrayList<Node>compo1= new ArrayList<>();
    Set set = new HashSet();


    public void parcours ( Node root, ArrayList visitedlist, ArrayList compo1 ) {



        if (!visitedlist.contains(root)) {
            visitedlist.add(root);
            compo1.add(root);
        }
        for (int i = 0; i < root.getNeighbors().size(); i++) {
            Node neighbor = root.getNeighbors().get(i);
            if ((compareType(root, neighbor) == true) && (!(visitedlist.contains(neighbor)))) {
                visitedlist.add(neighbor);
                compo1.add(neighbor);
                //root= neighbor;
               // neighbor.setColor(Color.red);
                neighbor.setColor(root.getColor());
                parcours(neighbor, visitedlist, compo1);

                composante.add(compo1);


            }
            else if ((compareType(root, neighbor) == false) && (!(visitedlist.contains(neighbor)))) {
                ArrayList<Node> compo2 = new ArrayList();
                visitedlist.add(neighbor);
                compo2.add(neighbor);
                neighbor.setColor(Color.red);
               // neighbor.setColor(Color.green);
                //root=neighbor;
                parcours(neighbor, visitedlist, compo2);
                composante.add(compo2);



            }
        }
    }


/////////////////////////////////////////// Connected Links ////////////////////////////////////////////////////////////


    ArrayList<Link> connectedLinks= new ArrayList<>();

    public ArrayList<Link> connectedL ( Topology tp){
        final List<Link> links = tp.getLinks();


        for ( int i=0; i<links.size();i++){

            Link l= links.get(i);
            Node n1= l.endpoint(0);
            Node n2= l.endpoint(1);
            if ( n1 instanceof Ipv4 && n2 instanceof Ipv4 && (!((Ipv4) n1).getType().equals(((Ipv4) n2).getType()))) {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
            else if ( n1 instanceof Ipv4 && n2 instanceof Ipv6 && (!((Ipv4) n1).getType().equals(((Ipv6) n2).getType()))) {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
            else if (n1 instanceof Ipv6 && n2 instanceof  Ipv6 && (!((Ipv6) n1).getType().equals(((Ipv6) n2).getType()))) {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
            else if ( n1 instanceof Ipv6 && n2 instanceof Ipv4 && (!((Ipv6) n1).getType().equals(((Ipv4) n2).getType()))) {

                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
        }
        return  connectedLinks;
    }

///////////////////////////////////// Convertisseur /////////////////////////////////////////////////////////////////

        ArrayList<Node> listConvert= new ArrayList<>();

      public void Convert (ArrayList<Link> connectedLinks,Topology tp){

          for ( int i=0; i< connectedLinks.size(); i++) {
              Link l = connectedLinks.get(i);
              Node n1 = l.endpoint(0);
              listConvert.add(n1);
              Node n2 = l.endpoint(1);
              listConvert.add(n2);
                System.out.println(n1.getNeighbors().size());
                  if (n1 instanceof Ipv6 && n2 instanceof Ipv6) {
                      ((Ipv6) n1).setType("Conv");
                      ((Ipv6) n2).setType("Conv");
                      ((Ipv6) n1).setIcon("./src/img/Conv.png");
                      ((Ipv6) n2).setIcon("./src/img/Conv.png");
                  }
                  else if (n1 instanceof Ipv4 && n2 instanceof Ipv6)  {
                          ((Ipv4) n1).setType("Conv");
                           ((Ipv6) n2).setType("Conv");
                          ((Ipv4) n1).setIcon("./src/img/Conv.png");
                          ((Ipv6) n2).setIcon("./src/img/Conv.png");

                  }
                   else if (n1 instanceof Ipv4 && n2 instanceof Ipv4) {
                      ((Ipv4) n1).setType("Conv");
                     ((Ipv4) n2).setType("Conv");
                      ((Ipv4) n1).setIcon("./src/img/Conv.png");
                      ((Ipv4) n2).setIcon("./src/img/Conv.png");
                  }
                  else if (n1 instanceof Ipv6 && n2 instanceof Ipv4) {
                          ((Ipv6) n1).setType("Conv");
                         ((Ipv4) n2).setType("Conv");
                         ((Ipv6) n1).setIcon("./src/img/Conv.png");
                         ((Ipv4) n2).setIcon("./src/img/Conv.png");
                  }

              }
          }

//////////////////////////////////////// Minimisation Convertion ///////////////////////////////////////////////////////





  /*  public void ConvertMinimiz( Topology tp,ArrayList<Node>listConvert){
        Boolean allConvert= true;

        for ( int i=0; i<listConvert.size(); i++){

            Node n= listConvert.get(i);
            List<Node> neigbors= n.getNeighbors();
            for ( int j=0; j< neigbors.size(); j++){
               Node neighbor= neigbors.get
            }

        }
    }*/

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void prof (Topology tp) {
        Node root = tp.getNodes().get(0);
        root.setColor(Color.green);


        parcours(root, visitedlist, compo1);
        set.addAll(composante) ;
        ArrayList distinctList = new ArrayList(set) ;
        System.out.println(distinctList.size());

        for ( int i=0; i< distinctList.size();i++)
            System.out.println(distinctList.get(i));

            ArrayList<Link>liste= connectedL(tp);
        for ( int i=0; i< liste.size();i++)
            System.out.println(liste.get(i));

        Convert(liste,tp);

    }






    }//endClass












