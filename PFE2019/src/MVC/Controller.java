package MVC;
import java.awt.event.*;
import jbotsim.*;
import jbotsim.Node;
import jbotsim.event.SelectionListener;
import jbotsimx.ui.CommandListener;

import javax.swing.*;
import javax.xml.soap.*;

public class Controller implements  ActionListener,SelectionListener,CommandListener {
    Model model;
    View view;
    int i;
    public Controller(View view){
        this.model=model;
        this.view=view;
        ////////////////////////////Ajout des évenements///////////////////////////
        view.tp.addSelectionListener(this);
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.Baddlink.addActionListener(this);
        view.ipv4.addActionListener(this);
        view.ipv6.addActionListener(this);





    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==view.b1){view.b1.setText("this application ...... c 'est pas fini le texv"); }
           // else if (e.getSource()==)


        if (e.getSource() == view.ipv4) {
            view.tp.setDefaultNodeModel(Ipv4.class);
        }
        else if(e.getSource() == view.ipv6) {
            view.tp.setDefaultNodeModel(Ipv6.class);
        }

        else if(e.getSource()==view.Baddlink) { i = 1;}
    }




    @Override
    public void onSelection(Node node) {

        if (i == 1) { model.addlink(view.tp,node);}

    }

    @Override
    public void onCommand(String s) {

    }
}
