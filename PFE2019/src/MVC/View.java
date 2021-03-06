package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import jbotsim.*;
import jbotsimx.ui.*;
public class View {

    private JFrame frame;
    public JButton b1 =new JButton("Read me");
    public JLabel labelipv4 = new JLabel("");
    public JLabel labelipv6 = new JLabel("");
    public JLabel addlink = new JLabel("Add links ");
    public JLabel  Remove = new JLabel("Remove Links");
    public JLabel Rchange=new JLabel("   Modify");
    public JLabel   Run = new JLabel("     Run");
    public JRadioButton  ipv4 = new JRadioButton();
    public JRadioButton  ipv6 = new JRadioButton();
    public GridBagConstraints G = new GridBagConstraints();
    public GridBagConstraints G1 = new GridBagConstraints();
    public ImageIcon imgipv4,imgipv6,imgaddlink,imgrun,imgremove,imgrouteur;
    public JButton Baddlink,Brun,Bremove,mrouteur;
    public Topology tp;
    public JTopology jtp;
    public ButtonGroup bg;





    public View()

    {
        frame = new JFrame("Placement d'un convertisseur Ipv4-Ipv6");
        frame.setBackground(Color.WHITE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        G.fill = GridBagConstraints.BOTH;
        G.insets = new Insets(5, 5, 5, 5);

        G1.fill = GridBagConstraints.BOTH;
        G1.insets = new Insets(5, 5, 5, 5);


        imgipv4 = new ImageIcon(new ImageIcon("./src/img/ipv4.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        imgipv6 = new ImageIcon(new ImageIcon("./src/img/ipv6.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        imgaddlink = new ImageIcon(new ImageIcon("./src/img/link_add.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgrun = new ImageIcon(new ImageIcon("./src/img/run.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgremove = new ImageIcon(new ImageIcon("./src/img/remove_link.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        imgrouteur=new ImageIcon((new ImageIcon("./src/img/64.png").getImage().getScaledInstance(68, 68, Image.SCALE_DEFAULT)));

/////////////////////////////////////////first panel/////////////////////////////////////////////////////////
        JPanel menuaide=new JPanel();
        menuaide.setBackground(new Color(177,218,237));
        menuaide.setLayout(new FlowLayout());
        Style(b1);
        menuaide.add(new JLabel("                                                                                                                                                                                                                             "));
        menuaide.add(b1);

/////////////////////////////////////////Panel menu////////////////////////////////////////////////////////
        JPanel panelmenu = new JPanel();
        panelmenu.setBackground(new Color(177,218,237));
        panelmenu.setLayout(new GridBagLayout());
        JPanel menu =new JPanel();
        menu.setBackground(new Color(177,218,237));
        menu.setLayout(new FlowLayout());
        JLabel b= new JLabel("Create your topology");
        b.setFont(new Font("Serif",Font.PLAIN,20));
        menu.add(b);
        G.gridwidth = 2;
        G.gridx=0;
        G.gridy=0;
        panelmenu.add(menu,G);

//////////////////////////////////////////Panel tools//////////////////////////////////////////////////////
        labelipv4.setIcon(imgipv4);
        labelipv4.setPreferredSize(new Dimension(60,60));
        labelipv6.setIcon(imgipv6);
        labelipv6.setPreferredSize(new Dimension(60,60));
        JPanel Nmenu = new JPanel();
        Nmenu.setBackground(new Color(177,218,237));
        Nmenu.setLayout(new FlowLayout());
        JPanel P1 = new JPanel();
        P1.setLayout(new GridLayout(2,1));
        P1.setBackground(new Color(177,218,237));
        P1.add(new JLabel("    Choose a router     "));

        JPanel P11 = new JPanel();
        P11.setBackground(new Color(177,218,237));
        ipv4.setBackground(new Color(177,218,237));
        P11.add(ipv4);
        P11.add(labelipv4);
        P11.add(ipv6);
        ipv6.setBackground(new Color(177,218,237));
        P11.add(labelipv6);
        P1.add(P11);
        Nmenu.add(P1);

        G.gridwidth = 2;
        G.gridx=0;
        G.gridy=1;
        panelmenu.add(Nmenu,G);


        JPanel P2 = new JPanel();
        P2.setLayout(new GridLayout(2,1));
        P2.setBackground(new Color(177,218,237));
        P2.add(addlink);
        Baddlink = new JButton(imgaddlink);
        Style(Baddlink);
        Baddlink.setPreferredSize(new Dimension(70,70));
        P2.add(Baddlink);
        Nmenu.add(P2);


        JPanel P3 = new JPanel();
        P3.setLayout(new GridLayout(2,1));
        P3.setBackground(new Color(177,218,237));
        P3.add(Remove);
        Bremove =  new JButton(imgremove);
        Style(Bremove);
        Bremove.setPreferredSize(new Dimension(70,70));
        P3.add(Bremove);
        Nmenu.add(P3);


        JPanel P4 =new JPanel(new GridLayout(2,1));
        P4.setBackground(new Color(177,218,237));
        P4.add(Rchange);
        mrouteur =  new JButton(imgrouteur);
        Style(mrouteur);
        mrouteur.setPreferredSize(new Dimension(70,70));
        P4.add(mrouteur);
        Nmenu.add(P4);


        JPanel P5 = new JPanel();
        P5.setLayout(new GridLayout(2,1));
        P5.setBackground(new Color(177,218,237));
        P5.add(Run);
        Brun =  new JButton(imgrun);
        Style(Brun);
        Brun.setPreferredSize(new Dimension(70,70));
        P5.add(Brun);
        Nmenu.add(P5);


////////////////////////////////////////////////Jbotssim Topology///////////////////////////////////////////////////////
        tp = new Topology();
        tp.setDimensions(1000,500);
        jtp = new JTopology(tp);
        jtp.setSize(1000,500);

        tp.setLinkResolver(new LinkResolver() {
            public boolean isHeardBy(Node n1, Node n2)
            {
                return false;
            }
        });

        JPanel  Topo = new JPanel();
        Topo.setBackground(new Color(177,218,237));
        JPanel paneltp = new JPanel();
        paneltp.setBackground(new Color(177,218,237));
        bg = new ButtonGroup();
        bg.add(ipv4);
        bg.add(ipv6);
        ipv4.setSelected(true);
        paneltp.add(jtp);
        Topo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(177,218,237)));
        paneltp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(177,218,237)));
        Topo.add(paneltp);
        jtp.addCommand("Clear");
        frame.add(Topo, BorderLayout.SOUTH);
        frame.add(panelmenu,BorderLayout.CENTER);
        frame.add(menuaide,BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }

    public void Style(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

}

