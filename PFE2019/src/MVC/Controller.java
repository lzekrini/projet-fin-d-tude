package MVC;
import java.awt.event.*;
import jbotsim.*;
import jbotsim.event.SelectionListener;
import jbotsimx.ui.CommandListener;

import javax.swing.*;

public class Controller implements  ActionListener,SelectionListener,CommandListener {
    Model model;
    View view;
    public Controller(Model model,View view){
        this.model=model;
        this.view=view;
        ////////////////////////////Ajout des évenements///////////////////////////
        view.tp.addSelectionListener(this);
        view.getB1().addActionListener(this);
        view.getB2().addActionListener(this);
        view.getB3().addActionListener(this);
        view.getR1pv6().addActionListener(this);
        ///////////////////////////////
        view.getRIPV4().addActionListener(this);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        {
            if(e.getSource()==view.getB1()){System.out.println("this application ...... c 'est pas fini le text"); }

        }

    }

    @Override
    public void onSelection(Node node) {

    }

    @Override
    public void onCommand(String s) {

    }
}
