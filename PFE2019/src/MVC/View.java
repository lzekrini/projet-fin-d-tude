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

    public JLabel labelipv4 = new JLabel("");
   // public JButton b1,b2,b3;
    public JLabel labelipv6 = new JLabel("");
    public JLabel addlink = new JLabel("Ajouter Lien  ");
    public JLabel removelink = new JLabel("Supprimer Lien");
    public JLabel  clear = new JLabel("Rénitialisation");
    public JLabel   Run = new JLabel("Exécuter");
    public JLabel  update = new JLabel("Modifier Routeur");
    public JLabel  Menu = new JLabel("Menu");
    public JRadioButton  ipv4 = new JRadioButton();
    public JRadioButton  ipv6 = new JRadioButton();
    public GridBagConstraints G = new GridBagConstraints();
    public GridBagConstraints G1 = new GridBagConstraints();
    public ImageIcon imgipv4,imgipv6,imgaddlink,imgclear,imgrun;
    public JButton Baddlink, Bclear,Brun;
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


        imgipv4 = new ImageIcon(new ImageIcon("/home/dida/Desktop/img/ipv6.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgipv6 = new ImageIcon(new ImageIcon("/home/dida/Desktop/img/ip4.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgaddlink = new ImageIcon(new ImageIcon("/home/dida/Desktop/img/link_add.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgclear = new ImageIcon(new ImageIcon("/home/dida/Desktop/img/reset.jpeg").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        imgrun = new ImageIcon(new ImageIcon("/home/dida/Desktop/img/run.jpeg").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));

        JPanel menuaide=new JPanel();
        menuaide.setLayout(new FlowLayout());
        stylebutton(b1);
        stylebutton(b2);
        stylebutton(b3);
        menuaide.add(b1);
        menuaide.add(b2);
        menuaide.add(b3);

        JPanel panelmenu = new JPanel();
        panelmenu.setBackground(Color.LIGHT_GRAY);
        panelmenu.setLayout(new GridBagLayout());

        JPanel menu =new JPanel();
        JLabel labelmenu =new JLabel("");
        menu.add(new JLabel("Menu"));

        G.gridwidth = 2;
        G.gridx=0;
        G.gridy=0;
        panelmenu.add(menu,G);



        labelipv4.setIcon(imgipv4);
        labelipv4.setPreferredSize(new Dimension(60,60));
        labelipv6.setIcon(imgipv6);
        labelipv6.setPreferredSize(new Dimension(60,60));


        JPanel Nmenu = new JPanel();
        Nmenu.setBackground(Color.LIGHT_GRAY);
        Nmenu.setLayout(new FlowLayout());


        JPanel P1 = new JPanel();
       P1.setLayout(new GridLayout(2,1));
        P1.setBackground(Color.LIGHT_GRAY);
        P1.add(new JLabel("Router Type :    "));

        JPanel P11 = new JPanel();
        P11.setBackground(Color.LIGHT_GRAY);
        ipv4.setBackground(Color.LIGHT_GRAY);
        P11.add(ipv4);
        P11.add(labelipv4);
        P11.add(ipv6);
        ipv6.setBackground(Color.LIGHT_GRAY);
        P11.add(labelipv6);
        P1.add(P11);
        Nmenu.add(P1);

        G.gridwidth = 2;
        G.gridx=0;
        G.gridy=1;
        panelmenu.add(Nmenu,G);





        JPanel P2 = new JPanel();
        P2.setLayout(new GridLayout(2,1));
        P2.setBackground(Color.LIGHT_GRAY);
        P2.add(addlink);
        Baddlink = new JButton(imgaddlink);
        stylebutton(Baddlink);
        Baddlink.setPreferredSize(new Dimension(70,70));
        P2.add(Baddlink);
        Nmenu.add(P2);


        JPanel P3 = new JPanel();
        P3.setLayout(new GridLayout(2,1));
        P3.setBackground(Color.LIGHT_GRAY);
        P3.add(clear);
        Bclear =  new JButton(imgclear);
        stylebutton(Bclear);
        Bclear.setPreferredSize(new Dimension(70,70));
        P3.add(Bclear);
        Nmenu.add(P3);

        JPanel P4 = new JPanel();
        P4.setLayout(new GridLayout(2,1));
        P4.setBackground(Color.LIGHT_GRAY);
        P4.add(Run);
        Brun =  new JButton(imgrun);
        stylebutton(Brun);
        Brun.setPreferredSize(new Dimension(70,70));
        P4.add(Brun);
        Nmenu.add(P4);

// créer la topologie jbotsim


        tp = new Topology();
        tp.setDimensions(600,455);
        jtp = new JTopology(tp);
        jtp.setSize(600,459);

        JPanel  Topo = new JPanel();
        Topo.setBackground(Color.LIGHT_GRAY);

        JPanel paneltp = new JPanel();

        bg = new ButtonGroup();
        bg.add(ipv4);
        bg.add(ipv6);
        ipv4.setSelected(true);


        paneltp.add(jtp);
        Topo.add(paneltp);


        frame.add(Topo, BorderLayout.SOUTH);
        frame.add(panelmenu,BorderLayout.CENTER);
        frame.add(menuaide,BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);


    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }


    public void stylebutton(JButton button) {

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }


}
