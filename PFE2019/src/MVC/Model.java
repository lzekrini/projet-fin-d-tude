package MVC;
import jbotsim.*;
import jbotsim.Color;
import java.util.*;
import java.util.List;

public class Model
{
    int x = 0;
    int index[] = {0, 0};

//////////////////////////////////// Add links method /////////////////////////////////////
    public void counter(Topology tp, Node node)
    {
        index[x] = node.getID();
        x++;
        if (x > 1)
        {
            addlink(tp);
        }
    }

    public void addlink(Topology tp)
    {
        Link l = new Link(tp.getNodes().get(index[0]), tp.getNodes().get(index[1]));
        l.setWidth(4);
        l.setColor(Color.BLACK);
        tp.addLink(l);
        x = 0;
    }

///////////////////////////////////// Remove links method ///////////////////////////////
    public void countdown(Topology tp, Node node)
    {
        index[x] = node.getID();
        x++;
        if (x > 1)
        {
            rmlink(tp);
        }
    }

    public void rmlink(Topology tp)
    {
        Link l = new Link(tp.getNodes().get(index[0]), tp.getNodes().get(index[1]));
        tp.removeLink(l);
        x = 0;
    }

//////////////////////////////////// Modify switch method ///////////////////////////////
    public void modify(Topology topo, Node node)
    {
        if (node instanceof Ipv4)
        {
            if (((Ipv4) node).getType().equals("IPV4"))
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
        if (node instanceof Ipv6)
        {
            if (((Ipv6) node).getType().equals("IPV6"))
            {
                ((Ipv6) node).setType("IPV4");
                node.setIcon("./src/img/ipv4.png");
                System.out.println(((Ipv6) node).getType());
            } else
            {
                ((Ipv6) node).setType("IPV6");
                node.setIcon("./src/img/ipv6.png");
                System.out.println(((Ipv6) node).getType());
            }
        }
    }

///////////////////////////////////// Create components /////////////////////////////////////
    public boolean compareType(Node n1, Node n2)
    {
        boolean compare= false;
        if (n1 instanceof Ipv4 && n2 instanceof Ipv4)
        {
            if (((Ipv4) n1).getType().equals(((Ipv4) n2).getType()))
                compare= true;
        }
        else if (n1 instanceof Ipv4 && n2 instanceof Ipv6)
        {
            if (((Ipv4) n1).getType().equals(((Ipv6) n2).getType()))
                compare= true;
        }
        else if (n1 instanceof Ipv6 && n2 instanceof Ipv4)
        {
            if (((Ipv6) n1).getType().equals(((Ipv4) n2).getType()))
                compare= true;
        }
        else if (n1 instanceof Ipv6 && n2 instanceof Ipv6)
        {
            if (((Ipv6) n1).getType().equals(((Ipv6) n2).getType()))
                compare= true;
        }
        return compare;
    }

    ArrayList <Node> visitedlist= new ArrayList<>();
    ArrayList<ArrayList> composante = new ArrayList<>();
    ArrayList<Node>compo1= new ArrayList<>();
    Set set = new HashSet();

    public void parcours ( Node root, ArrayList visitedlist, ArrayList compo1 )
    {
        if (!visitedlist.contains(root))
        {
            visitedlist.add(root);
            compo1.add(root);
        }
        for (int i = 0; i < root.getNeighbors().size(); i++)
        {
            Node neighbor = root.getNeighbors().get(i);
            if ((compareType(root, neighbor) == true) && (!(visitedlist.contains(neighbor))))
            {
                visitedlist.add(neighbor);
                compo1.add(neighbor);
                //neighbor.setColor(root.getColor());
                parcours(neighbor, visitedlist, compo1);
                composante.add(compo1);
            }
            else if ((compareType(root, neighbor) == false) && (!(visitedlist.contains(neighbor))))
            {
                ArrayList<Node> compo2 = new ArrayList();
                visitedlist.add(neighbor);
                compo2.add(neighbor);
               // neighbor.setColor(Color.red);
                parcours(neighbor, visitedlist, compo2);
                composante.add(compo2);
                composante.add(compo1);
            }
        }
    }

/////////////////////////////////////////// Create connected Links /////////////////////////////////////////
    ArrayList<Link> connectedLinks= new ArrayList<>();

    public ArrayList<Link> connectedL ( Topology tp )
    {
        final List<Link> links = tp.getLinks();
        for ( int i=0; i<links.size();i++)
        {
            Link l= links.get(i);
            Node n1= l.endpoint(0);
            Node n2= l.endpoint(1);
            if ( n1 instanceof Ipv4 && n2 instanceof Ipv4 && (!((Ipv4) n1).getType().equals(((Ipv4) n2).getType())))
            {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
            else if ( n1 instanceof Ipv4 && n2 instanceof Ipv6 && (!((Ipv4) n1).getType().equals(((Ipv6) n2).getType())))
            {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
            else if (n1 instanceof Ipv6 && n2 instanceof  Ipv6 && (!((Ipv6) n1).getType().equals(((Ipv6) n2).getType())))
            {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
            else if ( n1 instanceof Ipv6 && n2 instanceof Ipv4 && (!((Ipv6) n1).getType().equals(((Ipv4) n2).getType())))
            {
                connectedLinks.add(l);
                l.setColor(Color.orange);
            }
        }
        return  connectedLinks;
    }

///////////////////////////////////// Conversion /////////////////////////////////////////////////////////////////
    ArrayList<Node> listConvert= new ArrayList<>();

    public void Convert (ArrayList<Link> connectedLinks)
    {
        for ( int i=0; i< connectedLinks.size(); i++)
        {
            Link l = connectedLinks.get(i);
            Node n1 = l.endpoint(0);
            listConvert.add(n1);
            Node n2 = l.endpoint(1);
            listConvert.add(n2);
            if (n1 instanceof Ipv6 && n2 instanceof Ipv6)
            {
                ((Ipv6) n1).setType("Conv");
                ((Ipv6) n2).setType("Conv");
                ((Ipv6) n1).setIcon("./src/img/Conv.png");
                ((Ipv6) n2).setIcon("./src/img/Conv.png");
            }
            else if (n1 instanceof Ipv4 && n2 instanceof Ipv6)
            {
                ((Ipv4) n1).setType("Conv");
                ((Ipv6) n2).setType("Conv");
                ((Ipv4) n1).setIcon("./src/img/Conv.png");
                ((Ipv6) n2).setIcon("./src/img/Conv.png");

            }
            else if (n1 instanceof Ipv4 && n2 instanceof Ipv4)
            {
                ((Ipv4) n1).setType("Conv");
                ((Ipv4) n2).setType("Conv");
                ((Ipv4) n1).setIcon("./src/img/Conv.png");
                ((Ipv4) n2).setIcon("./src/img/Conv.png");
            }
            else if (n1 instanceof Ipv6 && n2 instanceof Ipv4)
            {
                ((Ipv6) n1).setType("Conv");
                ((Ipv4) n2).setType("Conv");
                ((Ipv6) n1).setIcon("./src/img/Conv.png");
                ((Ipv4) n2).setIcon("./src/img/Conv.png");
            }

        }
    }

//////////////////////////////////////// Conversion Minimization 1 ///////////////////////////////////////////////
    public void ConvertMinimiz( ArrayList<Node>listConvert)
    {
        for ( int i=0; i<listConvert.size(); i++)
        {
            Boolean allConvert= true;
            Node n= listConvert.get(i);
            List<Node> neigbors= n.getNeighbors();
            for ( int j=0; j< neigbors.size(); j++)
            {
                Node neigbor = neigbors.get(j);
                if ( neigbor instanceof Ipv6 && (!((Ipv6) neigbor).getType().equals("Conv")))
                {
                    allConvert=false;
                }
                else if ( neigbor instanceof Ipv4 && (!((Ipv4) neigbor).getType().equals("Conv")))
                {
                    allConvert= false;
                }
             }

            if ( allConvert==true)
            {
                if ( n instanceof Ipv6)
                {
                    ((Ipv6) n).setType("IPV6");
                    n.setIcon("./src/img/ipv6.png");
                    listConvert.remove(n);
                }
                else
                {
                    ((Ipv4) n).setType("IPV4");
                    n.setIcon("./src/img/ipv4.png");
                    listConvert.remove(n);
                }
            }
        }
    }

///////////////////////////////// Conversion Minimization 2: Connected components ////////////////////////////////
    public void minimisationConvCompo ( ArrayList<ArrayList>conv )
    {
        for (int i=0; i<conv.size(); i++)
        {
            ArrayList<Node>liste= conv.get(i);
            for ( int j=0; j<liste.size();j++)
            {
                Node n= liste.get(j);
                if (( n instanceof  Ipv6 &&( ((Ipv6) n).getType().equals("Conv")))||( n instanceof  Ipv4 &&( ((Ipv4) n).getType().equals("Conv"))))
                {
                    for ( int k=j+1; k<liste.size(); k++)
                    {
                        Node n1= liste.get(k);
                        if ( n1 instanceof Ipv6 && ((Ipv6) n1).getType().equals("Conv"))
                        {
                            ((Ipv6) n1).setType("IPV6");
                            n1.setIcon("./src/img/ipv6.png");
                            listConvert.remove(n1);
                        }
                        else if ( n1 instanceof Ipv4 && ((Ipv4) n1).getType().equals("Conv"))
                        {
                            ((Ipv4) n1).setType("IPV4");
                            n1.setIcon("./src/img/ipv4.png");
                            listConvert.remove(n1);
                        }
                    }
                }
            }
        }
    }

////////////////////// Conversion Minimization 3: The neighbor of all nodes  //////////////////////////////////////
    public void minimisationAllNeighbor( ArrayList<Node>convrt, Topology tp)
    {
        for ( int i=0; i<convrt.size(); i++ )
        {
            Node node= convrt.get(i);
            if ( node.getNeighbors().size()== tp.getNodes().size()-1 )
            {
                List<Node> list= node.getNeighbors();
                for ( int j=0; j<list.size(); j++)
                {
                    Node n= list.get(j);
                    if ( n instanceof Ipv6 && convrt.contains(n) )
                    {
                        ((Ipv6) n).setType("IPV6");
                        n.setIcon("./src/img/ipv6.png");
                        convrt.remove(n);
                    }
                    else if ( n instanceof Ipv4 && convrt.contains(n) )
                    {
                        ((Ipv4) n).setType("IPV4");
                        n.setIcon("./src/img/ipv4.png");
                        convrt.remove(n);
                    }
                }
            }
        }
    }

/////////////////////////////////////////// Conversion Minimization 4 /////////////////////////////////////////////
    public void minimisation4( ArrayList<Node>convrt1, Topology tp)
    {
       for ( int i=0; i<tp.getNodes().size(); i++ )
       {
           Node n= tp.getNodes().get(i);
           List<Link>allLinks = n.getLinks();
           boolean allLinksConnected= true;
           for ( int j=0; j<allLinks.size();j++ )
           {
               Link l= allLinks.get(j);
               if ( l.getColor()!=Color.orange)
                   allLinksConnected=false;
           }
           List<Node> neighbors= n.getNeighbors();
           boolean allNeighborsConvert=true;
           for ( int j=0; j<neighbors.size();j++ )
           {
               Node n1= neighbors.get(j);
               if ( (convrt1.contains(n1))== false )
               {
                   allNeighborsConvert=false;
               }
           }
           if ( allLinksConnected==true && allNeighborsConvert==true )
           {
               if ( n instanceof Ipv6)
               {
                   ((Ipv6) n).setType("Conv");
                   n.setIcon("./src/img/Conv.png");
                   convrt1.add(n);
               }
               else if ( n instanceof Ipv4)
               {
                   ((Ipv4) n).setType("Conv");
                   n.setIcon("./src/img/Conv.png");
                   convrt1.add(n);
               }

               for ( int m=0; m< n.getNeighbors().size(); m++ )
               {
                   Node n2= n.getNeighbors().get(m);
                   if ( n2 instanceof Ipv6)
                   {
                       ((Ipv6) n2).setType("IPV6");
                       n2.setIcon("./src/img/ipv6.png");
                       convrt1.remove(n2);
                   }
                   else if ( n2 instanceof Ipv4)
                   {
                       ((Ipv4) n2).setType("IPV4");
                       n2.setIcon("./src/img/ipv4.png");
                       convrt1.remove(n2);
                   }
               }
           }
       }
    }

//////////////////////////////////////////// Conversion Test ///////////////////////////////////////////////
    public void testConvertTopo (Topology tp)
    {
        Node root = tp.getNodes().get(0);
        //root.setColor(Color.green);
        parcours(root, visitedlist, compo1);
        set.addAll(composante);
        ArrayList distinctList = new ArrayList(set);
        System.out.println(distinctList.size());
        for ( int i = 0; i < distinctList.size(); i++ )
            System.out.println(distinctList.get(i));
        ArrayList<Link> liste = connectedL(tp);
        /*for (int i = 0; i < liste.size(); i++)
            System.out.println(liste.get(i));*/
        Convert(liste);
        /*for ( int i = 0; i < listConvert.size(); i++ )
            System.out.println(listConvert.get(i));*/
        ConvertMinimiz(listConvert);
        minimisationConvCompo(distinctList);
        minimisationAllNeighbor(listConvert,tp);
       //minimisation4(listConvert,tp);
    }
}












