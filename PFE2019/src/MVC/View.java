package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

import java.awt.event.ActionListener;

import jbotsim.*;
import jbotsimx.ui.*;
public class View {

    private JFrame frame;
    public JButton b1 =new JButton("File");
    public JButton b2 =new JButton("Help");
    public JButton b3 =new JButton("Edit");
    public JLabel b11 = new JLabel("this application ");
    public JLabel labelipv4 = new JLabel("");
    public JLabel labelipv6 = new JLabel("");
    public JLabel addlink = new JLabel("Add links ");
    public JLabel  Remove = new JLabel("Remove Links");
    public JLabel Rchange=new JLabel("   Modify");
    public JLabel   Run = new JLabel("     Run");

    public JLabel reset = new JLabel("Reset");
    public JLabel  Menu = new JLabel("Create your topology");
    public JRadioButton  ipv4 = new JRadioButton();
    public JRadioButton  ipv6 = new JRadioButton();
    public GridBagConstraints G = new GridBagConstraints();
    public GridBagConstraints G1 = new GridBagConstraints();
    public ImageIcon imgipv4,imgipv6,imgaddlink,imgclear,imgrun,imgremove,imgrouteur,imgreset;
    public JButton Baddlink, Bclear,Brun,Bremove,Reset,Chrouteur,mrouteur;
    public Topology tp;
    public JTopology jtp;
    public ButtonGroup bg;





    public View()

    {
        frame = new JFrame("Placement d'un convertisseur Ipv4-Ipv6");
        frame.setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        G.fill = GridBagConstraints.BOTH;
        G.insets = new Insets(5, 5, 5, 5);

        G1.fill = GridBagConstraints.BOTH;
        G1.insets = new Insets(5, 5, 5, 5);


        imgipv4 = new ImageIcon(new ImageIcon("./src/img/ip4.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgipv6 = new ImageIcon(new ImageIcon("./src/img/ipv6.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgaddlink = new ImageIcon(new ImageIcon("./src/img/link_add.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgrun = new ImageIcon(new ImageIcon("./src/img/run.jpeg").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgremove = new ImageIcon(new ImageIcon("./src/img/remove_link.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgrouteur=new ImageIcon((new ImageIcon("./src/img/64.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
        imgreset=new ImageIcon((new ImageIcon("./src/img/reset.jpeg").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
        /////////////////////////////////premier panel/////////////////////////////////////////////////////////
        JPanel menuaide=new JPanel();
        menuaide.setBackground(Color.LIGHT_GRAY);
        menuaide.setLayout(new FlowLayout());
        Style(b1);
        Style(b2);
        Style(b3);
        menuaide.add(b1);
        menuaide.add(b2);
        menuaide.add(b3);
/////////////////////////////////////////Panel menu////////////////////////////////////////////////////////
        JPanel panelmenu = new JPanel();
        panelmenu.setBackground(Color.CYAN);
        panelmenu.setLayout(new GridBagLayout());

        JPanel menu =new JPanel();
        menu.setBackground(Color.CYAN);
        menu.add(new JLabel("Create your topology"));

        G.gridwidth = 2;
        G.gridx=0;
        G.gridy=0;
        panelmenu.add(menu,G);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        labelipv4.setIcon(imgipv4);
        labelipv4.setPreferredSize(new Dimension(60,60));
        labelipv6.setIcon(imgipv6);
        labelipv6.setPreferredSize(new Dimension(60,60));
        JPanel Nmenu = new JPanel();
        Nmenu.setBackground(Color.CYAN);
        Nmenu.setLayout(new FlowLayout());
        JPanel P1 = new JPanel();
       P1.setLayout(new GridLayout(2,1));
        P1.setBackground(Color.CYAN);
        P1.add(new JLabel("    Choose a router :    "));

        JPanel P11 = new JPanel();
        P11.setBackground(Color.CYAN);
        ipv4.setBackground(Color.CYAN);
        P11.add(ipv4);
        P11.add(labelipv4);
        P11.add(ipv6);
        ipv6.setBackground(Color.CYAN);
        P11.add(labelipv6);
        P1.add(P11);
        Nmenu.add(P1);

        G.gridwidth = 2;
        G.gridx=0;
        G.gridy=1;
        panelmenu.add(Nmenu,G);

        JPanel P2 = new JPanel();
        P2.setLayout(new GridLayout(2,1));
        P2.setBackground(Color.CYAN);
        P2.add(addlink);
        Baddlink = new JButton(imgaddlink);
        Style(Baddlink);
        Baddlink.setPreferredSize(new Dimension(70,70));
        P2.add(Baddlink);
        Nmenu.add(P2);


        JPanel P3 = new JPanel();
        P3.setLayout(new GridLayout(2,1));
        P3.setBackground(Color.CYAN);
        P3.add(Remove);
        Bremove =  new JButton(imgremove);
        Style(Bremove);
        Bremove.setPreferredSize(new Dimension(70,70));
        P3.add(Bremove);
        Nmenu.add(P3);


        JPanel P4 =new JPanel(new GridLayout(2,1));
        P4.setBackground(Color.CYAN);
        P4.add(Rchange);
        mrouteur =  new JButton(imgrouteur);
        Style(mrouteur);
        mrouteur.setPreferredSize(new Dimension(70,70));
        P4.add(mrouteur);
        Nmenu.add(P4);



        JPanel P5 = new JPanel();
        P5.setLayout(new GridLayout(2,1));
        P5.setBackground(Color.CYAN);
        P5.add(Run);
        Brun =  new JButton(imgrun);
        Style(Brun);
        Brun.setPreferredSize(new Dimension(70,70));
        P5.add(Brun);
        Nmenu.add(P5);

        ///////////////////////////////////////////////////Suite de menu////////////////////////////////////////////////////////////::
       // JPanel PanelSuite = new JPanel();
       // PanelSuite.setLayout(new FlowLayout());
       // JPanel p5 =new JPanel(new GridLayout(2,1));
       // p5.setBackground(Color.CYAN);
        //p5.add(reset);
        //Reset =  new JButton(imgreset);
        //Style(Reset);
        //Reset.setPreferredSize(new Dimension(70,70));
       // p5.add(Reset);
        //PanelSuite.add(p5);

/////////////////////////////////////////////////////////////////////////////////////////////////////////

// créer la topologie jbotsim/////////////////////////////////////////////////////////////


        tp = new Topology();
        tp.setDimensions(600,500);
        jtp = new JTopology(tp);
        jtp.setSize(500,400);

        tp.setLinkResolver(new LinkResolver() {
            public boolean isHeardBy(Node n1, Node n2) {
                return false;
            }
        });


        JPanel  Topo = new JPanel();
        Topo.setBackground(Color.WHITE);

        JPanel paneltp = new JPanel();
        paneltp.setBackground(Color.white);

        bg = new ButtonGroup();
        bg.add(ipv4);
        bg.add(ipv6);
        ipv4.setSelected(true);


        paneltp.add(jtp);
        Topo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        paneltp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        Topo.add(paneltp);

        jtp.addCommand("Clear");

        frame.add(Topo, BorderLayout.SOUTH);
        frame.add(panelmenu,BorderLayout.NORTH);
       // frame.add(PanelSuite, BorderLayout.CENTER);
       // frame.add(menuaide,BorderLayout.NORTH);



        frame.pack();
        frame.setVisible(true);


    }

    public void Style(JButton button) {

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    }



